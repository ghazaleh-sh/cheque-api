package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.StakeholderDto;
import ir.co.sadad.cheque.domain.dto.v2.StakeholderResponseDto;

import java.util.List;

/**
 * <pre>
 *     to do changes associated with Stakeholder table
 * </pre>
 */
public interface StakeholderService {

    /**
     * create Stakeholder based on request
     *
     * @param stakeholderDto
     */
    void createStakeholder(StakeholderDto stakeholderDto);

    /**
     * update Stakeholder based on request
     *
     * @param
     * @return StakeholderResponseDto
     */
    StakeholderResponseDto UpdateStakeholder(String sayadId, String signatureValue, String transactionId);

    /**
     * get info of Stakeholder based on sayadId ,
     *
     * @param sayadId cheque identifier
     * @return info of stakeholder
     */
    List<StakeholderResponseDto> getBy(String sayadId);
}
