package parkingLotG.parkinglotG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import parkingLotG.parkinglotG.domain.TestDomain;

import java.util.List;
import java.util.Optional;

public interface nearByLocationRepository extends JpaRepository<TestDomain,Long> {
    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(37.4685225)) * cos(radians(lat)) * cos(radians(lng) - radians(126.8943311)) + sin(radians(37.4685225)) * sin(radians(lat)))) AS distance " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "HAVING distance <= 3 " +
            "ORDER BY distance " +
            "LIMIT 0, 100", nativeQuery = true)
    List<TestDomain> staticFindNearbyLocations();


    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(lat)) * cos(radians(lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(lat)))) AS distance " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "HAVING distance <= 3 " +
            "ORDER BY distance " +
            "LIMIT 0, 100", nativeQuery = true)
    List<TestDomain> findNearbyLocations(@Param("latitude") Double latitude, @Param("longitude") Double longitude);


}
