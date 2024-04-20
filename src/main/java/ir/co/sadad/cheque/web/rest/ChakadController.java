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

  
}
