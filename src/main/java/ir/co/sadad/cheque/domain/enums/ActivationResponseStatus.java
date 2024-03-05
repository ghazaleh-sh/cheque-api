package ir.co.sadad.cheque.domain.enums;

/**
 * response of activation , this is user for permanent activation of user
 */
public enum ActivationResponseStatus {
    /**
     * user is active in chakad
     */
    ACTIVE,
    /**
     * user is not active
     */
    INACTIVE;

    public static ActivationResponseStatus fromInteger(int x) {
        switch(x) {
            case 1:
                return ACTIVE;
            case 2:
                return INACTIVE;
        }
        return null;
    }
}
