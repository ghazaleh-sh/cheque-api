package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

@Data
public class SignedDataRequestDto {
    private String transactionId;
    private String signatureValue;
}
