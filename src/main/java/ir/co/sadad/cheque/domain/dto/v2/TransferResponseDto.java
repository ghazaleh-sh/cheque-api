package ir.co.sadad.cheque.domain.dto.v2;

import lombok.Data;

import java.util.List;

@Data
public class TransferResponseDto {
    private List<UserDto> receivers;
    private List<UserDto> signers;
    private String sayadId;
    private String description;
    private String reason;
    private String account;
    private Boolean giveBack;
    private String currentDateTime;

}
