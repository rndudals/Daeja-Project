package parkingLotG.parkinglotG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import parkingLotG.parkinglotG.domain.TestDomain;

import java.util.List;
import java.util.Optional;

public interface nearByLocationRepository extends JpaRepository<TestDomain,Long> {
    @Query(value = "SELECT id, parking_code,SUM(capacity) as capacity, cur_parking, lat, lng, parking_name, add_rates, add_time_rate, current_parking_update_time, day_maximum, fulltime_monthly, holiday_begin_time, holiday_end_time, holiday_pay_nm, night_free_open_nm, operation_rule_nm,  parking_addr, parking_type_nm, pay_nm, que_status, rates, saturday_pay_nm, sh_co, sh_link, sh_type, tel, time_rate, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, " +
            "CASE " +
            "WHEN cur_parking/SUM(capacity) < 0.3 THEN '많음' " +
            "WHEN cur_parking/SUM(capacity) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as color, " +
            "(6371 * acos(cos(radians(37.4685225)) * cos(radians(lat)) * cos(radians(lng) - radians(126.8943311)) + sin(radians(37.4685225)) * sin(radians(lat)))) AS distance " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "HAVING distance <= 3 " +
            "ORDER BY distance " +
            "LIMIT 0, 100", nativeQuery = true)
    List<TestDomain> staticFindNearbyLocations();


    @Query(value = "SELECT id, parking_code,SUM(capacity) as capacity, cur_parking, lat, lng, parking_name, add_rates, add_time_rate, current_parking_update_time, day_maximum, fulltime_monthly, holiday_begin_time, holiday_end_time, holiday_pay_nm, night_free_open_nm, operation_rule_nm,  parking_addr, parking_type_nm, pay_nm, que_status, rates, saturday_pay_nm, sh_co, sh_link, sh_type, tel, time_rate, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, " +
            "CASE " +
            "WHEN cur_parking/SUM(capacity) < 0.3 THEN '많음' " +
            "WHEN cur_parking/SUM(capacity) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as color, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(lat)) * cos(radians(lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(lat)))) AS distance " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "HAVING distance <= 3 " +
            "ORDER BY distance " +
            "LIMIT 0, 100", nativeQuery = true)
    List<TestDomain> findNearbyLocations(@Param("latitude") Double latitude, @Param("longitude") Double longitude);


}
