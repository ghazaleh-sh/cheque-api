package ir.co.sadad.cheque.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.service.PreDashboardService;
import ir.co.sadad.cheque.service.DashboardService;
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
        return preDashboardService.chakadActivation(authToken, activationRequestDto);
    }

    @PostMapping("/deactivation")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> deactivationRequest(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                   @Valid @RequestBody ChakadDeactivationRequestDto chakadDeactivationRequestDto) {
        return preDashboardService.chakadDeactivation(authToken, chakadDeactivationRequestDto);
    }

    @PostMapping("/inquiryStatus")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ChakadInquiryStatusResponseDto inquiryStatus(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                        @Valid @RequestBody ChakadStatusRequestDto chakadStatusRequestDto) {
        return preDashboardService.chakadInquiryStatus(authToken, chakadStatusRequestDto);
    }


    @Operation(summary = "سرویس کارتابل", description = "سرویس کارتابل که به صورت رپر پیاده سازی شده است")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ChakadCartableResponseDto.class)))
    @PostMapping("/cartable")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ChakadCartableResponseDto cartable(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                              @Valid @RequestBody ChakadCartableRequestDto cartableRequest) {
        return dashboardService.cartable(authToken, cartableRequest);
    }


    @Operation(summary = "سرویس بابت", description = "سرویس گرفتن لیست بابت ها")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ReasonResponseDto.class)))
    @GetMapping("/reasons/{type}")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public List<ReasonResponseDto> getReasons(@PathVariable("type") String type) {
        return dashboardService.getReasons(type);
    }

    @Operation(summary = "سرویس صدور", description = "سرویس کشیدن چک دیجیتال")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ChakadResponseDto.class)))
    @PostMapping("/issue")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> issue(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                            @Valid @RequestBody ChakadIssueRequestDto issueRequestDto) {
        return dashboardService.issue(authToken, issueRequestDto);
    }

    @Operation(summary = "سرویس انتقال", description = "سرویس انتقال چك ديجيتال")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ChakadResponseDto.class)))
    @PostMapping("/transfer")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> transfer(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                               @Valid @RequestBody ChakadTransferRequestDto transferRequestDto) {
        return dashboardService.transfer(authToken, transferRequestDto);
    }
}
