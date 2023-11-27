package parkingLotG.parkinglotG.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import parkingLotG.parkinglotG.domain.ParkingInfo;
import parkingLotG.parkinglotG.repository.responseRepository;
import parkingLotG.parkinglotG.service.ResponseService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
public class responseController {
    private final ResponseService responseService;

    @Autowired
    public responseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ParkingInfo> userData() {
        return responseService.getGroupedParkingInfoList();
    }

    @GetMapping(value = "/response/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ParkingInfo> userData1() {
        return responseService.getAllParkingInfoList();
    }

    @GetMapping(value = "/response/{parking_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<ParkingInfo> userData3(@PathVariable String parking_code) {
        return responseService.getParkingInfoByCode(parking_code);
    }

}


