package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * response of cartable dto
 */
@Data
@Schema(title = "آبجکت خروجی کارتابل چکاد")
public class ChakadCartableResponseDto {

    @Schema(title = "شناسه سیاد")
    private String sayadId;

    @Schema(title = " شماره سريال چك")
    private String serialNo;

    @Schema(title = "  شماره سري چك")
    private String seriesNo;

    @Schema(title = " كد شبا حساب عهده")
    private String fromIban;

    @Schema(title = " مبلغ چك")
    private Long amount;

    @Schema(title = "تاريخ خورشيدي سر رسيد چك")
    private String dueDate;

    @Schema(title = "شرح چك")
    private String description;

    @Schema(title = " كد بانك عهده چك")
    private String bankCode;

    @Schema(title = " كد شعبه عهده چك")
    private String branchCode;

    @Schema(title = "نوع ارز چك")
    private Integer currency;

    @Schema(title = " نوع چك")
    private Integer chequeType;

    @Schema(title = " ١ :چك كاغذي  ٢ :چك ديجيتال")
    private Integer chequeMedia;

    @Schema(title = "وضعيت چك")
    private Integer chequeStatus;

    @Schema(title = " وضعيت ضمانت چك")
    private Integer guaranteeStatus;

    @Schema(title = "وضعیت مسدودی")
    private Integer blockStatus;

    @Schema(title = "ایا چک بسته شده است")
    private Integer locked;

    @Schema(title = "ایا ذینفع دیگری دارد")
    private Boolean shared;


}
