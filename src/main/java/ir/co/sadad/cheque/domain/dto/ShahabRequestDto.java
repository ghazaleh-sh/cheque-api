package ir.co.sadad.cheque.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
//@AllArgsConstructor
public class ShahabRequestDto {

    @NotNull(message = "error.national.code.is.mandatory")
    private String nationalCode;

    @NotNull(message = "shahab.error.birth.date.is.mandatory")
    private String birthDate;

    @NotNull(message = "shahab.error.issue.number.is.mandatory")
    private String issueNumber;

    private String birthPlace;

    private String postalCode;
}
