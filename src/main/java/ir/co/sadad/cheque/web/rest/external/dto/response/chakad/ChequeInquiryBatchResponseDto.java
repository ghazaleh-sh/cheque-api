package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * response of batch inquiry
 */

@Data
public class ChequeInquiryBatchResponseDto {

    @Accessors(fluent = true)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean isSuccess;

    private Integer totalResultCount;

    private List<String> errorCodes;

    private List<ChequeInquiryBatchDataResponseDto> Result;

    public void isSuccess(boolean value) {
        this.isSuccess = value;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
