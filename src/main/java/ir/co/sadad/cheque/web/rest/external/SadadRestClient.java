package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.SadadRestClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.BouncedChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.GuaranteeInquiryRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.SamatInquiryRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.ApiGeneralResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.BouncedChequeInquiryResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.GuaranteeInquiryResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.SamatInquiryMainResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "SadadRestClient", url = "${feign.client.sadad-rest-url}", configuration = { SadadRestClientConfig.class })
public interface SadadRestClient {
    @RequestMapping(method = RequestMethod.POST, value = "/api/sama-cheque/v1/bounced/cheques/customer-Info/inquiry")
    ApiGeneralResponseDto<BouncedChequeInquiryResponseDto> getBouncedChequeInquiry(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody BouncedChequeRequestDto bouncedChequeRequestDto
    );

    @RequestMapping(method = RequestMethod.POST, value = "/api/samat/v1/inquiry/main")
    ApiGeneralResponseDto<SamatInquiryMainResponseDto> inquiryFacility(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody SamatInquiryRequestDto samatInquiryRequestDto
    );

    // this service has not used in this project until now
    @RequestMapping(method = RequestMethod.POST, value = "/api/loan-guarantee/v1/guarantor/guarantee/inquiry")
    ApiGeneralResponseDto<GuaranteeInquiryResponseDto> guaranteeInquiry(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody GuaranteeInquiryRequestDto guaranteeInquiryRequestDto
    );
}
