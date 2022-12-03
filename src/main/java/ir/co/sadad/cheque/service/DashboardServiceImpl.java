package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.entity.*;
import ir.co.sadad.cheque.domain.enums.StakeholderRole;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.repository.*;
import ir.co.sadad.cheque.service.mapper.DashboardMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;
import ir.co.sadad.cheque.web.rest.external.ChakadClient;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CartableDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.TransferChequeResponseDto;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ChequeReasonsRepository reasonsRepository;
    private final ChequeIssueRepository chequeIssueRepository;
    private final ChequeSignerRepository chequeSignerRepository;
    private final ChequeStakeholderRepository stakeholderRepository;
    private final ChequeTransferRepository chequeTransferRepository;

    public DashboardServiceImpl(HttpServletRequest httpServletRequest,
                                SsoClientTokenManager ssoClientTokenManager,
                                DashboardMapper mapper,
                                ChakadClient chakadClient,
                                ChequeReasonsRepository reasonsRepository,
                                ChequeIssueRepository chequeIssueRepository,
                                ChequeSignerRepository chequeSignerRepository,
                                ChequeStakeholderRepository stakeholderRepository,
                                ChequeTransferRepository chequeTransferRepository) {
        super(httpServletRequest, ssoClientTokenManager);
        this.mapper = mapper;
        this.chakadClient = chakadClient;
        this.reasonsRepository = reasonsRepository;
        this.chequeIssueRepository = chequeIssueRepository;
        this.chequeSignerRepository = chequeSignerRepository;
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
    public ChakadCartableResponseDto cartable(String authToken,
                                              ChakadCartableRequestDto cartableRequest) {

        ChakadResponseDto<CartableDto> cartable =
            chakadClient.cartable(getToken(), getUserAgent(), mapper.toCartableReqDto(cartableRequest));

        if (SUCCESS_CHAKAD_CODE.equals(cartable.getMassageCode()))
            return mapper.toCartableResDto(cartable.getData());
        else
            throw new ChakadClientException(cartable.getMessage());
    }

    /**
     * {@inheritDoc}
     *
     * @param reasonType type of reason
     * @return
     */
    @Override
    public List<ReasonResponseDto> getReasons(String reasonType) {
        if (reasonType.isBlank()) {
            throw new GeneralException();
        }
        return mapper.toReasonResponse(reasonsRepository.findByReasonType(reasonType));
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    @SneakyThrows
    public ResponseEntity<HttpStatus> issue(String authToken, ChakadIssueRequestDto issueRequestDto) {

        if (issueRequestDto.getCheque().getReason() == null
            && Long.parseLong(issueRequestDto.getCheque().getAmount()) >= AMOUNT_FOR_REASON)
            throw new ChakadClientException("chakad.error.reason.is.mandatory", Status.BAD_REQUEST);

        ChequeReason reason = checkReason(issueRequestDto.getCheque().getReason());


        ChakadResponseDto<ObjectUtils.Null> issueResponse = chakadClient.issueCheque(getToken(),
            getUserAgent(),
            mapper.toIssueCheque(issueRequestDto));

        if (!SUCCESS_CHAKAD_CODE.equals(issueResponse.getMassageCode()))
            throw new ChakadClientException(issueResponse.getMessage());

        try {
            ChequeIssue chequeIssue = mapper.toIssueEntity(issueRequestDto.getCheque());
            chequeIssue.setChequeReason(reason);
            chequeIssueRepository.saveAndFlush(chequeIssue);

            saveSigners(issueRequestDto.getCheque().getSigners(), chequeIssue, null);
            saveStakeholders(issueRequestDto.getCheque().getSayadId(),
                issueRequestDto.getCheque().getReceivers(),
                issueRequestDto.getCheque().getGuarantors(),
                chequeIssue);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            throw new ChakadClientException("chakad.issue.database.constraint", Status.BAD_REQUEST);
        }

    }

    @Override
    @SneakyThrows
    public ResponseEntity<HttpStatus> transfer(String authToken, ChakadTransferRequestDto transferRequestDto) {

        //for checking amount of issue which related to transfer
//        if (transferRequestDto.getTransfer().getReason() == null) {
//            ChequeIssue relatedIssue = chequeIssueRepository.
//                findTopBySayadId(transferRequestDto.getTransfer().getSayadId());
//            if (relatedIssue != null && Long.parseLong(relatedIssue.getAmount()) >= AMOUNT_FOR_REASON)
//                throw new ChakadClientException("chakad.error.reason.is.mandatory", Status.BAD_REQUEST);
//        }

        ChequeReason reason = checkReason(transferRequestDto.getTransfer().getReason());

        ChakadResponseDto<TransferChequeResponseDto> transferResponse = chakadClient.transferCheques(getToken(),
            getUserAgent(),
            mapper.toTransferCheque(transferRequestDto));

        if (!SUCCESS_CHAKAD_CODE.equals(transferResponse.getMassageCode()))
            throw new ChakadClientException(transferResponse.getMessage());

        try {
            ChequeTransfer chequeTransfer = mapper.toTransferEntity(transferRequestDto.getTransfer());
            chequeTransfer.setChequeReason(reason);
            chequeTransferRepository.saveAndFlush(chequeTransfer);

            saveSigners(transferRequestDto.getTransfer().getSigners(), null, chequeTransfer);
            saveStakeholders(transferRequestDto.getTransfer().getSayadId(),
                transferRequestDto.getTransfer().getReceivers(),
                null,
                chequeTransfer);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ConstraintViolationException e) {
            throw new ChakadClientException("chakad.issue.database.constraint", Status.BAD_REQUEST);

        }

    }


    private ChequeReason checkReason(String reason) {
        ChequeReason existedReason = null;
        if (reason != null)
            existedReason = reasonsRepository.findByReasonCode(reason)
                .orElseThrow(() -> new ChakadClientException("reason.not.find", Status.BAD_REQUEST));
        return existedReason;
    }


    private void saveSigners(List<ChakadSignerDto> signers, ChequeIssue chequeIssue, ChequeTransfer chequeTransfer) {
        List<ChequeSigner> chequeSigner = mapper.toSignerEntity(signers);
        chequeSigner.forEach(s -> {
            if (chequeIssue != null) {
                s.setChequeIssue(chequeIssue);
            } else if (chequeTransfer != null) {
                s.setChequeTransfer(chequeTransfer);
            }
        });
        chequeSignerRepository.saveAllAndFlush(chequeSigner);
    }

    private <T> void saveStakeholders(String sayadId, List<ChakadReceiverDto> receivers, List<ChakadGuarantorDto> guarantors, T obj) {
        stakeholderRepository.findAllBySayadIdAndIsFinalTrue(sayadId)
            .forEach(stk -> {
                stk.setIsFinal(false);
                stakeholderRepository.saveAndFlush(stk);
            });

        receivers.forEach(rec -> {
            ChequeStakeholder stakeholder = new ChequeStakeholder();
            stakeholder.setName(rec.getName());
            stakeholder.setIdentifier(rec.getIdentifier());
            stakeholder.setIdentifierType(rec.getIdentifierType().toString());
            stakeholder.setSayadId(sayadId);
            stakeholder.setRole(StakeholderRole.RECIEVER);
            stakeholder.setIsFinal(true);
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
                    granter.setRole(StakeholderRole.GRANTER);
                    granter.setIsFinal(true);
                    granter.setChequeIssue((ChequeIssue) obj);
                    stakeholderRepository.save(granter);
                });
    }

}
