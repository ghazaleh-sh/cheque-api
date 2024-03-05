package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * dto for cheque request
 * name of document : Cheque-Chakad-issueV1.2.pdf
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChequeDto {

    /**
     * ليست دريافت كنندگان چك
     * require
     */
    private List<ReceiverDto> receivers;

    /**
     * ليست امضاكنندگان چك
     * require
     */
    private List<SignerDto> signers;

    /**
     * ليست ضمانت كنندگان چك
     * require
     */
    private List<GuarantorDto> guarantors;

    /**
     * شناسه صياد چك مورد نظر
     * require
     */
    private String sayadId;


    /**
     * شماره سريال چك؛ حداكثر٢٠ رقم
     * require
     */
    private String serialNumber;


    /**
     * حداكثر ١٠ كاراكتر، مثال ٦٥-ب
     * require
     */
    private String seriNumber;

    /**
     * مبلغ چك،
     * حداكثر ١٦ رقم فقط شامل اعداد
     * require
     */
    private Long amount;

    /**
     * تاريخ خورشيدي سررسيد چك
     * رشته كاركتري به طول ٨ در ساختار yyyyMMdd
     * require
     */
    private String settlementDate;

    /**
     * شرح چك
     * require
     */
    private String description;

    /**
     * بابت
     * اين فيلد متناسب با مبلغ چك تعيين مي شود.
     * توضيحات اين فيلد در بخش مقادير مجاز براي "بابت"
     * آورده شده است.
     */
    private String reason;


}
