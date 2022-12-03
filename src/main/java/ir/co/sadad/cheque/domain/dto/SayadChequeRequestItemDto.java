package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "اطلاعات درخواست دسته چک")
@Data
public class SayadChequeRequestItemDto {

    @Schema(title = "تعداد برگه چک - 1 میشه 25 برگی - 2 میشه 50 برگی - 3 100 برگی")
    private Integer sheetCount;

    @Schema(title = "تاریخ ثبت درخواست")
    private Integer requestDate;

    @Schema(title = "وضعیت درخواست ", description = "1 )ثبت درخواست" +
        " 2 )رد درخواست توسط شعبه" +
        "3 )عدم صالحیت –دارای چک برگشتی" +
        "4 )عدم صالحیت –دارای بدهی معوق" +
        "5 )عدم صالحیت –دارای ممنوعیت قضایی" +
        " 6 )عدم صالحیت –دارای دسته چک از حساب دیگر" +
        " 7 )تایید درخواست" +
        " 8 )چاپ دسته چک" +
        "9 )منتظر مراجعه مشتری به شعبه )در حال حاضر غیرفعال(")
    private Integer requestStatus;

    @Schema(title = " تاریخ آخرین وضعیت رکورد را نمایش می دهد")
    private Integer updateDate;


    @Schema(title = "ورودی درخواست", description = " 1 )شعبه" +
        " 2 )بام")
    private Integer requestInput;


    @Schema(title = " شماره چک )از-تا(", description = "درصورتیکه وضعیت درخواست\"چاپ دسته چک\" باشد، اولین و آخرین شماره برگه" +
        "چک اعالم می گردد. بعنوان نمونه 9920330575-9")
    private String chequeNumber;


    @Schema(title = "نوع دسته چک", description = " 1 )کاغذی" +
        " 2 )دیجیتا")
    private Integer mediaType;
}
