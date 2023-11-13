package parkingLotG.parkinglotG.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import parkingLotG.parkinglotG.domain.TestDomain;

import java.util.List;
import java.util.Optional;

@Repository // 스프링빈에 등록 //

public interface responseRepository extends JpaRepository<TestDomain,Long> {
    @Query(value = "SELECT id,parking_name, parking_code, SUM(capacity) as capacity, cur_parking, lat, lng, " +
            "CASE " +
            "WHEN cur_parking/SUM(capacity) < 0.3 THEN 'G' " +
            "WHEN cur_parking/SUM(capacity) BETWEEN 0.3 AND 0.6 THEN 'Y' " +
            "ELSE 'R' " +
            "END as color " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "ORDER BY id", nativeQuery = true)

    List<TestDomain> selectGroupByQueryList();

    @Query(value = "SELECT id,parking_name, parking_code, SUM(capacity) as capacity, cur_parking, lat, lng, " +
            "CASE " +
            "WHEN cur_parking/SUM(capacity) < 0.3 THEN 'G' " +
            "WHEN cur_parking/SUM(capacity) BETWEEN 0.3 AND 0.6 THEN 'Y' " +
            "ELSE 'R' " +
            "END as color " +
            "FROM test_domain " +
            "WHERE parking_code = :parking_code " +
            "GROUP BY parking_code "
            , nativeQuery = true)
    Optional<TestDomain> selectGroupByQueryOne(@Param("parking_code") String parking_code);



}