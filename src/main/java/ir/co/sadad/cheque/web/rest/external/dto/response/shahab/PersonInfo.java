package ir.co.sadad.cheque.web.rest.external.dto.response.shahab;

import lombok.Data;

@Data
public class PersonInfo {

    private String nationalId;
    private String shahabCode;
    private Boolean isConfirmed;
    private String birthDate;
    private Integer issueNumber;
    private String birthLocation;
    private String name;
    private String familyName;
    private String fatherName;
    private String issueSeries;
    private String gender;
    private String postalAddress;
    private Integer errorCode;
    private String locFlag;
    private String persistDate;
    private String postalCode;

}
