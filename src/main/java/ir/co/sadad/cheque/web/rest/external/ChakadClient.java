package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.domain.dto.ChakadAcceptRequestDto;
import ir.co.sadad.cheque.web.rest.external.config.ChakadClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.*;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.*;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ChakadClient", url = "${feign.client.chakad-url}", configuration = {ChakadClientConfig.class})
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
    ChakadResponseDto<CartableResponseDto> cartable(
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
    ChakadTransferTotalResponseDto transferCheques(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("x-user-authorization") String clientBearerToken,
        @RequestHeader("callerTerminalName") String callerTerminalName,
        @RequestParam("authorizationCode") String otpCode,
        @RequestBody TransferDto transferChequeRequest
    );

    /**
     * service of challenge code
     * @param bearerToken token of service desk
     * @param callerTerminalName userCode
     * @param challengeCodeDto request for challenge code
     * @return challenge code
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.challenge-code-path}")
    ChakadResponseDto<ChallengeCodeResponseDto> challengeCode(
            @RequestHeader("Authorization") String bearerToken,
            @RequestHeader("callerTerminalName") String callerTerminalName,
            @RequestBody ChallengeCodeDto challengeCodeDto
    );

    /**
     * service of deposit Register
     * @param bearerToken token of service desk
     * @param depositRegisterDto request for deposit register
     * @return status of registering deposit
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.deposit-register-path}")
    ChakadErrorResponseDto depositRegister(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody DepositRegisterDto depositRegisterDto
    );

    /**
     * service of deposit inquiry
     * @param bearerToken token of service desk
     * @param depositInquiryDto request for deposit Inquiry
     * @return Data list of user deposits
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.deposit-inquiry-path}")
    DepositInquiryResponseDto depositInquiry(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody DepositInquiryDto depositInquiryDto
    );

    /**
     * service of deposit inquiry
     * @param bearerToken token of service desk
     * @param depositCancelDto request for deposit cancel
     * @return status of canceling deposit
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.deposit-cancel-path}")
    ChakadErrorResponseDto depositCancel(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody DepositCancelDto depositCancelDto
    );
}
