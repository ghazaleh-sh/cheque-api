package ir.co.sadad.cheque.web.rest.external.dto.response;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class AllocatingEstelamResponseDto implements Serializable {

    private int resultCount;
    private int messageCode;
    private List<AllocatingEstelamDataDto> data;
}
