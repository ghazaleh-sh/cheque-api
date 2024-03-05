package ir.co.sadad.cheque.domain.dto.v2;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.co.sadad.cheque.domain.enums.PichakRequestStatus;
import ir.co.sadad.cheque.domain.enums.RequestInputType;
import lombok.Data;

@Schema(title = "اطلاعات درخواست دسته چک")
@Data
public class PichakReportResponseDto {
    private Integer sheetCount;
    private String requestDate;
    private PichakRequestStatus requestStatus;
    private String localizedRequestStatus;
    private String updateDate;
    private RequestInputType requestInput;
    private String chequeNumber;
}
