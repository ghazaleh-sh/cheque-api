package ir.co.sadad.cheque.domain.enums;

/**
 * determine type of user certification based on NAMAD
 * that whether matched with activated certification on their phone or not.
 */
public enum UserCertificationStatus {
    /**
     * return it when user cid is not equal with user cid in DB
     */
    UPDATE_PROFILE,

    /**
     * return it when user cid is equal with user cid in DB
     */
    MATCHED,

    /**
     * return it when user does not exist in userInfo table- but has valid certificate (700)
     */
    ACTIVATION
}
