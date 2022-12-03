package ir.co.sadad.cheque.web.rest.external.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SamatInquiryRequestDto {

    private String inquiryIdentifier;
    private int customerType;
    private String individualFirstName;
    private String individualLastNameOrLegalName;
    private String customerIdentifier;
}
