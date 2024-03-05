package ir.co.sadad.cheque.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE, TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ChequeReasonValidator.class})
public @interface ChequeReason {

    String message() default "chakad.error.reason.is.mandatory";

    String messageNotBlank() default "chakad.error.reason.is.mandatory";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value() default "";
}
