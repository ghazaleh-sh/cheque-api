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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sayad")
@RequiredArgsConstructor
@Log4j2
@Tag(description = "سرویس های مربوط به صیاد", name = "Sayad Resources")
public class SayadController {

    private final SayadService sayadService;

    @Operation(summary = "سرویس استعلام رهگیری دسته چک", description = "سرویس مربوط به استعلام درخواست دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeRequestItemDto.class)))
    @PostMapping("/report")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<List<SayadChequeRequestItemDto>> sayadReport(
        @Valid @RequestBody SayadChequeReportRequestDto sayadChequeReportRequest) {
        return new ResponseEntity<>(sayadService.reportIssuedCheque(sayadChequeReportRequest), HttpStatus.OK);
    }


    @Operation(summary = "سرویس درخواست دسته چک", description = "سرویس مربوط به درخواست دسته چک")
    @ApiResponse(responseCode = "204",
        content = @Content(schema = @Schema(implementation = SayadChequeRequestResDto.class)))
    @PostMapping("/request")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<HttpStatus> sayadRequest(
        @Valid @RequestBody SayadChequeRequestDto sayadChequeRequest) {
        sayadService.requestCheque(sayadChequeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "سرویس درخواست استعلام دسته چک", description = "سرویس مربوط استعلام دسته چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeInquiryBatchResponseDto.class)))
    @PostMapping("/batch-inquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<List<SayadChequeInquiryBatchResponseDto>> batchRequest(
        @Valid @RequestBody SayadChequeInquiryBatchRequestDto sayadChequeInquiryBatchRequest) {
        return new ResponseEntity<>(sayadService.batchInquiry(sayadChequeInquiryBatchRequest), HttpStatus.OK);

    }

    @Operation(summary = "سرویس درخواست استعلام برگ چک", description = "سرویس مربوط به درخواست استعلام برگ چک")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = SayadChequeInquirySheetResponseDto.class)))
    @PostMapping("/sheet-inquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<List<SayadChequeInquirySheetResponseDto>> sheetRequest(
        @Valid @RequestBody SayadChequeInquirySheetRequestDto sayadChequeInquirySheetRequest) {
        return new ResponseEntity<>(sayadService.sheetInquiry(sayadChequeInquirySheetRequest), HttpStatus.OK);

    }

}
