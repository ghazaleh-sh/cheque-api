package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeactivationResponseDto {

    private String certificateKeyId;
}
