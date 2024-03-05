package ir.co.sadad.cheque.domain.entity;

import ir.co.sadad.cheque.domain.enums.StakeholderRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CHEQUE_STAKEHOLDER", schema = "BMI_ACCOUNT")
@Getter
@Setter
public class ChequeStakeholder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "SAYAD_ID", columnDefinition = "CHAR(16)", length = 16, nullable = false)
    private String sayadId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTIFIER", length = 15, nullable = false)
    private String identifier;

    @Column(name = "IDENTIFIER_TYPE", nullable = false)
    private String identifierType;

    @Column(name = "AGENT")
    private Integer agent;

    @Column(name = "GRANTOR_IDENTIFIER", length = 15)
    private String grantorIdentifier;

    @Column(name = "GRANTOR_NAME")
    private String grantorName;

    @Column(name = "GRANTOR_TYPE")
    private String grantorType;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StakeholderRole role;

    @Column(name = "CERTIFICATION_KEYID")
    private String certificationKeyId;

    @Column(name = "SHAHAB_ID")
    private String shahabId;

    @CreatedDate
    @Column(name = "Modify_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

//    @Column(name = "IS_FINAL", columnDefinition = "SMALLINT DEFAULT 0", nullable = false)
//    private Boolean isFinal = false;

    @ManyToOne
    @JoinColumn(name = "ISSUE_ID", foreignKey = @ForeignKey(name = "FKSTAKEHOLDER_TO_ISSUE"))
    private ChequeIssue chequeIssue;

    @ManyToOne
    @JoinColumn(name = "TRANSFER_ID", foreignKey = @ForeignKey(name = "FKSTAKEHOLDER_TO_TRANSFER"))
    private ChequeTransfer chequeTransfer;

}
