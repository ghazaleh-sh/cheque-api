package ir.co.sadad.cheque.domain.dto;

import ir.bmi.identity.client.security.model.BmiSsoAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface AccessTokenProcessor {
    default CurrentUserInfoDTO getCurrentUserInfo() {
        BmiSsoAuthentication authentication = (BmiSsoAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return new CurrentUserInfoDTO(authentication.getPrincipal(), "Bearer " + authentication.getPrincipal().getAccessToken());
    }
}
