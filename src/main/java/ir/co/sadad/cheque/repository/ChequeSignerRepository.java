package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeSigner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeSignerRepository extends JpaRepository<ChequeSigner, Long> {

}
