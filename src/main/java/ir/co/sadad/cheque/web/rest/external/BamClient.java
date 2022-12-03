package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.BamClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.response.AccountDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.ChequebookResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.RemainedTwentyPercentChequeResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.bam.CustomerDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.bam.GenericResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "BamClient", url = "${feign.client.bam-api}", configuration = { BamClientConfig.class })
public interface BamClient {
    @RequestMapping(method = RequestMethod.GET, value = "/v2/cheque/chequebookList/1/100")
    List<ChequebookResponseDto> checkIsFirstCheque(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.GET, value = "/v2/cheque/chequebookList/{accountId}/1/100")
    RemainedTwentyPercentChequeResponseDto inquiryRemainedTwentyPercentCheque(
        @RequestHeader("Authorization") String bearerToken,
        @PathVariable("accountId") String accountId
    );

    @RequestMapping(method = RequestMethod.GET, value = "/customer/accounts/full")
    List<AccountDto> getAccountList(@RequestHeader("Authorization") String bearerToken);

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    GenericResponseDto<CustomerDto> getProfile(@RequestHeader("Authorization") String bearerToken);
}
