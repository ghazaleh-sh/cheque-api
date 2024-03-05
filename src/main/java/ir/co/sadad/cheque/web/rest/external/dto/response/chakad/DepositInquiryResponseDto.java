package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * response of deposit inquiry service
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositInquiryResponseDto {
    private boolean succeeded;
    private String code;
    private String message;
    private List<ChakadErrorResponseDto.ErrorDetails> errorDetails;
    private List<Data> data;

    @lombok.Data
    public static class ErrorDetails {
        private String code;
        private String message;
    }

    @lombok.Data
    public static class Data {
        /**
         * شناسه صیادی
         */
        private String sayadNo;

        /**
         * کد ملی/شناسه اتباع واگذارنده
         */
        private String holderIdentifier;

        /**
         * نام واگذارنده
         */
        private String holderName;

        /**
         * شناسه واگذاری
         */
        private Integer depositId;

        /**
         * وضعیت واگذاری
         */
        private String depositStatus;

        /**
         * وضعیت وصول چک
         */
        private String chequeStatus;

        /**
         * تاریخ ثبت واگذاری
         * با فرمت yyyymmdd
         */
        private String requestDate;

        /**
         * شماره حساب واگذارنده
         */
        private Integer accountNumber;

        /**
         * تاریخ مبادله
         * با فرمت yyyymmdd
         */
        private String depositDate;

        /**
         * مبلغ وصول شده
         */
        private Integer amount;

        /**
         * پاسخ چکاوک
         */
        private String messageStatus;

        /**
         * علت رد چکاوک
         */
        private String messageReasonText;

        /**
         * تاریخ برگشت
         * با فرمت yyyymmdd
         */
        private String returnDate;

        /**
         * شناسه چک برگشتی
         */
        private String returnCode;

    }

}
