package parkingLotG.parkinglotG.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Parkingtable")
public class ParkingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PARKING_CODE")
    private Integer PARKING_CODE;

    @Column(name = "PARKING_NAME")
    private String PARKING_NAME;

    @Column(name = "PARKING_ADDR")
    private String PARKING_ADDR;

    @Column(name = "PARKING_TYPE_NM")
    private String PARKING_TYPE_NM;

    @Column(name = "OPERATION_RULE_NM")
    private String OPERATION_RULE_NM;

    @Column(name = "TEL")
    private String TEL;

    @Column(name = "QUE_STATUS")
    private boolean QUE_STATUS;

    @Column(name = "CAPACITY")
    private Integer CAPACITY;

    @Column(name = "CUR_PARKING")
    private Integer CUR_PARKING;

    @Column(name = "CUR_PARKING_TIME")
    private String CUR_PARKING_TIME;

    @Column(name = "PAY_NM")
    private String PAY_NM;

    @Column(name = "NIGHT_FREE_OPEN_NM")
    private String NIGHT_FREE_OPEN_NM;

    @Column(name = "WEEKDAY_BEGIN_TIME")
    private String WEEKDAY_BEGIN_TIME;

    @Column(name = "WEEKDAY_END_TIME")
    private String WEEKDAY_END_TIME;

    @Column(name = "WEEKEND_BEGIN_TIME")
    private String WEEKEND_BEGIN_TIME;

    @Column(name = "WEEKEND_END_TIME")
    private String WEEKEND_END_TIME;

    @Column(name = "HOLIDAY_BEGIN_TIME")
    private String HOLIDAY_BEGIN_TIME;

    @Column(name = "HOLIDAY_END_TIME")
    private String HOLIDAY_END_TIME;

    @Column(name = "SATURDAY_PAY_NM")
    private String SATURDAY_PAY_NM;

    @Column(name = "HOLIDAY_PAY_NM")
    private String HOLIDAY_PAY_NM;

    @Column(name = "FULLTIME_MONTHLY")
    private Integer FULLTIME_MONTHLY;

    @Column(name = "RATES")
    private String RATES;

    @Column(name = "TIME_RATE")
    private String TIME_RATE;

    @Column(name = "ADD_RATES")
    private String ADD_RATES;

    @Column(name = "ADD_TIME_RATE")
    private String ADD_TIME_RATE;

    @Column(name = "DAY_MAXIMUM")
    private Integer DAY_MAXIMUM;

    @Column(name = "LAT")
    private double LAT;

    @Column(name = "LNG")
    private double LNG;

    @Column(name = "SH_CO")
    private String SH_CO;

    @Column(name = "SH_LINK")
    private String SH_LINK;

    @Column(name = "SH_TYPE")
    private boolean SH_TYPE;

    @Column(name = "COLOR")
    private String COLOR;


    public ParkingInfo() {}




}