package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class GuaranteeInquiryResponseDto {

    private Date inquiryDate;
    private String identifier;
    private List<GuarantorGuaranteesListDto> guarantorGuaranteesList;
}
