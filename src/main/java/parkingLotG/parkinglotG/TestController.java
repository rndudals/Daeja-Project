package parkingLotG.parkinglotG;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "운여준와 아이들";
    }
}
