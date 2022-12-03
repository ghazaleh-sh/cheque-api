package ir.co.sadad.cheque.domain.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ChequeData implements Serializable {

    private int sheetCount;
    private long requestDate;
    private long updateTime;
    private String chequeStatus;
    private String requestInput;
    private String chequeNumber;
    private int chequeStatusIndex;
}
