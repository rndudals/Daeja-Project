package parkingLotG.parkinglotG.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;


@Entity
@Data
public class TestDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer parkingCode;
    private String parkingName;
    private Integer capacity;
    private Integer curParking;
    private double lat;
    private double lng;
    private String color;



    //추가
    private String parking_addr;
    private String parking_type_nm;
    private String operation_rule_nm;
    private String tel;
    private boolean que_status;
    private String currentParkingUpdateTime;
    private String pay_nm;
    private String night_free_open_nm;
    private String weekday_begin_time;
    private String weekday_end_time;
    private String weekend_begin_time;
    private String weekend_end_time;
    private String holiday_begin_time;
    private String holiday_end_time;
    private String saturday_pay_nm;
    private String holiday_pay_nm;
    private Integer fulltime_monthly;
    private Integer rates;
    private Integer time_rate;
    private String add_rates;
    private String add_time_rate;
    private Integer day_maximum;
    private String sh_co;
    private String sh_link;
    private boolean sh_type;
    public TestDomain() {}




}