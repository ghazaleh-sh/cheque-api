package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeIssueRepository extends JpaRepository<ChequeIssue, Long> {

    ChequeIssue findTopBySayadId(String sayadId);
}
