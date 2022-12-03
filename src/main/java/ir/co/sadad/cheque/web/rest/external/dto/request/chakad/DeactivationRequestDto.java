package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import ir.co.sadad.cheque.web.rest.external.dto.response.bam.CustomerDto;
import lombok.Data;

import java.util.List;

/**
 * dto for deactivation of chakad
 * <pre>
 *     for more info : Cheque-Chakad-deactivationV1.3.pdf
 * </pre>
 */
@Data
public class DeactivationRequestDto {

    /**
     * نوع توكن مشتري كه در طي امضاي ديجيتال از آن
     * استفاده شده است:
     * ١ :توكن نماد
     * require
     */
    private Integer tokenType;

    /**
     * مهر اشخاص حقوقي؛
     * ٠ :نمي باشد
     * ١ :مي باشد
     * require
     */
    private Integer legalStamp;


    private CustomerRequestDto customer;

    private OrganizationDto organization;
}
