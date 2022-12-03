package ir.co.sadad.cheque.web.rest.external.dto.response;

import lombok.Data;

@Data
public class RequestItemDto {

    private int sheetCount;
    private int requestDate;
    private int requestStatus;
    private int updateDate;
    private int requestInput;
    private String chequeNumber;
}
