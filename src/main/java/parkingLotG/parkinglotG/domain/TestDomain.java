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
    public TestDomain() {}



    public TestDomain(int parkingCode, String parkingName, int capacity, int curParking, double lat, double lng, String color) {
        this.parkingCode=parkingCode;
        this.parkingName=parkingName;
        this.capacity=capacity;
        this.curParking=curParking;
        this.lat=lat;
        this.lng=lng;
        this.color=color;
    }
}