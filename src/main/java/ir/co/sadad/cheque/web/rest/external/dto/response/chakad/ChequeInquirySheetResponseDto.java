package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * response for sheet
 */
@Data
public class ChequeInquirySheetResponseDto {

    /**
     * وضعیت نتیجه سرویس
     */
    @Accessors(fluent = true)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean isSuccess;

    private List<String> errorCodes;

    private List<ChequeInquirySheetDataResponseDto> result;

    public void isSuccess(boolean value) {
        this.isSuccess = value;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
