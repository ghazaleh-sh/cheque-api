package ir.co.sadad.cheque.validation;


import ir.co.sadad.cheque.domain.enums.CustomerIdType;
import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NationalCodeValidator implements ConstraintValidator<NationalCode, Object> {

    private String identifier;
    private String type;
    private String messageNotBlank;


    @Override
    public void initialize(final NationalCode constraintAnnotation) {
        identifier = constraintAnnotation.value()[0];
        type = constraintAnnotation.value()[1];
        messageNotBlank = constraintAnnotation.messageNotBlank();

    }

    @Override
    public boolean isValid(final Object o, final ConstraintValidatorContext context) {
        try {
            final String identifierCode = (String) PropertyUtils.getProperty(o, identifier);
            final String idType = (String) PropertyUtils.getProperty(o, this.type);

            if (identifierCode == null || identifierCode.equals("")) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(messageNotBlank).addConstraintViolation();
                return false;
            }

            if (idType.equals(CustomerIdType.INDIVIDUAL.getKey()))
                return checkMelliCode(identifierCode);

            if (idType.equals(CustomerIdType.CORPORATE.getKey()) || idType.equals(CustomerIdType.SHAHAB_CORPORATE.getKey()))
                return identifierCode.length() == 11;

            if (idType.equals(CustomerIdType.FOREIGN_INDIVIDUAL.getKey()) ||
                idType.equals(CustomerIdType.FOREIGN_CORPORATE.getKey()) ||
                idType.equals(CustomerIdType.SHAHAB_FOREIGN_INDIVIDUAL.getKey()))
                return identifierCode.length() >= 8 && identifierCode.length() <= 15;

            return false;
        } catch (final Exception e) {
//            throw new IllegalArgumentException(e);
            return false;
        }
    }

    private Boolean checkMelliCode(String nationalCode) {


        if (nationalCode.length() != 10) {
            return false;
        }
        if (!nationalCode.matches("^\\d{10}$")) {
            return false;
        }

        int sum = 0;
        int lenght = 10;
        for (int i = 0; i < lenght - 1; i++) {
            sum += Integer.parseInt(String.valueOf(nationalCode.charAt(i))) * (lenght - i);
        }

        int r = Integer.parseInt(String.valueOf(nationalCode.charAt(9)));

        int c = sum % 11;

        return (((c < 2) && (r == c)) || ((c >= 2) && ((11 - c) == r)));
    }
}
