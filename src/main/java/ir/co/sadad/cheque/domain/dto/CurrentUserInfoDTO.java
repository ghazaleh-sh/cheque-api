package ir.co.sadad.cheque.domain.dto;

import ir.bmi.identity.client.security.model.BmiOAuth2User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentUserInfoDTO {

    private BmiOAuth2User bmiOAuth2User;
    private String accessToken;
}
