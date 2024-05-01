package ir.co.sadad.cheque.web.rest.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.cheque.domain.dto.SayadChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.service.v2.DashboardServiceV2;
import ir.co.sadad.cheque.service.v2.PichakServiceV2;
import ir.co.sadad.cheque.service.v2.PreDashboardV2Service;
import ir.co.sadad.cheque.web.rest.external.dto.response.chakad.DepositInquiryResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/v2/chakad")
@RequiredArgsConstructor
@Log4j2
@Tag(description = "سرویس های چکاد", name = "Chakad services resources V2 version ")
public class ChakadV2Controller {

    private final PreDashboardV2Service preDashboardV2Service;
    private final DashboardServiceV2 dashboardService;
    private final PichakServiceV2 pichakService;

    @GetMapping("/inquiryStatus")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public InquiryStatusFinalResDto inquiryStatus(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return preDashboardV2Service.inquiryStatus();
    }

    @PostMapping("/activation_tbs")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public TbsActivationResponseDto tsbActivation(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                  @Valid @RequestBody TbsActivationRequestDto activationRequestDto) {
        return preDashboardV2Service.tsbActivation(authToken, activationRequestDto);
    }

    @PatchMapping("/activation_tbs")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public void tsbActivation(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        preDashboardV2Service.tsbActivationUpdate(authToken);
    }

    @PostMapping("/activation")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public SuccessClientResponseDto activation(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                               @Valid @RequestBody ActivationClientRequestDto activationClientRequestDto) {
        return preDashboardV2Service.activation(authToken, activationClientRequestDto);
    }

    @Operation(summary = "سرویس غیرفعالسازی پروفایل")
    @DeleteMapping("/deactivation")
    public DeactivationResponseDto deactivation(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return preDashboardV2Service.deactivation(authToken);
    }

    @Operation(summary = "سرویس دریافت گواهی معتبر کاربر")
    @GetMapping("/userCertification")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public UserCertificationResponseDto userCertification(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return dashboardService.userCertification(authToken);
    }


    @Operation(summary = "سرویس درخواست صدور چک")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = IssueRequestResDto.class)))
    @PostMapping("/cheque_issued_request")
    public IssueRequestResDto issueRequest(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                           @Valid @RequestBody IssueRequestReqDto issueRequestReq) {

        return dashboardService.requestIssue(issueRequestReq, authToken);
    }

    @Operation(summary = "سرویس صدور چک")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ChequeIssueResponseDto.class)))
    @PostMapping("/cheque_issued")
    public ChequeIssueResponseDto issue(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                        @Valid @RequestBody ChequeIssueRequestDto issueReq) {

        return dashboardService.issue(issueReq, authToken);
    }

    @Operation(summary = "سرویس کارتابل")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ChequeCartableDto.class)))
    @GetMapping("/digitalcheque_cartable")
    public List<ChequeCartableDto> cartable(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken) {
        return dashboardService.cartable(authToken);

    }

    @Operation(summary = "سرویس درخواست دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SuccessClientResponseDto.class)))
    @PostMapping("/request")
    public SuccessClientResponseDto chequeRequest(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                  @Valid @RequestBody PichakRequestChequeReqDto requestChequeReqDto) {
        return pichakService.requestCheque(requestChequeReqDto, authToken);
    }

    @Operation(summary = "سرویس پیگیری درخواست دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = PichakReportResponseDto.class)))
    @GetMapping("/{iban}/inquiry")
    public List<PichakReportResponseDto> chequeReport(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                      @PathVariable String iban) {
        return pichakService.reportCheque(iban, authToken);
    }

    @Operation(summary = "سرویس تایید/ رد چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SuccessClientResponseDto.class)))
    @PatchMapping("/{sayadId}/status")
    public SuccessClientResponseDto acceptance(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                               @PathVariable String sayadId,
                                               @Valid @RequestBody AcceptanceRequestDto acceptanceRequestDto) {
        return dashboardService.acceptance(sayadId, acceptanceRequestDto, authToken);
    }

    @Operation(summary = "سرویس انتقال/ عودت چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = TransferResponseDto.class)))
    @PostMapping("/transfer")
    public TransferResponseDto transfer(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                        @RequestHeader(name = "authorizationCode") String authorizationCode,
                                        @Valid @RequestBody TransferRequestDto transferRequestDto) {
        return dashboardService.transfer(transferRequestDto, authorizationCode, authToken);
    }

    @Operation(summary = "سرویس استعلام دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = PichakReportResponseDto.class)))
    @GetMapping("/{account}/ChequeBookInquiry")
    public List<SayadChequeInquiryBatchResponseDto> ChequeBookInquiry(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                      @PathVariable String account) {
        return pichakService.ChequeBookInquiry(account, authToken);
    }

    @Operation(summary = "سرویس استعلام برگه چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = PichakLeafInquiryResponseDto.class)))
    @GetMapping("/{account}/{chequeIssuedId}/ChequeLeafInquiry")
    public List<PichakLeafInquiryResponseDto> ChequeLeafInquiry(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                                @PathVariable String account,
                                                                @PathVariable Long chequeIssuedId) {
        return pichakService.ChequeLeafInquiry(chequeIssuedId, account, authToken);
    }

    @Operation(summary = "سرویس واگذاری چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SuccessClientResponseDto.class)))
    @PostMapping("/depositregister")
    public SuccessClientResponseDto depositRegister(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                    @Valid @RequestBody DepositRegisterRequestDto depositRegisterRequestDto) {
        return dashboardService.depositRegister(depositRegisterRequestDto, authToken);
    }

    @Operation(summary = "سرویس لغو واگذاری چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SuccessClientResponseDto.class)))
    @DeleteMapping("/deposit")
    public SuccessClientResponseDto depositCancel(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                  @Valid @RequestParam(name = "sayadId")
                                                  @NotBlank @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid") String sayadId) {
        return dashboardService.depositCancel(sayadId, authToken);
    }

    @Operation(summary = "سرویس استعلام واگذاری چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = DepositInquiryResponseDto.class)))
    @GetMapping("/depositinquiry")
    public DepositInquiryResponseDto depositInquiry(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authToken,
                                                    @Valid @RequestParam(name = "sayadId")
                                                    @NotBlank @Size(min = 16, max = 16, message = "chakad.error.sayad.sayad.id.length.invalid") String sayadId) {
        return dashboardService.depositInquiry(sayadId, authToken);
    }
}
