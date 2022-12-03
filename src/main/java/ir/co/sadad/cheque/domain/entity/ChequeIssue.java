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
@Table(name = "CHEQUE_ISSUE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ChequeIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "SAYAD_ID", columnDefinition = "CHAR(16)", length = 16, nullable = false)
    @NotNull
    private String sayadId;

    @Column(name = "SERIAL_NUMBER", columnDefinition = "VARCHAR(20)")
    private String serialNumber;

    @Column(name = "SERI_NUMBER", columnDefinition = "VARCHAR(30)")
    private String seriNumber;

    @Column(name = "AMOUNT", nullable = false, columnDefinition = "VARCHAR(16)")
    @NotNull
    private String amount;

    @Column(name = "SETTLEMENT_DATE")
    private String settlementDate;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(250 CODEUNITS32)", nullable = false)
    private String description;

    @CreatedDate
    @Column(name = "ISSUE_DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @ManyToOne
    @JoinColumn(name = "REASON_ID", foreignKey = @ForeignKey(name = "FKISSUE_TO_REASON"))
    private ChequeReason chequeReason;
}
