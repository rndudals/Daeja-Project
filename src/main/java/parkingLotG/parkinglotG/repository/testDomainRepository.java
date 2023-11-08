package parkingLotG.parkinglotG.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import parkingLotG.parkinglotG.domain.TestDomain;

import java.util.List;

@Repository // 스프링빈에 등록 //

public interface testDomainRepository extends JpaRepository<TestDomain,Long> {
    @Query(value = "SELECT id,parking_name, parking_code, SUM(capacity) as capacity, cur_parking, lat, lng " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "ORDER BY id", nativeQuery = true)
    List<TestDomain> customQuery();
}
