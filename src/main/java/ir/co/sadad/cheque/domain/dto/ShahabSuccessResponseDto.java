package ir.co.sadad.cheque.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.co.sadad.cheque.web.rest.external.dto.response.shahab.PersonInfo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShahabSuccessResponseDto {

//    private String trackCode;
//    private PersonInfo personInfo;
    private String shahabCode;
}
