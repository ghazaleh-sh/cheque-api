package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ChequeReportResponseDto {

    private int messageCode;
    private int identifier;
    private List<RequestItemDto> data;
}
