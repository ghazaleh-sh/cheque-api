package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeStakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChequeStakeholderRepository extends JpaRepository<ChequeStakeholder, Long> {

    List<ChequeStakeholder> findAllBySayadIdAndIsFinalTrue(String sayadId);
}
