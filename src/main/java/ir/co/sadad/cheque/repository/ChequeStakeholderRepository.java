package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChequeStakeholderRepository extends JpaRepository<ChequeStakeholder, Long> {

    Optional<List<ChequeStakeholder>> findAllBySayadId(String sayadId);
}
