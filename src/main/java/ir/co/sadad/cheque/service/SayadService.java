package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.web.rest.errors.GeneralException;

import javax.servlet.http.HttpServletRequest;

/**
 * service for sayad rquests
 * <pre>
 *     contains :
 *      . issue cheque
 *      . report
 *      . sheet inquiry
 *      . batch inquiry
 * </pre>
 */
public abstract class SayadService extends BaseService {

    public SayadService(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    /**
     * report for issued cheques
     * <pre>
     *     for more info : Cheque-Sayad-Report.pdf
     * </pre>
     *
     * @param chequeReportRequest request
     * @return info from cheques services
     * @throws GeneralException response was not 100 , throws exception
     */
    public abstract SayadChequeReportResponseDto reportIssuedCheque(SayadChequeReportRequestDto chequeReportRequest);

    /**
     * request for cheque
     * <pre>
     *      for more info : Cheque-Sayad-Request.pdf
     *  </pre>
     *
     * @param sayadChequeRequest request for cheque
     * @return if response of service was 100 return true
     * @throws GeneralException response was not 100 , throws exception
     */
    public abstract SayadChequeRequestResDto requestCheque(SayadChequeRequestDto sayadChequeRequest);

    /**
     * service for batch inquiry
     * <pre>
     *     for more info : GR-MNG-001.pdf
     * </pre>
     *
     * @param chequeInquiryBatchRequest request for batch inquiry
     * @return inquiry batch from service
     * @throws GeneralException response was not 100 , throws exception
     */
    public abstract SayadChequeInquiryBatchResponseDto batchInquiry(SayadChequeInquiryBatchRequestDto chequeInquiryBatchRequest);

    /**
     * service for sheet inquiry
     * <pre>
     *     for more info : GR-MNG-002.pdf
     * </pre>
     *
     * @param chequeInquirySheetRequest request for sheet inquiry
     * @return inquiry sheet from service
     * @throws GeneralException response was not 100 , throws exception
     */
    public abstract SayadChequeInquirySheetResponseDto sheetInquiry(SayadChequeInquirySheetRequestDto chequeInquirySheetRequest);
}
