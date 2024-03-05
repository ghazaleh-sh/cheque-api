package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.TransferRequestDto;
import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.repository.ChequeTransferRepository;
import ir.co.sadad.cheque.service.mapper.TransferMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final ChequeTransferRepository transferRepository;
    private final ChequeIssueService issueService;
    private final TransferMapper mapper;

    @Override
    public ChequeTransfer createTransfer(TransferRequestDto transferDto, ChequeReason existedReason) {
        ChequeTransfer transferToSave = mapper.toEntity(transferDto);
        transferToSave.setTransferDate(new Date());
        transferToSave.setChequeReason(existedReason);
        try {
            transferToSave.setChequeIssue(issueService.getEntityBy(transferDto.getSayadId()));
        } catch (ChakadClientException ex) {
            transferToSave.setChequeIssue(null);
        }
        return transferRepository.saveAndFlush(transferToSave);
    }

    @Override
    public void getBy(String sayadId) {

    }
}
