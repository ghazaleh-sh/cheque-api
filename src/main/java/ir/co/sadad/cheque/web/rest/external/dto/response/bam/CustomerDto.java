package ir.co.sadad.cheque.web.rest.external.dto.response.bam;

import java.util.Date;
import lombok.Data;

@Data
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String ssn;
    private String engFirstName;
    private String englastName;
    private String fatherName;
    private String idNo;
    private String idSeries;
    private String idSerial;
    private byte typeID;
    private byte subTypeID;
    private Date registerDate;
    private byte maritalStatusID;
    private boolean gender;
    private Date birthDate;
    private String birthCity;
    private String issuePlace;
    private String address;
    private String postCode;
    private String telHome;
    private String telWork;
    private String mobile;
    private String fax;
    private String email;
    private String sabtConfirm;
    private String cif;
    private String cuTypeID;
    private String saptaMobile;
}
