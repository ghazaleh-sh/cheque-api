package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigInteger;

/**
 * dto for sheet inquiry cheque
 * <pre>
 *     for more info : GR-MNG-002.pdf
 * </pre>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChequeInquirySheetRequestDto {

    /**
     * شناسه صیادی
     * 16رقم
     * require-if
     */
    private String sayadId;

    /**
     * شماره حساب
     * 13 رقم
     * require-if
     */
    private String accountNumber;

    /**
     * سری و سریال چک
     * 10 رقم
     * require-if
     */
    private String chequeNumber;

    /**
     * cheque number
     */
    private Long chequeIssueId;

    /**
     * نوع آرشسو
     */
    private Integer archiveType;

}
