package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.math.BigInteger;

@Schema(title = "آبجکت درخواست استعلام برگه چک")
@Data
public class SayadChequeInquirySheetRequestDto {


    @Schema(title = "شناسه صیاد")
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    private String sayadChequeId;


    @Schema(title = "شماره حساب")
    @Size(min = 13, max = 13, message = "chakad.error.sayad.account.number.length.invalid")
    private String account;


    @Schema(title = "سری و سریال چک")
    @Size(min = 10, max = 10, message = "chakad.error.sayad.cheque.id.length.invalid ")
    private String chequeNumber;


    @Schema(title = "کد درخواست دسته چک")
    private BigInteger chequeIssueId;

    @Schema(title = "نوع آرشیو")
    private Integer archiveType;


}
