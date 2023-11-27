package parkingLotG.parkinglotG.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingLotG.parkinglotG.domain.ParkingInfo;
import parkingLotG.parkinglotG.repository.nearByLocationRepository;

import java.util.List;

@Service
public class nearByLocationService {

    private final nearByLocationRepository repository;

    @Autowired
    public nearByLocationService(nearByLocationRepository repository) {
        this.repository = repository;
    }

    public List<ParkingInfo> getNearbyParkingInfo() {
        return repository.staticFindNearbyLocations();
    }

    public List<ParkingInfo> getNearbyParkingInfo(Double lat, Double lng) {
        return repository.findNearbyLocations(lat, lng);
    }
}
