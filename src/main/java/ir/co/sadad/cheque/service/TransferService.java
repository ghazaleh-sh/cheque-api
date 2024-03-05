package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.TransferRequestDto;
import ir.co.sadad.cheque.domain.entity.ChequeReason;
import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.TransferDto;

public interface TransferService {
    /**
     * create transfer based on request
     *
     * @param transferDto
     */
    ChequeTransfer createTransfer(TransferRequestDto transferDto, ChequeReason existedReason);

    /**
     * get info of transfer based on sayadId ,
     *
     * @param sayadId cheque identifier
     * @return info of issue
     */
    void getBy(String sayadId);
}
