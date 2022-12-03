package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

import java.util.List;

/**
 * request for inquiry status of chakad services
 *
 * @See Chakad Cheque inquiry status document
 */
@Data
public class InquiryStatusRequestDto {
    /**
     * ليست اطلاعات مشتريان
     * require
     */
    private List<CustomerRequestDto> customers;
}
