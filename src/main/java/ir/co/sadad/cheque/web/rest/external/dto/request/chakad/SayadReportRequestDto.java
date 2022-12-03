package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * dto for sayad report request
 * <pre>
 *     for more info : Cheque-Sayad-Report.pdf
 * </pre>
 */
@Data
public class SayadReportRequestDto {

    /**
     * شماره شبا
     * حساب جاری بانک ملی
     * require - 26 char
     */
    private String IBAN;

    /**
     * زمانی ب
     * 1 )یک ماهه
     * 2 )سه ماهه
     * 3 )شش ماهه
     * 4 )یک ساله
     * require - 1 char
     */
    private Integer length;
}
