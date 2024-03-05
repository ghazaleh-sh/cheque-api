package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

/**
 * dto for Registering deposit
 */
@Data
public class DepositRegisterDto {
    /**
     * ماهیت چک
     * 1: کاغذی
     * 2: دیجیتال
     */
    private Integer mediaType;

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

    /**
     * گواهی عدم پرداخت در صورت نیاز صادر شود؟
     * 0: خیر
     * 1: بلی
     */
    private Integer bounceCheque;

    /**
     * شماره حساب واگذارنده (13 رقمی)
     */
    private String holderAccountNumber;

    /**
     * تصویر روی چک
     * در صورتیکه ماهیت چک کاغذی باشد اجباری است
     */
    private Byte frontImg;

    /**
     * تصویر پشت چک
     * در صورتیکه ماهیت چک کاغذی باشد اجباری است
     */
    private Byte backImg;

    /**
     * کد شعبه واگذارنده (7 رقمی)
     * در صورتیکه ماهیت چک کاغذی باشد اجباری است
     */
    private String holderBranchCode;

}
