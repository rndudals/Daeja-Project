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
    private String parkingCode;
    private String parkingName;
    private double capacity;
    private double curParking;
    private double lat;
    private double lng;
    private String color;
    public TestDomain() {}



    public TestDomain(String parkingCode, String parkingName, double capacity, double curParking, double lat, double lng, String color) {
        this.parkingCode=parkingCode;
        this.parkingName=parkingName;
        this.capacity=capacity;
        this.curParking=curParking;
        this.lat=lat;
        this.lng=lng;
        this.color=color;
    }
}
