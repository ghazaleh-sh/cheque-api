package ir.co.sadad.cheque.domain.entity;

import ir.co.sadad.cheque.domain.enums.ChequeAccountType;
import ir.co.sadad.cheque.domain.enums.ChequeStatus;
import ir.co.sadad.cheque.domain.enums.RequestInputType;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cheque")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Cheque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 10)
    @Column(name = "account_id")
    private String accountId;

    @Size(min = 2)
    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "cheque_account_type")
    @Enumerated(EnumType.STRING)
    private ChequeAccountType chequeAccountType;

    @Column(name = "cheque_status")
    @Enumerated(EnumType.STRING)
    private ChequeStatus chequeStatus;

    @Size(min = 10)
    @Column(name = "iban")
    private String iban;

    @Column(name = "register_date_time")
    //    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDateTime;

    @Column(name = "last_update_date_time")
    //    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDateTime;

    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "request_input_type")
    @Enumerated(EnumType.STRING)
    private RequestInputType requestInputType;

    @Column(name = "cheque_first_number")
    private String chequeFirstNumber;

    @Column(name = "cheque_last_number")
    private String chequeLastNumber;
    //
    //    public Long getId() {
    //        return this.id;
    //    }
    //
    //    public Cheque id(Long id) {
    //        this.setId(id);
    //        return this;
    //    }
    //
    //    public void setId(Long id) {
    //        this.id = id;
    //    }
    //
    //    public String getAccountId() {
    //        return this.accountId;
    //    }
    //
    //    public Cheque accountId(String accountId) {
    //        this.setAccountId(accountId);
    //        return this;
    //    }
    //
    //    public void setAccountId(String accountId) {
    //        this.accountId = accountId;
    //    }
    //
    //    public String getBranchCode() {
    //        return this.branchCode;
    //    }
    //
    //    public Cheque branchCode(String branchCode) {
    //        this.setBranchCode(branchCode);
    //        return this;
    //    }
    //
    //    public void setBranchCode(String branchCode) {
    //        this.branchCode = branchCode;
    //    }
    //
    //    public String getChequeAccountType() {
    //        return this.chequeAccountType;
    //    }
    //
    //    public Cheque chequeAccountType(String chequeAccountType) {
    //        this.setChequeAccountType(chequeAccountType);
    //        return this;
    //    }
    //
    //    public void setChequeAccountType(String chequeAccountType) {
    //        this.chequeAccountType = chequeAccountType;
    //    }
    //
    //    public String getChequeStatus() {
    //        return this.chequeStatus;
    //    }
    //
    //    public Cheque chequeStatus(String chequeStatus) {
    //        this.setChequeStatus(chequeStatus);
    //        return this;
    //    }
    //
    //    public void setChequeStatus(String chequeStatus) {
    //        this.chequeStatus = chequeStatus;
    //    }
    //
    //    public String getIban() {
    //        return this.iban;
    //    }
    //
    //    public Cheque iban(String iban) {
    //        this.setIban(iban);
    //        return this;
    //    }
    //
    //    public void setIban(String iban) {
    //        this.iban = iban;
    //    }
    //
    //    public Instant getRegisterDateTime() {
    //        return this.registerDateTime;
    //    }
    //
    //    public Cheque registerDateTime(Instant registerDateTime) {
    //        this.setRegisterDateTime(registerDateTime);
    //        return this;
    //    }
    //
    //    public void setRegisterDateTime(Instant registerDateTime) {
    //        this.registerDateTime = registerDateTime;
    //    }
    //
    //    public Instant getLastUpdateDateTime() {
    //        return this.lastUpdateDateTime;
    //    }
    //
    //    public Cheque lastUpdateDateTime(Instant lastUpdateDateTime) {
    //        this.setLastUpdateDateTime(lastUpdateDateTime);
    //        return this;
    //    }
    //
    //    public void setLastUpdateDateTime(Instant lastUpdateDateTime) {
    //        this.lastUpdateDateTime = lastUpdateDateTime;
    //    }
    //
    //    public String getNationalCode() {
    //        return this.nationalCode;
    //    }
    //
    //    public Cheque nationalCode(String nationalCode) {
    //        this.setNationalCode(nationalCode);
    //        return this;
    //    }
    //
    //    public void setNationalCode(String nationalCode) {
    //        this.nationalCode = nationalCode;
    //    }
    //
    //    public String getRequestInputType() {
    //        return this.requestInputType;
    //    }
    //
    //    public Cheque requestInputType(String requestInputType) {
    //        this.setRequestInputType(requestInputType);
    //        return this;
    //    }
    //
    //    public void setRequestInputType(String requestInputType) {
    //        this.requestInputType = requestInputType;
    //    }
    //
    //    public String getChequeFirstNumber() {
    //        return this.chequeFirstNumber;
    //    }
    //
    //    public Cheque chequeFirstNumber(String chequeFirstNumber) {
    //        this.setChequeFirstNumber(chequeFirstNumber);
    //        return this;
    //    }
    //
    //    public void setChequeFirstNumber(String chequeFirstNumber) {
    //        this.chequeFirstNumber = chequeFirstNumber;
    //    }
    //
    //    public String getChequeLastNumber() {
    //        return this.chequeLastNumber;
    //    }
    //
    //    public Cheque chequeLastNumber(String chequeLastNumber) {
    //        this.setChequeLastNumber(chequeLastNumber);
    //        return this;
    //    }
    //
    //    public void setChequeLastNumber(String chequeLastNumber) {
    //        this.chequeLastNumber = chequeLastNumber;
    //    }
    //
    //

    //    @Override
    //    public boolean equals(Object o) {
    //        if (this == o) {
    //            return true;
    //        }
    //        if (!(o instanceof Cheque)) {
    //            return false;
    //        }
    //        return id != null && id.equals(((Cheque) o).id);
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    //        return getClass().hashCode();
    //    }

    // prettier-ignore

    //    @Override
    //    public String toString() {
    //        return "Cheque{" +
    //            "id=" + id ;
    //            ", accountId='" + getAccountId() + "'" +
    //            ", branchCode='" + getBranchCode() + "'" +
    //            ", chequeAccountType='" + getChequeAccountType() + "'" +
    //            ", chequeStatus='" + getChequeStatus() + "'" +
    //            ", iban='" + getIban() + "'" +
    //            ", registerDateTime='" + getRegisterDateTime() + "'" +
    //            ", lastUpdateDateTime='" + getLastUpdateDateTime() + "'" +
    //            ", nationalCode='" + getNationalCode() + "'" +
    //            ", requestInputType='" + getRequestInputType() + "'" +
    //            ", chequeFirstNumber='" + getChequeFirstNumber() + "'" +
    //            ", chequeLastNumber='" + getChequeLastNumber() + "'" +
    //            "}";
    //    }

}
