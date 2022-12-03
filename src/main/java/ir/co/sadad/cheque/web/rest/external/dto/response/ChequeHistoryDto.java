package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class ChequeHistoryDto {

    private Integer id;
    private ChequebookResponseDto chequeBook;
    private String chequeNo;
    private BigDecimal amount;
    private Date issueDate;
    private Date creationDateTime;
}
