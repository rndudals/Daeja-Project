package parkingLotG.parkinglotG.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import parkingLotG.parkinglotG.domain.TestDomain;
import parkingLotG.parkinglotG.repository.nearByLocationRepository;

import java.util.List;

@Slf4j
@RestController
public class nearByLocationController {
    @Autowired
    private nearByLocationRepository repository;

    @GetMapping(value = "/near", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDomain> userData() {
        List<TestDomain> testDomains = repository.staticFindNearbyLocations();
        return testDomains;
    }
    @GetMapping(value = "/near/{lat}/{lng}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestDomain> userData1(Double lat, Double lng) {
        List<TestDomain> testDomains = repository.findNearbyLocations(lat, lng);
        return testDomains;
    }

}
