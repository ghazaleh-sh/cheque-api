package ir.co.sadad.cheque.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(title = "خروجی سرویس استعلام درخواست دسته چک")
@Data
public class SayadChequeReportResponseDto {

    @Schema(title = "کد شناسایی شخص درخواست دهنده- برای اشخصاص ایرانی پر میشود")
    private Integer identifier;

    /**
     * طالعات درخواست های دسته چک
     */
    @Schema(title = "اطلاعات درخواست")
    private List<SayadChequeRequestItemDto> data;
}
