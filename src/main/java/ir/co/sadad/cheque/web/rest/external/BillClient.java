package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.ChequeClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.CustomerAccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "BillClient", url = "${feign.client.billService-url}", configuration = {ChequeClientConfig.class})
public interface BillClient {
    @RequestMapping(method = RequestMethod.GET, value = "${feign.client.account-customer-path}")
    CustomerAccountResponseDto getCustomerAccount(
        @RequestParam("accNo") String accNo
    );

}
