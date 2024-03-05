package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerAccountResponseDto {
    @JsonProperty("SSN")
    private String SSN;
    @JsonProperty("FirstName")
    private String FirstName;
    @JsonProperty("LastName")
    private String LastName;
    @JsonProperty("EngFirstName")
    private String EngFirstName;
    @JsonProperty("EngLastName")
    private String EngLastName;
    @JsonProperty("FatherName")
    private String FatherName;
    @JsonProperty("IdNo")
    private String IdNo;
    @JsonProperty("IdSeries")
    private String IdSeries;
    @JsonProperty("IdSerial")
    private String IdSerial;
    @JsonProperty("TypeID")
    private String TypeID;
    @JsonProperty("SubTypeID")
    private String SubTypeID;
    @JsonProperty("RegisterDate")
    private String RegisterDate;
    @JsonProperty("Gender")
    private Boolean Gender;
    @JsonProperty("MaritalStatusID")
    private Number MaritalStatusID;
    @JsonProperty("BirthDate")
    private String BirthDate;
    @JsonProperty("BirthCity")
    private String BirthCity;
    @JsonProperty("IssuePlace")
    private String IssuePlace;
    @JsonProperty("Address")
    private String Address;
    @JsonProperty("PostCode")
    private String PostCode;
    @JsonProperty("TelHome")
    private String TelHome;
    @JsonProperty("TelWork")
    private String TelWork;
    @JsonProperty("Mobile")
    private String Mobile;
    @JsonProperty("Fax")
    private String Fax;
    @JsonProperty("Email")
    private String Email;
    @JsonProperty("SabtConfirm")
    private String SabtConfirm;
}
