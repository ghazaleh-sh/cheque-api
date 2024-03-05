package ir.co.sadad.cheque.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.enums.ReasonType;
import ir.co.sadad.cheque.service.DashboardService;
import ir.co.sadad.cheque.service.PreDashboardService;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.ChakadResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chakad")
@RequiredArgsConstructor
@Log4j2
@Tag(description = "سرویس های چکاد", name = "Chakad services resources")
public class ChakadController {

    private final PreDashboardService preDashboardService;
    private final DashboardService dashboardService;

    @PostMapping("/activation")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> ActivationRequest(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                        @Valid @RequestBody ChakadActivationRequestDto activationRequestDto) {
        preDashboardService.chakadActivation(authToken, activationRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/deactivation")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> deactivationRequest(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                          @Valid @RequestBody ChakadDeactivationRequestDto chakadDeactivationRequestDto) {
        preDashboardService.chakadDeactivation(authToken, chakadDeactivationRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/inquiryStatus")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public List<InquiryActivationStatusDto> inquiryStatus(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                          @Valid @RequestBody ChakadStatusRequestDto chakadStatusRequestDto) {
        return preDashboardService.chakadInquiryStatus(authToken, chakadStatusRequestDto);
    }


    @Operation(summary = "سرویس کارتابل", description = "سرویس کارتابل که به صورت رپر پیاده سازی شده است")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ChakadCartableResponseDto.class)))
    @PostMapping("/cartable")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<List<ChakadCartableResponseDto>> cartable(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                    @Valid @RequestBody ChakadCartableRequestDto cartableRequest) {
        return new ResponseEntity<>(dashboardService.cartable(authToken, cartableRequest), HttpStatus.OK);

    }


    @Operation(summary = "سرویس بابت", description = "سرویس گرفتن لیست بابت ها")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ReasonResponseDto.class)))
    @GetMapping("/reasons/{type}")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<List<ReasonResponseDto>> getReasons(@PathVariable("type") String type) {
        return new ResponseEntity<>(dashboardService.getReasons(type), HttpStatus.OK);

    }

    @Operation(summary = "سرویس صدور", description = "سرویس کشیدن چک دیجیتال")
    @ApiResponse(responseCode = "204",
        content = @Content(schema = @Schema(implementation = ChakadResponseDto.class)))
    @PostMapping("/issue")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> issue(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                            @Valid @RequestBody ChakadIssueRequestDto issueRequestDto) {
        dashboardService.issue(authToken, issueRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "سرویس انتقال", description = "سرویس انتقال چك ديجيتال")
    @ApiResponse(responseCode = "204",
        content = @Content(schema = @Schema(implementation = ChakadResponseDto.class)))
    @PostMapping("/transfer")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> transfer(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                               @Valid @RequestBody ChakadTransferRequestDto transferRequestDto) {
        dashboardService.transfer(authToken, transferRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "سرویس چلنج کد", description = "سرویس چلنج ديجيتال")
    @ApiResponse(responseCode = "204",
        content = @Content(schema = @Schema(implementation = ChakadChallengeCodeResDto.class)))
    @PostMapping("/challengeCode")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<ChakadChallengeCodeResDto> challengeCode(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                   @Valid @RequestBody ChakadChallengeCodeReqDto chakadChallengeCodeReqDto) {
        return new ResponseEntity<>(preDashboardService.challengeCode(authToken, chakadChallengeCodeReqDto), HttpStatus.OK);
    }

    @Operation(summary = "سرویس تایید چک", description = "سرویس تایید چک توسط گیرنده")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ChakadResponseDto.class)))
    @PostMapping("/accept")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> acceptance(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                 @Valid @RequestBody ChakadAcceptRequestDto acceptRequestDto) {
        dashboardService.acceptance(authToken, acceptRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
