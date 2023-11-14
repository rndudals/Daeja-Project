package parkingLotG.parkinglotG.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parkingLotG.parkinglotG.domain.TestDomain;
import parkingLotG.parkinglotG.repository.responseRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class testDomainApiController {
    @Autowired
    private responseRepository tdrepository;

    @RequestMapping(value="/api",produces="application/json;charset=utf-8")
    public String func() throws IOException, ParseException {
        tdrepository.deleteAll();
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/657843584a726e3933394f4376434e/json/GetParkingInfo/1/1000/");
        urlBuilder.append("/" + URLEncoder.encode("657843584a726e3933394f4376434e", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // UTF-8로 인코딩 설정
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8")); // UTF-8로 인코딩 설정
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
        JSONObject getParkingInfo = (JSONObject) jsonObject.get("GetParkingInfo");
        JSONArray rowArray = (JSONArray) getParkingInfo.get("row");

        for (Object obj : rowArray) {
            JSONObject parkingData = (JSONObject) obj;

            Integer PARKING_CODE = Integer.parseInt((String) parkingData.get("PARKING_CODE"));
            String PARKING_NAME = (String) parkingData.get("PARKING_NAME");
            Double CAPACITY = (Double) parkingData.get("CAPACITY");
            Double CUR_PARKING = (Double) parkingData.get("CUR_PARKING");
            Double LAT = (Double)parkingData.get("LAT");
            Double LNG = (Double)parkingData.get("LNG");

            //추가
            String PARKING_ADDR = (String) parkingData.get("PARKING_ADDR");
            String PARKING_TYPE_NM = (String) parkingData.get("PARKING_TYPE_NM");
            String OPERATION_RULE_NM = (String) parkingData.get("OPERATION_RULE_NM");
            String TEL = (String) parkingData.get("TEL");

            // 위까지 성공


            String CURRENT_PARKING_UPDATE_TIME = (String) parkingData.get("CURRENT_PARKING_UPDATE_TIME");
            String PAY_NM = (String) parkingData.get("PAY_NM");
            String NIGHT_FREE_OPEN_NM = (String) parkingData.get("NIGHT_FREE_OPEN_NM");
            String WEEKDAY_BEGIN_TIME = (String) parkingData.get("WEEKDAY_BEGIN_TIME");
            String WEEKDAY_END_TIME = (String) parkingData.get("WEEKDAY_END_TIME");
            String WEEKEND_BEGIN_TIME = (String) parkingData.get("WEEKEND_BEGIN_TIME");
            String WEEKEND_END_TIME = (String) parkingData.get("WEEKEND_END_TIME");
            String HOLIDAY_BEGIN_TIME = (String) parkingData.get("HOLIDAY_BEGIN_TIME");
            String HOLIDAY_END_TIME = (String) parkingData.get("HOLIDAY_END_TIME");
            String SATURDAY_PAY_NM = (String) parkingData.get("SATURDAY_PAY_NM");
            String HOLIDAY_PAY_NM = (String) parkingData.get("HOLIDAY_PAY_NM");
            String fulltimeMonthlyStr = (String) parkingData.get("FULLTIME_MONTHLY");
            Integer FULLTIME_MONTHLY = null;
            if (fulltimeMonthlyStr != null && !fulltimeMonthlyStr.isEmpty()) {
                try {
                    FULLTIME_MONTHLY = Integer.parseInt(fulltimeMonthlyStr);
                } catch (NumberFormatException e) {
                    // 숫자로 변환할 수 없는 값이 들어왔을 때의 예외 처리
                    // 예를 들어, 숫자 이외의 문자열이 입력된 경우
                    // 이에 대한 대응 방법을 작성합니다.
                }
            }
            //인티저로 변경
            Double RATES = (Double) parkingData.get("RATES");
            Double TIME_RATE = (Double) parkingData.get("TIME_RATE");
            Double add_rates = (Double) parkingData.get("ADD_RATES");
            String ADD_RATES = String.valueOf(add_rates);
            Double add_time_rate = (Double)parkingData.get("ADD_TIME_RATE");
            String ADD_TIME_RATE = String.valueOf(add_time_rate);
            Double day_maximum = (Double) parkingData.get("DAY_MAXIMUM");
            Integer DAY_MAXIMUM = Integer.parseInt(String.valueOf(Math.round(day_maximum)));
            String SH_CO = (String) parkingData.get("SH_CO");
            String SH_LINK = (String) parkingData.get("SH_LINK");
            String queStatusString = (String) parkingData.get("QUE_STATUS");
            boolean QUE_STATUS = Boolean.valueOf(queStatusString);
            String shTypeString = (String) parkingData.get("SH_TYPE");
            boolean SH_TYPE = Boolean.valueOf(shTypeString);



            // TestDomain 객체 생성
            TestDomain testDomain = new TestDomain();

            testDomain.setParkingCode(PARKING_CODE);
            testDomain.setParkingName(PARKING_NAME);
            testDomain.setCapacity(Integer.parseInt(String.valueOf(Math.round(CAPACITY))));
            testDomain.setCurParking(Integer.parseInt(String.valueOf(Math.round(CUR_PARKING))));
            testDomain.setLat(LAT);
            testDomain.setLng(LNG);
            String color="null";
            if(CUR_PARKING/CAPACITY<0.3){
                color="많음";
            }
            else if(CUR_PARKING/CAPACITY<0.6){
                color="보통";
            }
            else{
                color="적음";
            }
            testDomain.setColor(color);



            // 추가
            testDomain.setParking_addr(PARKING_ADDR);
            testDomain.setParking_type_nm(PARKING_TYPE_NM);
            testDomain.setOperation_rule_nm(OPERATION_RULE_NM);
            testDomain.setTel(TEL);





            testDomain.setQue_status(QUE_STATUS);
            testDomain.setCurrentParkingUpdateTime(CURRENT_PARKING_UPDATE_TIME);
            testDomain.setPay_nm(PAY_NM);
            testDomain.setNight_free_open_nm(NIGHT_FREE_OPEN_NM);
            testDomain.setWeekday_begin_time(WEEKDAY_BEGIN_TIME);
            testDomain.setWeekday_end_time(WEEKDAY_END_TIME);
            testDomain.setWeekend_begin_time(WEEKEND_BEGIN_TIME);
            testDomain.setWeekend_end_time(WEEKEND_END_TIME);
            testDomain.setHoliday_begin_time(HOLIDAY_BEGIN_TIME);
            testDomain.setHoliday_end_time(HOLIDAY_END_TIME);
            testDomain.setSaturday_pay_nm(SATURDAY_PAY_NM);
            testDomain.setHoliday_pay_nm(HOLIDAY_PAY_NM);
            testDomain.setFulltime_monthly(FULLTIME_MONTHLY);
            testDomain.setRates(Integer.parseInt(String.valueOf(Math.round(RATES))));
            testDomain.setTime_rate(Integer.parseInt(String.valueOf(Math.round(TIME_RATE))));
            testDomain.setAdd_rates(ADD_RATES);
            testDomain.setAdd_time_rate(ADD_TIME_RATE);
            testDomain.setDay_maximum(DAY_MAXIMUM);
            testDomain.setSh_co(SH_CO);
            testDomain.setSh_link(SH_LINK);
            testDomain.setSh_type(SH_TYPE);






            // JPA를 사용하여 데이터베이스에 저장
            tdrepository.save(testDomain);
        }

        return sb.toString(); // 문자열로 변환
    }
    @RequestMapping("/apiTest") // api호출
    public String func1() throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/657843584a726e3933394f4376434e/json/GetParkingInfo/1/5/");
        urlBuilder.append("/" + URLEncoder.encode("657843584a726e3933394f4376434e", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); // UTF-8로 인코딩 설정
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8")); // UTF-8로 인코딩 설정
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb);
        return sb.toString(); // 문자열로 변환
    }
}