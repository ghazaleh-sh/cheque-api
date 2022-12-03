package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * repository for reasons
 */
@Repository
public interface ChequeReasonsRepository extends JpaRepository<ChequeReason, Long> {

    List<ChequeReason> findByReasonType(String reasonType);

    Optional<ChequeReason> findByReasonCode(String reasonCode);
}
