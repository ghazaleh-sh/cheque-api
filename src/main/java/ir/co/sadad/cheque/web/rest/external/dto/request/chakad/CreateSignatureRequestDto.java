package ir.co.sadad.cheque.web.rest.external.dto.request.chakad;

import lombok.Data;

@Data
public class CreateSignatureRequestDto {

    private DataToBeDisplayedDto dataToBeDisplayed;
    private DataToBeSignedDto dataToBeSigned;
    private SignatureCertificateDto signatureCertificate;
    private String digestAlgorithm;
    private String signMechanism;
    private String username;
    private String callBackUrl;
    private String callBackRejectPath;

}
