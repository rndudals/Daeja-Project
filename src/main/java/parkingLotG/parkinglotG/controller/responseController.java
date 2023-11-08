package parkingLotG.parkinglotG.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import parkingLotG.parkinglotG.domain.TestDomain;
import parkingLotG.parkinglotG.repository.testDomainRepository;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
public class responseController {
    @Autowired
    private testDomainRepository repository;


    @GetMapping(value = "/response", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDomain> userData() {
        List<TestDomain> testDomains = repository.selectGroupByQueryList();
        TestDomain testDomain = new TestDomain("123","hello", 20,20,20,20);
        return testDomains;
    }

    @GetMapping(value = "/response/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDomain> userData1() {
        List<TestDomain> testDomains = repository.findAll();
        TestDomain testDomain = new TestDomain("123","hello", 20,20,20,20);
        return testDomains;
    }
    //동적으로 조회하기

    @GetMapping(value = "/response/{parking_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<TestDomain> userData2(@PathVariable String parking_code) {
        Optional<TestDomain> testDomain = repository.selectGroupByQueryOne(parking_code);
        return testDomain;
    }


}


