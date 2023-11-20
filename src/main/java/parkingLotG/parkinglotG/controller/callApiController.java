package parkingLotG.parkinglotG.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parkingLotG.parkinglotG.domain.ParkingInfo;
import parkingLotG.parkinglotG.repository.responseRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
public class callApiController {
    @Autowired
    private responseRepository tdrepository;

    @RequestMapping(value="/api",produces="application/json;charset=utf-8")
    public String func() throws IOException, ParseException {
        tdrepository.deleteAll();
        for (int i = 1; i < 1000; i = i + 975) {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/657843584a726e3933394f4376434e/json/GetParkingInfo/"+i+"/"+(i+974)+"/");
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
                Double LAT = (Double) parkingData.get("LAT");
                Double LNG = (Double) parkingData.get("LNG");

                //추가
                String PARKING_ADDR = (String) parkingData.get("ADDR");
                String PARKING_TYPE_NM = (String) parkingData.get("PARKING_TYPE_NM");
                String OPERATION_RULE_NM = (String) parkingData.get("OPERATION_RULE_NM");
                String TEL = (String) parkingData.get("TEL");

                // 위까지 성공


                String CURRENT_PARKING_UPDATE_TIME = (String) parkingData.get("CUR_PARKING_TIME");
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
                Double rates = (Double) parkingData.get("RATES");
                String RATES = String.valueOf(rates);

                Double time_rate = (Double) parkingData.get("TIME_RATE");
                String TIME_RATE = String.valueOf(time_rate);

                Double add_rates = (Double) parkingData.get("ADD_RATES");
                String ADD_RATES = String.valueOf(add_rates);

                Double add_time_rate = (Double) parkingData.get("ADD_TIME_RATE");
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
                ParkingInfo testDomain = new ParkingInfo();

                testDomain.setPARKING_CODE(PARKING_CODE);
                testDomain.setPARKING_NAME(PARKING_NAME);
                testDomain.setCAPACITY(Integer.parseInt(String.valueOf(Math.round(CAPACITY))));
                testDomain.setCUR_PARKING(Integer.parseInt(String.valueOf(Math.round(CUR_PARKING))));
                testDomain.setLAT(LAT);
                testDomain.setLNG(LNG);
                String color = "null";
                if (CUR_PARKING / CAPACITY < 0.3) {
                    color = "많음";
                } else if (CUR_PARKING / CAPACITY < 0.6) {
                    color = "보통";
                } else {
                    color = "적음";
                }
                testDomain.setCOLOR(color);


                // 추가
                testDomain.setPARKING_ADDR(PARKING_ADDR);
                testDomain.setPARKING_TYPE_NM(PARKING_TYPE_NM);
                testDomain.setOPERATION_RULE_NM(OPERATION_RULE_NM);
                testDomain.setTEL(TEL);


                testDomain.setQUE_STATUS(QUE_STATUS);
                testDomain.setCUR_PARKING_TIME(CURRENT_PARKING_UPDATE_TIME);
                testDomain.setPAY_NM(PAY_NM);
                testDomain.setNIGHT_FREE_OPEN_NM(NIGHT_FREE_OPEN_NM);
                testDomain.setWEEKDAY_BEGIN_TIME(WEEKDAY_BEGIN_TIME);
                testDomain.setWEEKDAY_END_TIME(WEEKDAY_END_TIME);
                testDomain.setWEEKEND_BEGIN_TIME(WEEKEND_BEGIN_TIME);
                testDomain.setWEEKEND_END_TIME(WEEKEND_END_TIME);
                testDomain.setHOLIDAY_BEGIN_TIME(HOLIDAY_BEGIN_TIME);
                testDomain.setHOLIDAY_END_TIME(HOLIDAY_END_TIME);
                testDomain.setSATURDAY_PAY_NM(SATURDAY_PAY_NM);
                testDomain.setHOLIDAY_PAY_NM(HOLIDAY_PAY_NM);
                testDomain.setFULLTIME_MONTHLY(FULLTIME_MONTHLY);
                testDomain.setRATES(RATES);
                testDomain.setTIME_RATE(TIME_RATE);
                testDomain.setADD_RATES(ADD_RATES);
                testDomain.setADD_TIME_RATE(ADD_TIME_RATE);
                testDomain.setDAY_MAXIMUM(DAY_MAXIMUM);
                testDomain.setSH_CO(SH_CO);
                testDomain.setSH_LINK(SH_LINK);
                testDomain.setSH_TYPE(SH_TYPE);


                // JPA를 사용하여 데이터베이스에 저장
                tdrepository.save(testDomain);
            }

        }
        // 현재 시간을 가져옵니다.
        Date currentDate = new Date();

        // 출력할 형식을 지정합니다.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        // Random 객체 생성
        Random random = new Random();
        // 정수형 랜덤 값 출력 (0 이상부터)
        int randomNumber0 = random.nextInt(157);
        ParkingInfo dankook= new ParkingInfo(0,"단국대학교 죽전캠퍼스 주차빌딩 1", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 156, randomNumber0, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "0000","0000","0000", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.32036865101858,127.124970592852, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook);

        int randomNumber1 = random.nextInt(77);
        ParkingInfo dankook1= new ParkingInfo(1,"단국대학교 죽전캠퍼스 주차장10", "경기 용인시 수지구 죽전동 산 46-2", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 76, randomNumber1, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "0000","0000","0000", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.31977371980491,	127.12520652760476, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook1);

        int randomNumber2 = random.nextInt(81);
        ParkingInfo dankook2= new ParkingInfo(2,"단국대학교 죽전캠퍼스 주차장9", "경기 용인시 수지구 죽전동 산 47", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 80, randomNumber2, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.31967375608254,	127.12600737528392, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook);

        int randomNumber3 = random.nextInt(96);
        ParkingInfo dankook3= new ParkingInfo(3,"단국대학교 죽전캠퍼스 주차장4", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 95, randomNumber3, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.31933331047667,	127.12838727290409, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook3);

        int randomNumber4 = random.nextInt(121);
        ParkingInfo dankook4= new ParkingInfo(4,"단국대학교 죽전캠퍼스 주차장1", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 120, randomNumber4, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.31940610221102,	127.13183963969811, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook4);

        int randomNumber5 = random.nextInt(87);
        ParkingInfo dankook5= new ParkingInfo(5,"단국대학교 죽전캠퍼스 주차장5", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 86, randomNumber5, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.321426558249776,	127.12990264064867, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook5);

        int randomNumber6 = random.nextInt(51);
        ParkingInfo dankook6= new ParkingInfo(6,"단국대학교 죽전캠퍼스 주차장8", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 50, randomNumber6, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.322031801113695,	127.1284821211098, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook6);

        int randomNumber7 = random.nextInt(43);
        ParkingInfo dankook7= new ParkingInfo(7,"단국대학교 죽전캠퍼스 주차장", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 42, randomNumber7, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.322113687901506,	127.12774891460344, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook7);

        int randomNumber8 = random.nextInt(82);
        ParkingInfo dankook8= new ParkingInfo(8,"단국대학교 죽전캠퍼스 주차장6", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 81, randomNumber8, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료", 1, "400.0", "5.0", "80.0", "5.0", 1, 37.322871500761224,	127.12687017342562, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook8);

        int randomNumber9 = random.nextInt(92);
        ParkingInfo dankook9= new ParkingInfo(9,"단국대학교 기숙사 주차장", "경기 용인시 기흥구 마북로247번길 109", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 91, randomNumber9, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.31520329827193,	127.12725212742195, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook9);

        int randomNumber10 = random.nextInt(202);
        ParkingInfo dankook10= new ParkingInfo(10,"단국대학교 ICT관 신설주차장", "경기 용인시 수지구 죽전로 152", "단국대학교 주차장", "시간제 주차장", "031-1234-1234", true, 201, randomNumber10, formattedDate, "유료", "야간 개방","0000", "2400", "0000", "2400","0000","2400", "유료", "유료",1, "400.0", "5.0", "80.0", "5.0", 1, 37.32342462093351,	127.12781318708828, "단국대학교 주차관리실","https://www.dankook.ac.kr/web/kor/-68", false, "많음");
        tdrepository.save(dankook10);

        return "OK"; // 문자열로 변환
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