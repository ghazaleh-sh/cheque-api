package ir.co.sadad.cheque.web.rest.external.dto.response.chakad;

import lombok.Data;

import java.util.List;

/**
 * sayad report response
 * <pre>
 *     this response is different from ChakadResponseDto,  'cause  this response has INTEGER for messageCode and other filed
 * </pre>
 */
@Data
public class SayadReportResponseDto {

    /**
     * کد پیام
     */
    private Integer messageCode;

    /**
     * رای اشخاص حقیقی ایرانی کد ملی پر می شود.
     */
    private Integer identifier;

    /**
     * طالعات درخواست های دسته چک
     */
    private List<RequestItemDto> requestItem;
}
