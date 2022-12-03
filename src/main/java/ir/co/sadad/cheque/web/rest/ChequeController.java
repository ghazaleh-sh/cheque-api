package ir.co.sadad.cheque.web.rest;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.domain.entity.Cheque;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.repository.ChequeRepository;
import ir.co.sadad.cheque.service.ChequeService;
import ir.co.sadad.cheque.web.rest.external.dto.response.AllocatingEstelamResponseDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class ChequeController {

    private final ChequeService chequeService;
    private final ChequeRepository chequeRepository;
    private final SsoClientTokenManager ssoClientTokenManager;

    @PostMapping("/v1")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ChequeRequestResponse chequeRequest(@Valid @RequestBody ChequeRequestDto chequeRequestDto) {
        return chequeService.chequeRequest(chequeRequestDto);
    }

    @GetMapping("/bounced-inquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public BouncedChequeResponseDto bouncedInquiry() {
        return chequeService.bouncedInquiry();
    }

    @GetMapping("/bounced-inquiry/{ssn}")
    @PreAuthorize("hasAuthority('SCOPE_baamnet-cheque-bounced-inquiry')")
    public BouncedChequeResponseDto bouncedInquiryWithSsn(@PathVariable("ssn") String ssn) {
        return chequeService.bouncedInquiryWithSsn(ssn);
    }

    @PostMapping("/AllocatingInquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public AllocatingEstelamResponseDto getChequeAllocatingInquiry(
        @Valid @RequestBody AllocatingInquiryRequestDto allocatingInquiryRequestDto
    ) {
        return chequeService.chequeAllocatingInquiry(allocatingInquiryRequestDto);
    }

    @GetMapping("/request-inquiry")
    @PreAuthorize("hasAuthority('SCOPE_account-super')")
    public ChequeResponse requestInquiry(
        @RequestParam String iban,
        @RequestParam Integer timeInterval,
        @RequestParam(required = false) Integer pageNumber,
        @RequestParam(required = false) Integer pageSize
    ) {
        return chequeService.report(iban, timeInterval);
    }

    @GetMapping("/getAll")
    public List<Cheque> getAllCheques() {
        String ss = ssoClientTokenManager.getClientToken();
        return chequeRepository.findAll();
    }
}
