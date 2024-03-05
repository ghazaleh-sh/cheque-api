package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(title = "ابجکت درخواست استعلام رهگیری درخواست دسته چک")
@Data
public class SayadChequeReportRequestDto {

    @Schema(title = "حساب جاری بانک ملی - شماره شبا")
    @Length(min = 26 , max = 26 , message = "chakad.error.sayad.iban.length.invalid")
    @NotBlank(message = "chakad.error.sayad.iban.is.mandatory")
    private String IBAN;


    @Schema(title = "بازه زمانی - 1 یک ماهه - 2 سه ماهه - 3 شش ماهه - 4 یک ساله")
    @Range(min = 1,max = 4,message = "chakad.error.sayad.length.length.invalid")
    @NotNull(message = "chakad.error.sayad.length.is.mandatory")
    private Integer length;
}
