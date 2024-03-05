package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.List;

@Data
public class CreateSignatureResBodyDto {

    private String transactionId;
    private List<Integer> transactionDate;
}
