package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(title = "اطلاعات هر نوع کاربر سامانه", description = "جهت دریافت اطلاعات هر نوع کاربر اعم از دریافت کننده، صادر کننده ، ضامن و امثالهم به کار میرود")
@Data
public class UserDto {
    @Schema(title ="نام یوزر" , required = true)
    @Size(max = 250, message = "chakad.error.signer.name.length.invalid")
    @NotBlank(message = "chakad.error.signer.name.is.mandatory")
    private String name;

    @Schema(title = "کد تعیین هویت یوزر", description = "کد ملی یا شماره ثبت یا کد اتباع" , required = true)
    @NotBlank(message = "chakad.error.signer.id.code.is.mandatory")
    private String identifier;
}
