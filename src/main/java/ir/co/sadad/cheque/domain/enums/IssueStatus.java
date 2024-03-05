package ir.co.sadad.cheque.domain.enums;

import lombok.Getter;

@Getter
public enum IssueStatus {
    //در انتظار امضا
    PENDING,

    //صادر شده
    ISSUED,

    // نقد شده
    CASHED,

    // تایید شده
    APPROVED,

    // رد شده
    REJECTED,

    // دارای خطا
    FAILED;
}
