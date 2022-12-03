package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

import java.math.BigInteger;

/**
 * dto for sheet inquiry cheque
 * <pre>
 *     for more info : GR-MNG-002.pdf
 * </pre>
 */
@Data
public class ChequeInquirySheetRequestDto {

    /**
     * شناسه صیادی
     * 16رقم
     * require-if
     */
    private Integer sayadId;

    /**
     * شماره حساب
     * 13 رقم
     * require-if
     */
    private String account;

    /**
     * سری و سریال چک
     * 10 رقم
     * require-if
     */
    private String chequeNumber;

    /**
     * کد درخواست دسته چک
     * require-if
     */
    private BigInteger chequeIssueId;
}
