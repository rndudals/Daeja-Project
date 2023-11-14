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
    @Query(value = "SELECT id, parking_code,SUM(capacity) as capacity, cur_parking, lat, lng, parking_name, add_rates, add_time_rate, current_parking_update_time, day_maximum, fulltime_monthly, holiday_begin_time, holiday_end_time, holiday_pay_nm, night_free_open_nm, operation_rule_nm,  parking_addr, parking_type_nm, pay_nm, que_status, rates, saturday_pay_nm, sh_co, sh_link, sh_type, tel, time_rate, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, " +
            "CASE " +
            "WHEN cur_parking/SUM(capacity) < 0.3 THEN '많음' " +
            "WHEN cur_parking/SUM(capacity) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as color " +
            "FROM test_domain " +
            "GROUP BY parking_code " +
            "ORDER BY parking_code", nativeQuery = true)

    List<TestDomain> selectGroupByQueryList();

    @Query(value = "SELECT id, parking_code,SUM(capacity) as capacity, cur_parking, lat, lng, parking_name, add_rates, add_time_rate, current_parking_update_time, day_maximum, fulltime_monthly, holiday_begin_time, holiday_end_time, holiday_pay_nm, night_free_open_nm, operation_rule_nm,  parking_addr, parking_type_nm, pay_nm, que_status, rates, saturday_pay_nm, sh_co, sh_link, sh_type, tel, time_rate, weekday_begin_time, weekday_end_time, weekend_begin_time, weekend_end_time, " +
            "CASE " +
            "WHEN cur_parking/SUM(capacity) < 0.3 THEN '많음' " +
            "WHEN cur_parking/SUM(capacity) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as color " +
            "FROM test_domain " +
            "WHERE parking_code = :parking_code " +
            "GROUP BY parking_code "
            , nativeQuery = true)
    Optional<TestDomain> selectGroupByQueryOne(@Param("parking_code") String parking_code);



}