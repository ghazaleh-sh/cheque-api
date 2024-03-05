package ir.co.sadad.cheque.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.co.sadad.cheque.domain.enums.ActivationResponseStatus;
import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import ir.co.sadad.cheque.domain.enums.RequestType;
import ir.co.sadad.cheque.domain.enums.TokenType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * entity for manage info of user , this table will save after call challenge code service
 * <pre>
 *     for other service of chakad that uses this infos , this table created . contains user info such as
 *     name , phone , ssn and cheque infos
 * </pre>
 */
@Entity
@Table(name = "CHEQUE_USER_INFO", schema = "BMI_ACCOUNT")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ChequeUserInfo {

//    protected static final Timestamp DELETE_AT = Timestamp.valueOf("1970-01-01 00:00:00.0");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ID_TYPE")
    @Enumerated(EnumType.STRING)
    private CustomerIdType idType;

    @Column(name = "SHAHAB_ID", columnDefinition = "char(16)", length = 16)
    private String shahabId;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "TOKEN_TYPE")
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(name = "CHALLENGE_CODE")
    private Integer challengeCode;

    @Column(name = "ACTIVATION_TICKET")
    private String activationTicket;

    @Column(name = "ORGANIZATION_ID_CODE")
    private String organizationIdCode;

    @Column(name = "ORGANIZATION_ID_TYPE")
    @Enumerated(EnumType.STRING)
    private CustomerIdType organizationIdType;

    @Column(name = "ORGANIZATION_SHAHAB_ID", columnDefinition = "char(16)", length = 16)
    private String organizationShahabId;

    @Column(name = "REQUEST_TYPE")
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "REQUEST_DATE_TIME")
//    @Temporal(TemporalType.TIMESTAMP)
    private String requestDateTime;

    @Column(name = "ACTIVATION_RESPONSE_STATUS")
    @Enumerated(EnumType.STRING)
    private ActivationResponseStatus activationResponseStatus;

    @Column(name = "CERTIFICATE_KEY_ID")
    private String certificateKeyId;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "CREATE_HTTP_STATUS")
    private Integer createHttpStatus;

    @Column(name = "SIGN_HTTP_STATUS")
    private Integer signHttpStatus;

    @CreatedBy
    @Column(name = "CREATED_BY", nullable = false, updatable = false, columnDefinition = "char(15)", length = 15)
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATION_DATE_TIME", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY", nullable = false, columnDefinition = "char(15)", length = 15)
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "MODIFIED_DATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedDateTime;

    @JsonIgnore
    @Version
    @Column(name = "OPT_LOCK", nullable = false, columnDefinition = "integer DEFAULT 0")
    private Long version = 0L;
}
