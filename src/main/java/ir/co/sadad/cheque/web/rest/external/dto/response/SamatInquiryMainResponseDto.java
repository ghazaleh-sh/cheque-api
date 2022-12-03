package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class SamatInquiryMainResponseDto {

    private String inquiryDate;
    private String inquiryIdentifier;
    private String customerType;
    private String nationalIdentifier;
    private String legalIdentifier;
    private String individualFirstName;
    private String individualLastNameOrLegalName;
    private String birthCountryCode;
    private String badCreditDate;
    private String inquiryResponseIdentifier;
    private List<SamatInquiryEntryDto> inquiryEntries;
}
