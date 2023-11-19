package parkingLotG.parkinglotG.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import parkingLotG.parkinglotG.domain.ParkingInfo;

import java.util.List;

public interface nearByLocationRepository extends JpaRepository<ParkingInfo,Long> {
    @Query(value = "SELECT id, PARKING_CODE, PARKING_NAME, PARKING_ADDR, PARKING_TYPE_NM, OPERATION_RULE_NM, TEL, QUE_STATUS, sum(CAPACITY) as CAPACITY, CUR_PARKING, CUR_PARKING_TIME, PAY_NM, NIGHT_FREE_OPEN_NM, WEEKDAY_BEGIN_TIME, WEEKDAY_END_TIME, WEEKEND_BEGIN_TIME, WEEKEND_END_TIME, HOLIDAY_BEGIN_TIME, HOLIDAY_END_TIME, SATURDAY_PAY_NM, HOLIDAY_PAY_NM, FULLTIME_MONTHLY, RATES, TIME_RATE, ADD_RATES, ADD_TIME_RATE, DAY_MAXIMUM, LAT, LNG, SH_CO, SH_LINK, SH_TYPE, " +
            "CASE " +
            "WHEN CUR_PARKING/sum(CAPACITY) < 0.3 THEN '많음' " +
            "WHEN CUR_PARKING/sum(CAPACITY) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as COLOR, " +
            "(6371 * acos(cos(radians(37.4685225)) * cos(radians(lat)) * cos(radians(lng) - radians(126.8943311)) + sin(radians(37.4685225)) * sin(radians(lat)))) AS distance " +
            "FROM Parkingtable " +
            "GROUP BY PARKING_CODE " +
            "HAVING distance <= 3 " +
            "ORDER BY distance " +
            "LIMIT 0, 4", nativeQuery = true)
    List<ParkingInfo> staticFindNearbyLocations();


    @Query(value = "SELECT id, PARKING_CODE, PARKING_NAME, PARKING_ADDR, PARKING_TYPE_NM, OPERATION_RULE_NM, TEL, QUE_STATUS, sum(CAPACITY) as CAPACITY, CUR_PARKING, CUR_PARKING_TIME, PAY_NM, NIGHT_FREE_OPEN_NM, WEEKDAY_BEGIN_TIME, WEEKDAY_END_TIME, WEEKEND_BEGIN_TIME, WEEKEND_END_TIME, HOLIDAY_BEGIN_TIME, HOLIDAY_END_TIME, SATURDAY_PAY_NM, HOLIDAY_PAY_NM, FULLTIME_MONTHLY, RATES, TIME_RATE, ADD_RATES, ADD_TIME_RATE, DAY_MAXIMUM, LAT, LNG, SH_CO, SH_LINK, SH_TYPE, " +
            "CASE " +
            "WHEN CUR_PARKING/sum(CAPACITY) < 0.3 THEN '많음' " +
            "WHEN CUR_PARKING/sum(CAPACITY) BETWEEN 0.3 AND 0.6 THEN '보통' " +
            "ELSE '적음' " +
            "END as COLOR, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(lat)) * cos(radians(lng) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(lat)))) AS distance " +
            "FROM Parkingtable " +
            "GROUP BY PARKING_CODE " +
            "HAVING distance <= 3 " +
            "ORDER BY distance " +
            "LIMIT 0, 4", nativeQuery = true)
    List<ParkingInfo> findNearbyLocations(@Param("latitude") Double latitude, @Param("longitude") Double longitude);


}
