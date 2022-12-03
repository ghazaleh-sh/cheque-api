package ir.co.sadad.cheque.web.rest.external.dto.request.shahab;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CodeRequestDto {

    //    private CustomerShahabCodeDto customer-shahabCode;
    @NotNull
    private String identifier;

    @NotNull
    private String shahabType;
}
