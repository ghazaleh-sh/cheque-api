package ir.co.sadad.cheque.validation;

import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChequeReasonValidator implements ConstraintValidator<ChequeReason, Object> {

    private String reason;
    private String amount;
    private String messageNotBlank;

    @Override
    public void initialize(ChequeReason constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        reason = constraintAnnotation.value()[0];
        amount = constraintAnnotation.value()[1];
        messageNotBlank = constraintAnnotation.messageNotBlank();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        try {
            final String reasonCode = (String) PropertyUtils.getProperty(o, reason);
            final String amountValue = (String) PropertyUtils.getProperty(o, this.amount);

            if (Integer.parseInt(amountValue) >= 100000000)
                return reasonCode != null && !reasonCode.isEmpty();
            return true;
        } catch (final Exception e) {
            return false;
        }
    }
}
