//package ir.co.sadad.cheque.domain.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "CHEQUE_SIGNER")
//@Getter
//@Setter
//public class ChequeSigner {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "NAME")
//    private String name;
//
//    @Column(name = "IDENTIFIER", length = 15, nullable = false)
//    private String identifier;
//
//    @Column(name = "IDENTIFIER_TYPE", nullable = false)
//    private String identifierType;
//
////    @Column(name = "LEGAL_STAMP")
////    private String legalStamp;
////
////    @Column(name = "AGENT")
////    private String agent;
////
////    @Column(name = "GRANTER_TYPE")
////    private String granterType;
////
////    @Column(name = "GRANTER_IDENTIFIER")
////    private String granterIdentifier;
////
////    @Column(name = "GRANTER_NAME")
////    private String granterName;
//
////    @ManyToOne
////    @JoinColumn(name = "ISSUE_ID", foreignKey = @ForeignKey(name = "FKSIGNER_TO_ISSUE"))
////    private ChequeIssue chequeIssue;
////
////    @ManyToOne
////    @JoinColumn(name = "TRANSFER_ID", foreignKey = @ForeignKey(name = "FKSIGNER_TO_TRANSFER"))
////    private ChequeTransfer chequeTransfer;
//
//}
