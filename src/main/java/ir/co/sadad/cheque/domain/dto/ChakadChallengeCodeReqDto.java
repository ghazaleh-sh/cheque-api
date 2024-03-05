package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(title = "ابجکت چلنج کد")
public class ChakadChallengeCodeReqDto {

    @Schema(title = "شناسه سیاد", description = "١ :سرويس فعالسازي\n" +
        "٢ :سرويس به روز رساني گواهي ")
    @NotNull(message = "chakad.error.request.type.is.mandatory")
    private Integer requestType;

    @Schema(title = "کد مشتری")
    @NotNull(message = "chakad.error.code.customer.is.mandatory")
    private String idCodeCustomer;

    @Schema(title = "نوع مشتری")
    @NotNull(message = "chakad.error.type.customer.is.mandatory")
    private String idTypeCustomer;

    @Schema(title = "شناسه شرکت")
    private String idCodeOrganization;

    @Schema(title = "نوع شرکت")
    private Integer idTypeOrganization;

    @Schema(title = "مهر اشخاص حقوقی")
    private Integer legalStamp;

    @Schema(title = "شماره تلفن")
    private String mobileNumber;

//    @Schema(title = "تاریخ درخواست")
//    private String requestDateTime;

    @Schema(title = "در صورتيكه نوع توكن امضاء همراه مبتني بر وب \n" +
        " simless باشد اين پارامتر اجباري مي باشد. ")
    private String simlessIdentifier;

//    @Schema(title = "کد بانک")
//    private String bankCode;
}
