package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.EsServiceClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadErrorResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChequeInquirySheetResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.DepositInquiryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "PichakEgwClient", url = "${feign.client.chakad-url}", configuration = {EsServiceClientConfig.class})
public interface PichakEgwClient {
    /**
     * service of batch inquiry
     *
     * @param authorization             token
     * @param creator                   درخواست كننده
     * @param Username                  نام كاربري درخواست كننده
     * @param chequeInquiryBatchRequest request
     * @return response for batch inquiry
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.batch-inquiry-path}")
    ChequeInquiryBatchResponseDto bookInquiry(
        @RequestHeader("Authorization") String authorization,
        @RequestHeader("creator") String creator,
        @RequestHeader("Username") String Username,
        @RequestBody ChequeInquiryBatchRequestDto chequeInquiryBatchRequest
    );

    /**
     * service for sheet inquiry
     *
     * @param bearerToken               token
     * @param creator                   createor
     * @param Username                  userName of creator
     * @param chequeInquirySheetRequest request
     * @return response of sheet inquiry
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.sheet-inquiry-path}")
    ChequeInquirySheetResponseDto sheetInquiry(
        @RequestHeader("authorization") String bearerToken,
        @RequestHeader("creator") String creator,
        @RequestHeader("username") String Username,
        @RequestBody ChequeInquirySheetRequestDto chequeInquirySheetRequest
    );
}
