package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.domain.enums.IssueStatus;
import ir.co.sadad.cheque.domain.enums.ReasonType;
import ir.co.sadad.cheque.domain.enums.StakeholderRole;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.repository.ChequeIssueRepository;
import ir.co.sadad.cheque.repository.ChequeReasonsRepository;
import ir.co.sadad.cheque.repository.ChequeStakeholderRepository;
import ir.co.sadad.cheque.repository.ChequeTransferRepository;
import ir.co.sadad.cheque.service.mapper.DashboardMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.PichakClient;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.IssueChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CartableResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadTransferTotalResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
@Log4j2
public class DashboardServiceImpl extends DashboardService {

    private final DashboardMapper mapper;
    private final ChakadClient chakadClient;

    private final PichakClient pichakClient;
    private final ChequeReasonsRepository reasonsRepository;
    private final ChequeIssueRepository chequeIssueRepository;
    private final ChequeStakeholderRepository stakeholderRepository;
    private final ChequeTransferRepository chequeTransferRepository;

    public DashboardServiceImpl(HttpServletRequest httpServletRequest,
                                SsoClientTokenManager ssoClientTokenManager,
                                DashboardMapper mapper,
                                ChakadClient chakadClient,
                                PichakClient pichakClient, ChequeReasonsRepository reasonsRepository,
                                ChequeIssueRepository chequeIssueRepository,
                                ChequeStakeholderRepository stakeholderRepository,
                                ChequeTransferRepository chequeTransferRepository) {
        super(httpServletRequest, ssoClientTokenManager);
        this.mapper = mapper;
        this.chakadClient = chakadClient;
        this.pichakClient = pichakClient;
        this.reasonsRepository = reasonsRepository;
        this.chequeIssueRepository = chequeIssueRepository;
        this.stakeholderRepository = stakeholderRepository;
        this.chequeTransferRepository = chequeTransferRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @param authToken       token of password credential
     * @param cartableRequest request
     * @return
     */
    @Override
    @SneakyThrows
    public List<ChakadCartableResponseDto> cartable(String authToken,
                                                    ChakadCartableRequestDto cartableRequest) {


        ChakadResponseDto<CartableResponseDto> cartable =
            chakadClient.cartable(getToken(), getUserAgent(), mapper.toCartableReqDto(cartableRequest));

        if (SUCCESS_CHAKAD_CODE.equals(cartable.getCode()))
            return mapper.toCartableResDto(cartable.getData().getCheques());
        else
            throw new ChakadClientException(cartable.getCode());
    }

    /**
     * {@inheritDoc}
     *
     * @param reasonType type of reason: ISSUE or TRANSFER
     * @return
     */
    @Override
    @Cacheable(value = "cacheReason")
    @SneakyThrows
    public List<ReasonResponseDto> getReasons(String reasonType) {
        List<ReasonResponseDto> responseList = mapper.toReasonResponse(reasonsRepository.
            findByReasonType(ReasonType.getReasonByDescription(reasonType).getValue()));
        responseList.forEach(reason -> reason.setReasonType(reasonType));
        return responseList;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    @SneakyThrows
    public void issue(String authToken, ChakadIssueRequestDto issueRequestDto) {

        if (issueRequestDto.getCheque().getReason() == null
            && Long.parseLong(issueRequestDto.getCheque().getAmount()) >= AMOUNT_FOR_REASON)
            throw new ChakadClientException("chakad.error.reason.is.mandatory", Status.BAD_REQUEST);

        ChequeReason reason = checkReason(issueRequestDto.getCheque().getReason());

        IssueChequeRequestDto mapped = this.mapper.toIssueCheque(issueRequestDto);
        ChakadResponseDto<ObjectUtils.Null> issueResponse = chakadClient.issueCheque(getToken(),
//            basicAuth,
            getUserAgent(),
            mapped);

        if (!SUCCESS_CHAKAD_CODE.equals(issueResponse.getCode()))
            throw new ChakadClientException(issueResponse.getCode());

        try {
            ChequeIssue chequeIssue = this.mapper.toIssueEntity(issueRequestDto.getCheque());
            chequeIssue.setChequeReason(reason);
            chequeIssueRepository.saveAndFlush(chequeIssue);

//            saveSigners(issueRequestDto.getCheque().getSigners(), chequeIssue, null);
            saveStakeholders(issueRequestDto.getCheque().getSayadId(),
                issueRequestDto.getCheque().getSigners(),
                issueRequestDto.getCheque().getReceivers(),
                issueRequestDto.getCheque().getGuarantors(),
                chequeIssue);

        } catch (DataIntegrityViolationException e) {
            throw new ChakadClientException("chakad.issue.database.constraint", Status.BAD_REQUEST);
        }

    }

    @Override
    @SneakyThrows
    public void transfer(String authToken, ChakadTransferRequestDto transferRequestDto) {

        //for checking amount of issue which related to transfer
//        if (transferRequestDto.getTransfer().getReason() == null) {
//            ChequeIssue relatedIssue = chequeIssueRepository.
//                findTopBySayadId(transferRequestDto.getTransfer().getSayadId());
//            if (relatedIssue != null && Long.parseLong(relatedIssue.getAmount()) >= AMOUNT_FOR_REASON)
//                throw new ChakadClientException("chakad.error.reason.is.mandatory", Status.BAD_REQUEST);
//        }

        ChequeReason reason = checkReason(transferRequestDto.getTransfer().getReason());

        ChakadTransferTotalResponseDto transferResponse = chakadClient.transferCheques(getToken(),
            authToken,
            getUserAgent(),
            null,
            mapper.toTransferCheque(transferRequestDto.getTransfer()));

        if (!SUCCESS_CHAKAD_CODE.equals(transferResponse.getMessage()))
            throw new ChakadClientException(transferResponse.getCode());

        try {
            ChequeTransfer chequeTransfer = mapper.toTransferEntity(transferRequestDto.getTransfer());
            chequeTransfer.setChequeReason(reason);

            ChequeIssue relatedIssue = checkIssue(transferRequestDto.getTransfer().getSayadId());
            chequeTransfer.setChequeIssue(relatedIssue);

            chequeTransferRepository.saveAndFlush(chequeTransfer);

//            saveSigners(transferRequestDto.getTransfer().getSigners(), null, chequeTransfer);
            saveStakeholders(transferRequestDto.getTransfer().getSayadId(),
                transferRequestDto.getTransfer().getSigners(),
                transferRequestDto.getTransfer().getReceivers(),
                null,
                chequeTransfer);

        } catch (ConstraintViolationException e) {
            throw new ChakadClientException("chakad.issue.database.constraint", Status.BAD_REQUEST);

        }

    }

    @Override
    public void acceptance(String authToken, ChakadAcceptRequestDto acceptRequestDto) {

        ChakadResponseDto<ObjectUtils.Null> acceptResponse = pichakClient.acceptCheques(getToken(),
            ACCEPT_SERVICE_USERNAME,
            mapper.toAcceptCheque(acceptRequestDto));

        if (!SUCCESS_PICHAK_CODE.equals(acceptResponse.getMessage()))
            throw new ChakadClientException(acceptResponse.getMessage());

        ChequeIssue currentCheque = checkIssue(acceptRequestDto.getSayadId());
        currentCheque.setIssueStatus(IssueStatus.APPROVED);
        chequeIssueRepository.saveAndFlush(currentCheque);

    }

    private ChequeIssue checkIssue(String sayadId) {
        ChequeIssue currentCheque = chequeIssueRepository.findTopBySayadId(sayadId);
        if (currentCheque != null)
            return currentCheque;
        else throw new ChakadClientException("sayad.id.not.find", Status.BAD_REQUEST);
    }

    private ChequeReason checkReason(String reason) {
        ChequeReason existedReason = null;
        if (reason != null)
            existedReason = reasonsRepository.findByReasonCode(reason)
                .orElseThrow(() -> new ChakadClientException("reason.not.find", Status.BAD_REQUEST));
        return existedReason;
    }


//    private void saveSigners(List<ChakadSignerDto> signers, ChequeIssue chequeIssue, ChequeTransfer chequeTransfer) {
//        List<ChequeStakeholder> chequeBaseStakeholderUser = mapper.toُStakeholderEntity(signers);
////        chequeSigner.forEach(s -> {
////            if (chequeIssue != null) {
////                s.setChequeIssue(chequeIssue);
////            } else if (chequeTransfer != null) {
////                s.setChequeTransfer(chequeTransfer);
////            }
////        });
//        chequeSignerRepository.saveAllAndFlush(chequeSigner);
//    }

    private <T> void saveStakeholders(String sayadId,
                                      List<ChakadSignerDto> signers,
                                      List<ChakadReceiverDto> receivers,
                                      List<ChakadGuarantorDto> guarantors,
                                      T obj) {
//        stakeholderRepository.findAllBySayadIdAndIsFinalTrue(sayadId)
//            .forEach(stk -> {
//                stk.setIsFinal(false);
//                stakeholderRepository.saveAndFlush(stk);
//            });
        List<ChequeStakeholder> baseStakeholderUser = mapper.toُStakeholderEntity(signers);
        baseStakeholderUser.forEach(user -> user.setSayadId(sayadId));
        stakeholderRepository.saveAllAndFlush(baseStakeholderUser);

        receivers.forEach(rec -> {
            ChequeStakeholder stakeholder = new ChequeStakeholder();
            stakeholder.setName(rec.getName());
            stakeholder.setIdentifier(rec.getIdentifier());
            stakeholder.setIdentifierType(rec.getIdentifierType().toString());
            stakeholder.setSayadId(sayadId);
            stakeholder.setRole(StakeholderRole.RECEIVER);
//            stakeholder.setIsFinal(true);
            if (obj instanceof ChequeIssue)
                stakeholder.setChequeIssue((ChequeIssue) obj);
            else if (obj instanceof ChequeTransfer)
                stakeholder.setChequeTransfer((ChequeTransfer) obj);
            stakeholderRepository.save(stakeholder);
        });

        if (obj instanceof ChequeIssue)
            if (guarantors != null)
                guarantors.forEach(rec -> {
                    ChequeStakeholder granter = new ChequeStakeholder();
                    granter.setName(rec.getName());
                    granter.setIdentifier(rec.getIdentifier());
                    granter.setIdentifierType(rec.getIdentifierType().toString());
                    granter.setSayadId(sayadId);
                    granter.setRole(StakeholderRole.GRANTOR);
//                    granter.setIsFinal(true);
                    granter.setChequeIssue((ChequeIssue) obj);
                    stakeholderRepository.save(granter);
                });
    }

}
