package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.IssueDto;
import ir.co.sadad.cheque.domain.dto.v2.IssueResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import ir.co.sadad.cheque.domain.enums.IssueStatus;
import ir.co.sadad.cheque.repository.ChequeIssueRepository;
import ir.co.sadad.cheque.repository.ChequeStakeholderRepository;
import ir.co.sadad.cheque.service.mapper.ChequeIssueMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChequeIssueServiceImpl implements ChequeIssueService {

    private final ChequeIssueRepository chequeIssueRepository;

    private final ChequeIssueMapper mapper;

    private final ChequeStakeholderRepository stakeholderRepository;

    @Override
    public ChequeIssue createIssue(IssueDto issueDto) {
        checkExistenceOfIssue(issueDto.getSayadId());
        ChequeIssue issueToSave = mapper.toEntity(issueDto);
        issueToSave.setIssueDate(new Date());
        issueToSave.setIssueStatus(IssueStatus.PENDING);
        return chequeIssueRepository.saveAndFlush(issueToSave);
    }

    @Override
    public IssueResponseDto UpdateIssueWithSignInfo(String sayadId, String signatureValue, String transactionId) {
        ChequeIssue savedIssue = getEntityBy(sayadId);
        savedIssue.setSignatureValue(signatureValue);
        savedIssue.setTransactionId(transactionId);
        return mapper.toDto(chequeIssueRepository.saveAndFlush(savedIssue));
    }

    @Override
    public void updateIssueStatus(String sayadId, IssueStatus issueStatus) {
        ChequeIssue savedIssue = getEntityBy(sayadId);
        savedIssue.setIssueStatus(issueStatus);
        chequeIssueRepository.saveAndFlush(savedIssue);
    }

    @Override
    public void updateIssueSigned(String sayadId) {
        ChequeIssue savedIssue = getEntityBy(sayadId);
        savedIssue.setSigned(true);
        chequeIssueRepository.saveAndFlush(savedIssue);
    }

    @Override
    public IssueResponseDto getBy(String sayadId) {
        return mapper.toDto(this.getEntityBy(sayadId));
    }

    @Override
    public ChequeIssue getEntityBy(String sayadId) {
        return chequeIssueRepository.findBySayadId(sayadId).orElseThrow(() -> new ChakadClientException("CHQB.NOT.FOUND.900", Status.NOT_FOUND));
    }

    private void checkExistenceOfIssue(String sayadId) {
        Optional<ChequeIssue> savedIssue = chequeIssueRepository.findBySayadId(sayadId);
        if (savedIssue.isPresent()) {
            if (savedIssue.get().getIssueStatus() != IssueStatus.PENDING &&
                savedIssue.get().getIssueStatus() != IssueStatus.FAILED) {
                throw new ChakadClientException("CHQB.CHEQUE.IS.ISSUED.901", Status.BAD_REQUEST);
            } else {
                Optional<List<ChequeStakeholder>> stakeholders = stakeholderRepository.findAllBySayadId(savedIssue.get().getSayadId());
                stakeholders.ifPresent(stakeholderRepository::deleteAll);
                chequeIssueRepository.delete(savedIssue.get());
            }
        }
    }
}
