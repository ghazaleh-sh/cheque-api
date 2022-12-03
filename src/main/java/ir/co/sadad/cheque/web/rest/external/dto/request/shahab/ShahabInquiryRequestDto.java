package ir.co.sadad.cheque.web.rest.external.dto.request.shahab;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShahabInquiryRequestDto {

    @NotNull
    private String nationalCode;

    @NotNull
    private String birthDate;

    @NotNull
    private String issueNumber;

    private String birthPlace;
    private String postalCode;

    @NotNull
    private Boolean online;

}
