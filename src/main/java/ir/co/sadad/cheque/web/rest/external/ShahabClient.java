package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.ShahabClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.shahab.CodeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.shahab.ShahabInquiryRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.ShahabResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ShahabClient", url = "${feign.client.shahab-url}", configuration = {ShahabClientConfig.class})
public interface ShahabClient {

    /**
     * not used
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.inquiry-path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ShahabResponseDto shahabInquiry(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody ShahabInquiryRequestDto shahabInquiryRequestDto
    );

    /**
     * not used
     */
    @RequestMapping(method = RequestMethod.GET,
        value = "${feign.client.track-path}" + "/trackCode/{trackCode}/nationalCode/{nationalCode}",
        consumes = MediaType.APPLICATION_JSON_VALUE)
    ShahabResponseDto shahabTrack(
        @RequestHeader("Authorization") String bearerToken,
        @PathVariable("nationalCode") String nationalCode,
        @PathVariable("trackCode") String trackCode
    );

    /**
     * this client gets customer identifier and returns its shahab code
     *
     * @param bearerToken    client token with its described scopes
     * @param codeRequestDto includes code and type of customer
     * @return the shahab code - If error is generated, @ShahabClientConfig decodes via ShahabClientError
     */
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.code-path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ShahabResponseDto shahabCode(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody CodeRequestDto codeRequestDto
    );

}
