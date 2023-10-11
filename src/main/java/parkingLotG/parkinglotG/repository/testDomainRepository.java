package parkingLotG.parkinglotG.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parkingLotG.parkinglotG.domain.TestDomain;

@Repository // 스프링빈에 등록

public interface testDomainRepository extends JpaRepository<TestDomain,Long> {
}
