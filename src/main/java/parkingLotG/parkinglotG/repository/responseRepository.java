package parkingLotG.parkinglotG.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import parkingLotG.parkinglotG.domain.ParkingInfo;

import java.util.List;
import java.util.Optional;

@Repository // 스프링빈에 등록 //

public interface responseRepository extends JpaRepository<ParkingInfo,Long> {
    @Query (value = "SELECT id, PARKING_CODE, PARKING_NAME, PARKING_ADDR, PARKING_TYPE_NM, OPERATION_RULE_NM, TEL, QUE_STATUS, sum(CAPACITY) as CAPACITY, CUR_PARKING, CUR_PARKING_TIME, PAY_NM, NIGHT_FREE_OPEN_NM, WEEKDAY_BEGIN_TIME, WEEKDAY_END_TIME, WEEKEND_BEGIN_TIME, WEEKEND_END_TIME, HOLIDAY_BEGIN_TIME, HOLIDAY_END_TIME, SATURDAY_PAY_NM, HOLIDAY_PAY_NM, FULLTIME_MONTHLY, RATES, TIME_RATE, ADD_RATES, ADD_TIME_RATE, DAY_MAXIMUM, LAT, LNG, SH_CO, SH_LINK, SH_TYPE, " +
            "CASE " +
            "WHEN CUR_PARKING/sum(CAPACITY) < 0.3 THEN '많음' " +
            "WHEN CUR_PARKING/sum(CAPACITY) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as COLOR " +
            "FROM Parkingtable " +
            "GROUP BY PARKING_CODE " +
            "ORDER BY PARKING_CODE", nativeQuery = true)

    List<ParkingInfo> selectGroupByQueryList();

    @Query(value = "SELECT id, PARKING_CODE, PARKING_NAME, PARKING_ADDR, PARKING_TYPE_NM, OPERATION_RULE_NM, TEL, QUE_STATUS, sum(CAPACITY) as CAPACITY, CUR_PARKING, CUR_PARKING_TIME, PAY_NM, NIGHT_FREE_OPEN_NM, WEEKDAY_BEGIN_TIME, WEEKDAY_END_TIME, WEEKEND_BEGIN_TIME, WEEKEND_END_TIME, HOLIDAY_BEGIN_TIME, HOLIDAY_END_TIME, SATURDAY_PAY_NM, HOLIDAY_PAY_NM, FULLTIME_MONTHLY, RATES, TIME_RATE, ADD_RATES, ADD_TIME_RATE, DAY_MAXIMUM, LAT, LNG, SH_CO, SH_LINK, SH_TYPE, " +
            "CASE " +
            "WHEN CUR_PARKING/sum(CAPACITY) < 0.3 THEN '많음' " +
            "WHEN CUR_PARKING/sum(CAPACITY) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as COLOR " +
            "FROM Parkingtable " +
            "WHERE PARKING_CODE = :PARKING_CODE " +
            "GROUP BY PARKING_CODE "
            , nativeQuery = true)
    Optional<ParkingInfo> selectGroupByQueryOne(@Param("PARKING_CODE") String PARKING_CODE);



}