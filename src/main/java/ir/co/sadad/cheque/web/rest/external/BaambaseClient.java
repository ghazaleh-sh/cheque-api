package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.BaambaseClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.BaamBaseResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.CreateSignatureRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.SignedDataRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CertificationResBodyDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CreateSignatureResBodyDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.SignedDataResBodyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "BaambaseClient", url = "${feign.client.baambase-url}", configuration = {BaambaseClientConfig.class})
public interface BaambaseClient {

    @RequestMapping(method = RequestMethod.GET, value = "${feign.client.user-certifications-path}")
    BaamBaseResponseDto<List<CertificationResBodyDto>> userCertification(
        @RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.create-signature-path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    BaamBaseResponseDto<CreateSignatureResBodyDto> createSignature(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody CreateSignatureRequestDto createSignatureRequestDto);

    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.signed-data-path}")
    BaamBaseResponseDto<SignedDataResBodyDto> signedData(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody SignedDataRequestDto signedDataRequestDto);
}
