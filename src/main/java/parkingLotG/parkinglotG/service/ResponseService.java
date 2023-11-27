package parkingLotG.parkinglotG.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingLotG.parkinglotG.domain.ParkingInfo;
import parkingLotG.parkinglotG.repository.responseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {

    private final responseRepository repository;

    @Autowired
    public ResponseService(responseRepository repository) {
        this.repository = repository;
    }

    public List<ParkingInfo> getGroupedParkingInfoList() {
        return repository.selectGroupByQueryList();
    }

    public List<ParkingInfo> getAllParkingInfoList() {
        return repository.findAll();
    }

    public Optional<ParkingInfo> getParkingInfoByCode(String parkingCode) {
        return repository.selectGroupByQueryOne(parkingCode);
    }
}

