package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.AllocatingEstelamResponseDto;

public interface ChequeService {
    ChequeRequestResponse chequeRequest(ChequeRequestDto chequeRequestDto);

    AllocatingEstelamResponseDto chequeAllocatingInquiry(AllocatingInquiryRequestDto allocatingInquiryRequestDto);

    BouncedChequeResponseDto bouncedInquiry();

    BouncedChequeResponseDto bouncedInquiryWithSsn(String ssn);

    ChequeResponse report(String iban, int timeInterval);
}
