package ir.co.sadad.cheque.service;

import ir.co.sadad.cheque.domain.dto.v2.IssueDto;
import ir.co.sadad.cheque.domain.dto.v2.IssueResponseDto;
import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import ir.co.sadad.cheque.domain.enums.IssueStatus;

/**
 * <pre>
 *     to do changes associated with ISSUE table
 * </pre>
 */
public interface ChequeIssueService {

    /**
     * create issue based on request
     *
     * @param issueDto
     */
    ChequeIssue createIssue(IssueDto issueDto);

    /**
     * update issue based on request
     *
     * @param
     * @return IssueResponseDto
     */
    IssueResponseDto UpdateIssueWithSignInfo(String sayadId, String signatureValue, String transactionId);

    /**
     * set status of issue in different steps
     *
     * @param sayadId
     * @param issueStatus
     */
    void updateIssueStatus(String sayadId, IssueStatus issueStatus);

    /**
     * set status of issue in different steps
     *
     * @param sayadId
     */
    void updateIssueSigned(String sayadId);

    /**
     * get info of issue based on sayadId ,
     *
     * @param sayadId cheque identifier
     * @return info of issue
     */
    IssueResponseDto getBy(String sayadId);

    /**
     * get entity of issue based on sayadId ,
     *
     * @param sayadId cheque identifier
     * @return info of issue
     */
    ChequeIssue getEntityBy(String sayadId);

}
