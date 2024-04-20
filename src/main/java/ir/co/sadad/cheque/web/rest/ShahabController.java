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
@RequiredArgsConstructor
@Log4j2
public class ShahabController {


}
