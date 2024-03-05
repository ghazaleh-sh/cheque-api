package ir.co.sadad.cheque.web.rest.external;

import ir.co.sadad.cheque.web.rest.external.config.EsServiceClientConfig;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.SayadReportRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.request.chakad.SayadRequestDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.SayadRequestResponseDto;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.SayadReportResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "EsServiceClient", url = "${feign.client.esservice-url}", configuration = {EsServiceClientConfig.class})
public interface EsServiceClient {

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
}
