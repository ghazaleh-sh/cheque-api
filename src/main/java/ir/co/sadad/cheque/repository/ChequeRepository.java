package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.Cheque;
import ir.co.sadad.cheque.domain.enums.ChequeStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    Cheque findChequeByNationalCodeAndChequeStatus(String nationalCode, ChequeStatus chequeStatus);

    List<Cheque> findAllByNationalCodeAndChequeStatusIn(String nationalCode, List<ChequeStatus> chequeStatus);

    Cheque findTopByIban(String iban);
}
