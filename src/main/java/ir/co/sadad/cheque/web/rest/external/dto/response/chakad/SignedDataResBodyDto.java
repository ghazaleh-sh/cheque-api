package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.List;

@Data
public class SignedDataResBodyDto {

    private String transactionId;
    private String username;
    private String dataType;
    private String transactionStatus;
    private String verifyStatus;
    private List<Integer> responseDate;
    private String description;
    private String signedDocumentTicket;
    private String signedDocumentUrl;

}
