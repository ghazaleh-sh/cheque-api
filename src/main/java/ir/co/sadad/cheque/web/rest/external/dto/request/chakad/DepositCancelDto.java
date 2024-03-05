package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

@Data
public class DepositCancelDto extends DepositInquiryDto {

    /**
     * شناسه واگذارنده
     */
    private Integer depositId;

}
