package ir.co.sadad.cheque.service.v2;

import ir.co.sadad.cheque.domain.dto.SayadChequeInquiryBatchResponseDto;
import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class PichakServiceV2 extends BaseService {

    public static final Integer ISSUED_CODE = 4;
    public static final Integer REPORT_LENGTH = 1;
    public static final Integer BOOK_SHEET_COUNT = 0;

    public PichakServiceV2(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    public abstract SuccessClientResponseDto requestCheque(PichakRequestChequeReqDto requestChequeReqDto, String authToken);

    public abstract List<PichakReportResponseDto> reportCheque(String iban, String authToken);

    public abstract List<SayadChequeInquiryBatchResponseDto> ChequeBookInquiry(String accountNumber, String authToken);

    public abstract List<PichakLeafInquiryResponseDto> ChequeLeafInquiry(Long chequeIssuedId, String account, String authToken);
}
