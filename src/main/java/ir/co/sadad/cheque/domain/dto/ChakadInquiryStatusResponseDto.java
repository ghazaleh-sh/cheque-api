package ir.co.sadad.cheque.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChakadInquiryStatusResponseDto {
    /**
     * ليست اطلاعات وضعيت فعالسازي مشتريان
     */
    private List<InquiryActivationStatusDto> activationStatus;
}
