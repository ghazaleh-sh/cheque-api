package ir.co.sadad.cheque.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.service.SayadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sayad")
@RequiredArgsConstructor
@Log4j2
@Tag(description = "سرویس های مربوط به صیاد", name = "Sayad Resources")
public class SayadController {

    private final SayadService sayadService;

    @Operation(summary = "سرویس استعلام رهگیری دسته چک", description = "سرویس مربوط به استعلام درخواست دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeReportResponseDto.class)))
    @PostMapping("/report")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public SayadChequeReportResponseDto sayadReport(
        @Valid @RequestBody SayadChequeReportRequestDto sayadChequeReportRequest) {
        return sayadService.reportIssuedCheque(sayadChequeReportRequest);
    }


    @Operation(summary = "سرویس درخواست دسته چک", description = "سرویس مربوط به درخواست دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeReportResponseDto.class)))
    @PostMapping("/request")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public SayadChequeRequestResDto sayadRequest(
        @Valid @RequestBody SayadChequeRequestDto sayadChequeRequest) {
        return sayadService.requestCheque(sayadChequeRequest);
    }


    @Operation(summary = "سرویس درخواست استعلام دسته چک", description = "سرویس مربوط استعلام دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeInquiryBatchResponseDto.class)))
    @PostMapping("/batch-inquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public SayadChequeInquiryBatchResponseDto batchRequest(
        @Valid @RequestBody SayadChequeInquiryBatchRequestDto sayadChequeInquiryBatchRequest) {
        return sayadService.batchInquiry(sayadChequeInquiryBatchRequest);
    }

    @Operation(summary = "سرویس درخواست استعلام برگ چک", description = "سرویس مربوط به درخواست استعلام برگ چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeInquirySheetResponseDto.class)))
    @PostMapping("/sheet-inquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public SayadChequeInquirySheetResponseDto sheetRequest(
        @Valid @RequestBody SayadChequeInquirySheetRequestDto sayadChequeInquirySheetRequest) {
        return sayadService.sheetInquiry(sayadChequeInquirySheetRequest);
    }

}
