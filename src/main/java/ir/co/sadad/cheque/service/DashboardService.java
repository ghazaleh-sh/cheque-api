package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.*;
import ir.co.sadad.cheque.management.SsoClientTokenManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * services for dashboard of chakad
 * <pre>
 *     contains :
 *     1 . cartable
 *     2 . Issue
 *     3 . Transfer
 * </pre>
 */
public abstract class DashboardService extends BaseService {

    public DashboardService(HttpServletRequest httpServletRequest, SsoClientTokenManager ssoClientTokenManager) {
        super(httpServletRequest, ssoClientTokenManager);
    }

    /**
     * service of cartable
     * <pre>
     *     must used with sso client token
     * </pre>
     *
     * @param authToken       token of password credential
     * @param cartableRequest request
     * @return cartable dto
     */
    public abstract ChakadCartableResponseDto cartable(String authToken,
                                                       ChakadCartableRequestDto cartableRequest);

    /**
     * service of reasons
     * <pre>
     *     this reasons is uploaded to db and readOnly
     * </pre>
     *
     * @param reasonType type of reason
     * @return list of reasons
     */
    public abstract List<ReasonResponseDto> getReasons(String reasonType);

    /**
     * @param authToken       token of password credential
     * @param issueRequestDto cheque info and sign data
     * @return
     */
    public abstract ResponseEntity<HttpStatus> issue(String authToken, ChakadIssueRequestDto issueRequestDto);

    /**
     * @param authToken          token of password credential
     * @param transferRequestDto transfer info and sign data
     * @return
     */
    public abstract ResponseEntity<HttpStatus> transfer(String authToken, ChakadTransferRequestDto transferRequestDto);
}
