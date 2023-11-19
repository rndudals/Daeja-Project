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

    public ParkingInfo(Integer PARKING_CODE, String PARKING_NAME, String PARKING_ADDR, String PARKING_TYPE_NM,
                       String OPERATION_RULE_NM, String TEL, boolean QUE_STATUS, Integer CAPACITY, Integer CUR_PARKING,
                       String CUR_PARKING_TIME, String PAY_NM, String NIGHT_FREE_OPEN_NM, String WEEKDAY_BEGIN_TIME,
                       String WEEKDAY_END_TIME, String WEEKEND_BEGIN_TIME, String WEEKEND_END_TIME,
                       String HOLIDAY_BEGIN_TIME, String HOLIDAY_END_TIME, String SATURDAY_PAY_NM,
                       String HOLIDAY_PAY_NM, Integer FULLTIME_MONTHLY, String RATES, String TIME_RATE,
                       String ADD_RATES, String ADD_TIME_RATE, Integer DAY_MAXIMUM, double LAT, double LNG,
                       String SH_CO, String SH_LINK, boolean SH_TYPE, String COLOR) {
        this.PARKING_CODE = PARKING_CODE;
        this.PARKING_NAME = PARKING_NAME;
        this.PARKING_ADDR = PARKING_ADDR;
        this.PARKING_TYPE_NM = PARKING_TYPE_NM;
        this.OPERATION_RULE_NM = OPERATION_RULE_NM;
        this.TEL = TEL;
        this.QUE_STATUS = QUE_STATUS;
        this.CAPACITY = CAPACITY;
        this.CUR_PARKING = CUR_PARKING;
        this.CUR_PARKING_TIME = CUR_PARKING_TIME;
        this.PAY_NM = PAY_NM;
        this.NIGHT_FREE_OPEN_NM = NIGHT_FREE_OPEN_NM;
        this.WEEKDAY_BEGIN_TIME = WEEKDAY_BEGIN_TIME;
        this.WEEKDAY_END_TIME = WEEKDAY_END_TIME;
        this.WEEKEND_BEGIN_TIME = WEEKEND_BEGIN_TIME;
        this.WEEKEND_END_TIME = WEEKEND_END_TIME;
        this.HOLIDAY_BEGIN_TIME = HOLIDAY_BEGIN_TIME;
        this.HOLIDAY_END_TIME = HOLIDAY_END_TIME;
        this.SATURDAY_PAY_NM = SATURDAY_PAY_NM;
        this.HOLIDAY_PAY_NM = HOLIDAY_PAY_NM;
        this.FULLTIME_MONTHLY = FULLTIME_MONTHLY;
        this.RATES = RATES;
        this.TIME_RATE = TIME_RATE;
        this.ADD_RATES = ADD_RATES;
        this.ADD_TIME_RATE = ADD_TIME_RATE;
        this.DAY_MAXIMUM = DAY_MAXIMUM;
        this.LAT = LAT;
        this.LNG = LNG;
        this.SH_CO = SH_CO;
        this.SH_LINK = SH_LINK;
        this.SH_TYPE = SH_TYPE;
        this.COLOR = COLOR;
    }



}