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
@RequiredArgsConstructor
@Log4j2
public class ChequeController {

}
