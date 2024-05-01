package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.ChequeClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.AllocatingEstelamRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.ChequeRegisterDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.ChequeReportRequest;
import ir.co.sadad.cheque.web.rest.external.dto.response.AllocatingEstelamResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.ChequeReportResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "ChequeClient", url = "${feign.client.cheque-url}", configuration = { ChequeClientConfig.class })
public interface ChequeClient {
    @PostMapping(value = "${feign.client.sayadRequest-path}")
    SayadRequestResponseDto sayadRequest(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody ChequeRegisterDto chequeRegisterDto
    );

    @PostMapping(value = "${feign.client.allocating-path}")
    AllocatingEstelamResponseDto getAllocatingEstelam(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody AllocatingEstelamRequestDto allocatingEstelamRequestDto
    );

    @PostMapping(value = "${feign.client.report-path}")
    ChequeReportResponseDto report(
        @RequestHeader("Authorization") String bearerToken,
        @RequestBody ChequeReportRequest chequeReportRequest
    );
}
