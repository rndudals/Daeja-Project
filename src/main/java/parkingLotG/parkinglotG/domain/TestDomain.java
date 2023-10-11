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
    public TestDomain() {}



    public TestDomain(int id, String parkingCode, String parkingName, double capacity, double curParking, double lat, double lng) {
        this.id = id;
        this.parkingCode=parkingCode;
        this.parkingName=parkingName;
        this.capacity=capacity;
        this.curParking=curParking;
        this.lat=lat;
        this.lng=lng;
    }
}
