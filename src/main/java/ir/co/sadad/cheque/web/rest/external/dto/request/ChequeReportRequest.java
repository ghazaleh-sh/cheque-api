package ir.co.sadad.cheque.web.rest.external.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChequeReportRequest {

    private String iban;
    private int length;
}
