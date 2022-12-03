package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.math.BigInteger;

@Schema(title = "آبجکت درخواست استعلام برگه چک")
@Data
public class SayadChequeInquirySheetRequestDto {


    @Schema(title = "شناسه صیاد")
    @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid")
    private Integer sayadId;


    @Schema(title = "شماره حساب")
    @Length(min = 13, max = 13, message = "chakad.error.sayad.account.number.length.invalid")
    private String account;


    @Schema(title = "سری و سریال چک")
    @Length(min = 10, max = 10, message = "chakad.error.sayad.cheque.id.length.invalid ")
    private String chequeNumber;


    @Schema(title = "کد درخواست دسته چک")
    private BigInteger chequeIssueId;


}
