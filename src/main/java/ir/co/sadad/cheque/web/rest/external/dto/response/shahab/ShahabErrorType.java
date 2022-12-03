package ir.co.sadad.cheque.web.rest.external.dto.response.shahab;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShahabErrorType {

    private String code;
    private String message;
    private Date timestamp;
    private Integer domain;
    private List<ShahabSubErrors> errors;
}
