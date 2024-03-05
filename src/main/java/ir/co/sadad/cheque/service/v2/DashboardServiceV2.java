package ir.co.sadad.cheque.service.v2;

import ir.co.sadad.cheque.domain.dto.v2.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import ir.co.sadad.cheque.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * service of dashboard in version 2
 * <pre>
 *     this service contains :
 *     1 . issue cheque
 *     2 . cartable
 *     3. transfer
 *
 * </pre>
 */
public abstract class DashboardServiceV2 extends BaseService {

    public DashboardServiceV2(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    /**
     * to get valid certification and compare it with saved user info in terms of updating status
     *
     * @param authToken user's token (password type)
     * @return UserCertificationResponseDto
     */
    public abstract UserCertificationResponseDto userCertification(String authToken);

    /**
     * service for create request for issue of cheque
     * <pre>
     *     this service will check reason , then call inquiry service for getting ShahabCode of receivers ,
     *     after that create data for sign and send it to client for sign
     *     Remember :  at end of process this data will persist in DB .
     * </pre>
     *
     * @param issueRequestReq request of user , contains cheque info and receivers info
     * @param authToken       token of user
     * @return data ready to be sign by client
     */
    public abstract IssueRequestResDto requestIssue(IssueRequestReqDto issueRequestReq, String authToken);

    /**
     * service ro call main service of chakad issue
     *
     * @param issueReq
     * @param authToken
     * @return
     */
    public abstract ChequeIssueResponseDto issue(ChequeIssueRequestDto issueReq, String authToken);

    public abstract List<ChequeCartableDto> cartable(String authToken);

    public abstract SuccessClientResponseDto acceptance(String sayadId, AcceptanceRequestDto acceptanceRequestDto, String authToken);

    public abstract TransferResponseDto transfer(TransferRequestDto transferRequestDto, String authorizationCode, String authToken);

    public abstract SuccessClientResponseDto depositRegister(DepositRegisterRequestDto depositRegisterRequestDto, String authToken);

    public abstract SuccessClientResponseDto depositCancel(DepositCancelRequestDto cancelRequestDto, String authToken);
}
