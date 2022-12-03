package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.ChakadClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.*;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ChakadClient", url = "${feign.client.cheque-url}", configuration = {ChakadClientConfig.class})
public interface ChakadClient {

    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.activation-path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ChakadResponseDto<ObjectUtils.Null> activationProfile(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestBody ActivationRequestDto activationRequestDto
    );


    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.deactivation-path}")
    ChakadResponseDto<ObjectUtils.Null> deactivationProfile(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestBody DeactivationRequestDto deactivationRequestDto
    );

    /**
     * service for cartable
     *
     * @param bearerToken        token of service desk
     * @param callerTerminalName userCode
     * @param cartableRequest    request for cartable
     * @return cartable
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.cartable-path}")
    ChakadResponseDto<CartableDto> cartable(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestBody CartableRequestDto cartableRequest
    );

    /**
     * service for checking inquiry status of customers
     *
     * @param bearerToken          token of service desk
     * @param callerTerminalName   userCode
     * @param inquiryStatusRequest request for status of customer
     * @return list of activation status of customers
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.inquiry-status-path}")
    ChakadResponseDto<InquiryStatusResponseDto> inquiryStatus(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestBody InquiryStatusRequestDto inquiryStatusRequest
    );


    /**
     * service for issue cheques
     *
     * @param bearerToken        token of service desk
     * @param callerTerminalName userCode
     * @param issueCheque        request for issue cheque
     * @return result of issuing cheques
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.issue-cheque-path}")
    ChakadResponseDto<ObjectUtils.Null> issueCheque(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestBody IssueChequeRequestDto issueCheque
    );


    /**
     * service for transfer cheques
     *
     * @param bearerToken           token of service desk
     * @param callerTerminalName    userCode
     * @param transferChequeRequest request for transfer cheques
     * @return result of transfer cheques
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.transfer-cheque-path}")
    ChakadResponseDto<TransferChequeResponseDto> transferCheques(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestBody TransferChequeRequestDto transferChequeRequest
    );

    /**
     * service for report of sayad
     *
     * @param bearerToken        token of service desk
     * @param sayadReportRequest sayad request
     * @return result of sayad report
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.sayad-report-path}")
    SayadReportResponseDto sayadReport(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody SayadReportRequestDto sayadReportRequest
    );

    /**
     * request for cheque
     *
     * @param bearerToken  token of service desk
     * @param sayadRequest request for cheques
     * @return 100 if everything is success , other if something is not right
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.sayad-request-path}")
    SayadRequestResponseDto sayadRequest(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody SayadRequestDto sayadRequest
    );


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
    ChequeInquiryBatchResponseDto batchInquiry(
        @RequestHeader("Authorization") String authorization,
        @RequestHeader("creator") Integer creator,
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
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("creator") Integer creator,
        @RequestHeader("username") String Username,
        @RequestBody ChequeInquirySheetRequestDto chequeInquirySheetRequest
    );


}
