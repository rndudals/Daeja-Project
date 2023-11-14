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
                color="G";
            }
            else if(CUR_PARKING/CAPACITY<0.6){
                color="Y";
            }
            else{
                color="R";
            }

            testDomain.setColor(color);

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