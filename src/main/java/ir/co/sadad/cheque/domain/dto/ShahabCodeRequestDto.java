package ir.co.sadad.cheque.domain.dto;

import ir.co.sadad.cheque.validation.NationalCode;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@NationalCode(value = {"identifier", "shahabType"})
public class ShahabCodeRequestDto {

    /**
     * رشته ورودی برای کد ملی و کد فیدا و شناسه حقوقی
     */
    @NotNull(message = "error.national.code.is.mandatory")
    private String identifier;

    /**
     * نوع مشتری
     * حقیقی- حقوقی- اتباع بیگانه حقیقی و حقوقی
     */
    @NotNull(message = "shahab.error.type.is.mandatory")
    private String shahabType;
}
