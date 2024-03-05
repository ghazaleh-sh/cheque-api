package ir.co.sadad.cheque.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "CHEQUE_TRANSFER", schema = "BMI_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ChequeTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "SAYAD_ID", columnDefinition = "CHAR(16)", length = 16, nullable = false)
    private String sayadId;

//    @Column(name = "ACCEPT_TRANSFER")
//    private Boolean acceptTransfer;

    @Column(name = "GIVE_BACK")
    private Boolean giveBack;

//    @Column(name = "IDENTIFIER", length = 15, nullable = false)
//    private String identifier;
//
//    @Column(name = "IDENTIFIER_TYPE", nullable = false)
//    private String identifierType;
//
//    @Column(name = "ACCOUNT", columnDefinition = "VARCHAR(13)")
//    private String account;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(250 CODEUNITS32)", nullable = false)
    private String description;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "TBS")
    private String tbs;

    @Column(name = "SIGNING_STATUS")
    private String signingStatus;

    @Column(name = "CERTIFICATION_KEYID")
    private String certificationKeyId;

    @Column(name = "SIGNATURE_VALUE")
    private String signatureValue;

    @ManyToOne
    @JoinColumn(name = "REASON_ID", foreignKey = @ForeignKey(name = "FKTRANSFER_TO_REASON"))
    private ChequeReason chequeReason;

    @CreatedDate
    @Column(name = "TRANSFER_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferDate;

    @ManyToOne
    @JoinColumn(name = "ISSUE_ID", foreignKey = @ForeignKey(name = "FKTRANSFER_TO_ISSUE"))
    private ChequeIssue chequeIssue;

}
