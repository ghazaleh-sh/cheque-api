package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.ChakadClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.AcceptChequeRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "PichakClient", url = "${feign.client.pichak-url}", configuration = {ChakadClientConfig.class})
public interface PichakClient {
    @RequestMapping(method = RequestMethod.POST, value = "${feign.client.cheque-acceptance}")
    ChakadResponseDto<ObjectUtils.Null> acceptCheques(
        @RequestHeader("Authorization") String bearerToken,
        @RequestHeader("Username") String Username,
        @RequestBody AcceptChequeRequestDto acceptRequestDto);
}
