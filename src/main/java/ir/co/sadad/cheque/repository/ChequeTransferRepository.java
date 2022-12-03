package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeTransferRepository extends JpaRepository<ChequeTransfer, Long> {
}
