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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(description = "سرویس های چکاد", name = "Chakad services resources V2 version ")
public class ChakadV2Controller {


}
