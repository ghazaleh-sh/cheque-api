package ir.co.sadad.cheque.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.co.sadad.cheque.domain.dto.ShahabCodeRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabSuccessResponseDto;
import ir.co.sadad.cheque.service.ShahabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/shahab")
@RequiredArgsConstructor
@Log4j2
public class ShahabController {

    private final ShahabService shahabService;

    @PostMapping("/inquiry")
    @PreAuthorize("hasAuthority('icms-nahab-inquiry')")
    public ShahabSuccessResponseDto inquiryRequest(@Valid @RequestBody ShahabRequestDto shahabRequestDto) {
        return shahabService.inquiryRequest(shahabRequestDto);
    }

    @GetMapping("/track")
    @PreAuthorize("hasAuthority('icms-nahab-inquiry')")
    public ShahabSuccessResponseDto trackRequest(
        @RequestParam("trackCode") String trackCode,
        @RequestParam("ssn") String ssn) {
        return shahabService.trackRequest(ssn, trackCode);
    }

    @PostMapping("/code")
    @Operation(summary = "سرویس نمایش کد شهاب", description = "سرویس نمایش کد شهاب مشتریان حقیقی و حقوقی ایرانی و اتباع بیگانه V1")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ShahabSuccessResponseDto.class)))
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ShahabSuccessResponseDto codeRequest(
        @RequestBody ShahabCodeRequestDto shahabCodeRequestDto) {
        return shahabService.codeRequest(shahabCodeRequestDto);
    }
}
