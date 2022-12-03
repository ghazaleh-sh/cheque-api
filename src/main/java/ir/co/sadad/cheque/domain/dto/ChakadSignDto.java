package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.validation.NationalCode;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(title = "آبجکت امضا مشتری")
//@NationalCode(value = {"signerIdCode", "signerIdType"}) TODO: signerIdType must be "String" to use the validation annotation
public class ChakadSignDto {

    @Schema(title = "توکن نماد")
    @NotNull(message = "chakad.error.cms.is.mandatory")
    private String cms;

    @Schema(title = "وضعیت امضا")
    @NotNull(message = "chakad.error.signing.status.is.mandatory")
    private Integer signingStatus;

    @Schema(title = "کد شناسایی امضا کننده")
    @NotNull(message = "chakad.error.signer.id.code.is.mandatory")
    private String signerIdCode;

    @Schema(title = "نوع شخص امضا کننده")
    @NotNull(message = "chakad.error.signer.id.type.is.mandatory")
    private Integer signerIdType;

    @Schema(title = "کد شناسایی نماینده حقوقی امضا کننده")
    @NotNull(message = "chakad.error.signer.organization.id.code.is.mandatory")
    private String signerOrganizationIdCode;

    @Schema(title = "نوع شخص نماینده حقوقی امضا کننده")
    @NotNull(message = "chakad.error.signer.organization.id.type.is.mandatory")
    private Integer signerOrganizationIdType;

    @Schema(title = "تاریخ ثبت امضا")
    @Size(min = 14, max = 14, message = "chakad.error.sign.date.length.invalid")
    private String signDate;

    @NotNull(message = "chakad.error.legal.stamp.is.mandatory")
    private Integer legalStamp;

}
