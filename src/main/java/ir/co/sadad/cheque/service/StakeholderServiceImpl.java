package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.StakeholderDto;
import ir.co.sadad.cheque.domain.dto.v2.StakeholderResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import ir.co.sadad.cheque.repository.ChequeStakeholderRepository;
import ir.co.sadad.cheque.service.mapper.StakeholderMapper;
import ir.co.sadad.cheque.web.rest.errors.ChakadClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class StakeholderServiceImpl implements StakeholderService {

    private final ChequeStakeholderRepository stakeholderRepository;

    private final StakeholderMapper mapper;

    @Override
    public void createStakeholder(StakeholderDto stakeholderDto) {
        ChequeStakeholder issueToSave = mapper.toEntity(stakeholderDto);
        stakeholderRepository.saveAndFlush(issueToSave);
    }

    @Override
    public StakeholderResponseDto UpdateStakeholder(String sayadId, String signatureValue, String transactionId) {
        return null;
    }

    @Override
    public List<StakeholderResponseDto> getBy(String sayadId) {
        return mapper.toDto(this.getBySayadId(sayadId));
    }

    private List<ChequeStakeholder> getBySayadId(String sayadId) {
        return stakeholderRepository.findAllBySayadId(sayadId).orElseThrow(() -> new ChakadClientException("CHQB.NOT.FOUND.900", Status.NOT_FOUND));
    }
}
