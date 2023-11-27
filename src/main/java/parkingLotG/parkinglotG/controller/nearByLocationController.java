package parkingLotG.parkinglotG.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import parkingLotG.parkinglotG.domain.ParkingInfo;
import parkingLotG.parkinglotG.service.nearByLocationService;

import java.util.List;

@Slf4j
@RestController


public class nearByLocationController {
    private final nearByLocationService locationService; // 서비스 추가

    @Autowired
    public nearByLocationController(nearByLocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/near", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ParkingInfo> userData() {
        return locationService.getNearbyParkingInfo();
    }

    @GetMapping(value = "/near/{lat}/{lng}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParkingInfo> userData2(@PathVariable Double lat, @PathVariable Double lng) {
        return locationService.getNearbyParkingInfo(lat, lng);
    }

}
