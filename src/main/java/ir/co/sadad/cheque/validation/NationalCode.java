package ir.co.sadad.cheque.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
//@Repeatable(NationalCode.List.class)
@Documented
@Constraint(validatedBy = {NationalCodeValidator.class})
public @interface NationalCode {

    String message() default "error.national.code.not.valid";

    String messageNotBlank() default "error.national.code.is.mandatory";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value() default "";

//    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
//    @Retention(RUNTIME)
//    @Documented
//    @interface List {
//        NationalCode[] value();
//    }
}
