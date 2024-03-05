package ir.co.sadad.cheque.config;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

/**
 * config for token authorization client
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final Environment environment;

    public AuditorAwareImpl(Environment environment) {
        this.environment = environment;
    }

    /**
     * method for getting current auditor ,
     * <pre>
     *     POINT : in dev profile we bypass this method . and return mock ssn .
     * </pre>
     *
     * @return ssn of bmi Identity Token From Client.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        String auditSSN = null;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = null;
        try {
            httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        } catch (NullPointerException ex) {
            // it doesn't come from http request
            httpServletRequest = null;
        }

        Object objSSn = httpServletRequest.getAttribute("ssn");
        if (objSSn != null) {
            auditSSN = objSSn.toString();
        } else if (Arrays.stream(environment.getActiveProfiles()).anyMatch(
            env -> (env.equalsIgnoreCase("dev")))) {
            auditSSN = "1234567890";
        } else
            auditSSN = "1254";

        return Optional.of(auditSSN);
    }

}
