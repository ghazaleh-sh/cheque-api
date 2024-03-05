package ir.co.sadad.cheque.repository;

import ir.co.sadad.cheque.domain.entity.ChequeUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChequeUserInfoRepository extends JpaRepository<ChequeUserInfo, Long> {

    Optional<ChequeUserInfo> findByUserId(String userId);

    Optional<ChequeUserInfo> findByOrganizationIdCode(String organizationId);
}
