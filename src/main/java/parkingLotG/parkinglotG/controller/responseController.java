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
    @GetMapping(value = "/response/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<TestDomain> userData() {
        List<TestDomain> testDomains = repository.findAll();
        TestDomain testDomain = new TestDomain(1,"123","hello", 20,20,20,20);

        return testDomains;
    }

    @GetMapping(value = "/response/one", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<TestDomain> userData1() {

        Optional<TestDomain> testDomain = repository.findById(746L);

        return testDomain;
    }

    //동적으로 조회하기
    @GetMapping(value = "/response/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<TestDomain> userData1(@PathVariable Long id) {
        Optional<TestDomain> testDomain = repository.findById(id);
        return testDomain;
    }
}


