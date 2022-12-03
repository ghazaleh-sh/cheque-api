package ir.co.sadad.cheque.web.rest.external.dto.response.bam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDto<T> {

    private ResultSetDto<T> resultSet;
    private Object metaData;
}
