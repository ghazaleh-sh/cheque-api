package ir.co.sadad.cheque.domain.entity;

import ir.co.sadad.cheque.domain.enums.StakeholderRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CHEQUE_STAKEHOLDER")
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

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private StakeholderRole role;

    @Column(name = "IS_FINAL", columnDefinition = "SMALLINT DEFAULT 0", nullable = false)
    private Boolean isFinal = false;

    @ManyToOne
    @JoinColumn(name = "ISSUE_ID", foreignKey = @ForeignKey(name = "FKSTAKEHOLDER_TO_ISSUE"))
    private ChequeIssue chequeIssue;

    @ManyToOne
    @JoinColumn(name = "TRANSFER_ID", foreignKey = @ForeignKey(name = "FKSTAKEHOLDER_TO_TRANSFER"))
    private ChequeTransfer chequeTransfer;

}
