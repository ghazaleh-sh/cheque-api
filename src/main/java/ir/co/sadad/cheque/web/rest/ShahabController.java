package ir.co.sadad.cheque.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.co.sadad.cheque.domain.dto.ShahabRequestDto;
import ir.co.sadad.cheque.domain.dto.ShahabSuccessResponseDto;
import ir.co.sadad.cheque.service.ShahabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ShahabSuccessResponseDto> inquiryRequest(@Valid @RequestBody ShahabRequestDto shahabRequestDto) {
        return new ResponseEntity<>(shahabService.inquiryRequest(shahabRequestDto), HttpStatus.OK);

    }

    @GetMapping("/track")
    @PreAuthorize("hasAuthority('icms-nahab-inquiry')")
    public ResponseEntity<ShahabSuccessResponseDto> trackRequest(
        @RequestParam("trackCode") String trackCode,
        @RequestParam("ssn") String ssn) {
        return new ResponseEntity<>(shahabService.trackRequest(ssn, trackCode), HttpStatus.OK);
    }

    @PostMapping("/code")
    @Operation(summary = "سرویس نمایش کد شهاب", description = "سرویس نمایش کد شهاب مشتریان حقیقی و حقوقی ایرانی و اتباع بیگانه V1")
    @ApiResponse(responseCode = "200",
        content = @Content(schema = @Schema(implementation = ShahabSuccessResponseDto.class)))
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ResponseEntity<ShahabSuccessResponseDto> codeRequest(@RequestParam("ssn") String ssn) {
        return new ResponseEntity<>(shahabService.codeRequest(ssn), HttpStatus.OK);
    }
}
