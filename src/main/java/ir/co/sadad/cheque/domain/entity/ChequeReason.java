package ir.co.sadad.cheque.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "CHEQUE_REASON", schema = "BMI_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Getter
@Setter
public class ChequeReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "REASON_TYPE")
    private String reasonType;

    @Column(name = "REASON_CODE")
    private String reasonCode;

    @Column(name = "REASON_TITLE")
    private String reasonTitle;

    @Column(name = "REASON_PRIORITY")
    private Integer priority;

}
