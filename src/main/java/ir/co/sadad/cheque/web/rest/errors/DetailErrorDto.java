package ir.co.sadad.cheque.web.rest.errors;

import lombok.Builder;
import lombok.Data;

/**
 * model for detail of errors
 */
@Data
@Builder
public class DetailErrorDto {

    private String code;
    private String message;
    private String localizedMessage;


}
