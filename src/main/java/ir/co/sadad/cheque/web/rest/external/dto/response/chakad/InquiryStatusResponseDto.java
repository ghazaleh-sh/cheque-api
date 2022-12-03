package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.List;

/**
 * response for activation status of customers
 *
 * @See Chakad Cheque inquiry status document
 */
@Data
public class InquiryStatusResponseDto {

    /**
     * ليست اطلاعات وضعيت فعالسازي مشتريان
     */
    private List<ActivationStatusDto> activationStatus;
}
