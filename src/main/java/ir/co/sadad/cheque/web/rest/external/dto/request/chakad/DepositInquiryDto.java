package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * dto for inquiring deposit
 */
@Data
public class DepositInquiryDto {

    /**
     * شناسه صیاد ( 16 رقمی)
     */
    private String sayadId;

    /**
     * نوع کد شناسایی واگذارنده
     * 1: حقیقی ایرانی
     * 3:حقیقی اتباع
     */
    private Integer holderIdentifierType;

    /**
     * کد ملی/شناسه اتباع  واگذارنده
     * طبق الگوی کد/شناسه ملی نوع کد شناسایی
     */
    private String holderIdentifier;

}
