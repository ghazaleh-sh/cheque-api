package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChequeIssueRepository extends JpaRepository<ChequeIssue, Long> {

    Optional<ChequeIssue> findBySayadId(String sayadId);

    ChequeIssue findTopBySayadId(String sayadId);
}
