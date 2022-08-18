package com.skuniv.cgvr.controller.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserFormDto;
import com.skuniv.cgvr.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/*  org.json.* 라이브러리 사용을 위해
    build.gradle에 implementation group: 'org.json', name: 'json', version: '20220320' 추가하였음
 */

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private static final String loginURL = "https://sportal.skuniv.ac.kr/sportal/auth2/login.sku";
    private static final String lectURL = "https://sportal.skuniv.ac.kr/sportal/common/selectList.sku";

    class UserData {    // for Login
        String accessToken; // bearing token
        String name;        // Korean Name
        String schyr;       // grade
        String email;       // e-mail
        String contact;     // phone_number
        ArrayList<String> yearList = new ArrayList<>();
    }
    class LectureData {     // for Login
        // 강의정보
        String cpp = "";         // c++ year (1학기) CE1102
        String mfc = "";         // mfc year (2학기) CE1047
        String cg = "";          // graphics year (1학기) CE1053
        String ar = "";          // vr/ar year (2학기) CE1168 -> 수업명 바뀌어서 학수번호 바뀜
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password) throws IOException {
        // 포탈 로그인
        UserData userData = getUserData(username, password);
        // 확인용 출력문
        System.out.println("acessToken = " + userData.accessToken);
        System.out.println("name = " + userData.name);
        System.out.println("schyr = " + userData.schyr);
        System.out.println("email = " + userData.email);
        System.out.println("contact = " + userData.contact);

        // 강의 정보
        LectureData lectureData = getLectureData(username, userData.accessToken, userData.yearList);
        // 확인용 출력문
        System.out.println("C++ 프로그래밍 = " + lectureData.cpp);
        System.out.println("비주얼 객체지향 프로그래밍 = " + lectureData.mfc);
        System.out.println("컴퓨터 그래픽스 = " + lectureData.cg);
        System.out.println("VR/AR = " + lectureData.ar);

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserFormDto userFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            User user = User.createUser(userFormDto, passwordEncoder);
            userService.createUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        return "redirect:/signin";
    }

    public UserData getUserData(String username, String password) {
        UserData userData = new UserData();
        JSONObject response;
        // 포탈 로그인
        JSONObject payload = new JSONObject();

        payload.put("username", username);
        payload.put("password", password);
        payload.put("grant_type", "password");
        payload.put("userType", "sku");

        response = Connector.getInstance().getResponse(loginURL, payload);
        // 로그인 성공 실패 유무 출력
        if (response.get("RTN_STATUS").toString().equals("S")) {
            System.out.println("Login Success!");
        } else {
            System.out.println("Login Failed!");
        }
        userData.accessToken = response.get("access_token").toString();
        userData.name = response.getJSONObject("USER_INFO").get("KOR_NAME").toString();
        userData.schyr = response.getJSONObject("USER_INFO").get("SCHYR").toString();
        userData.email = response.getJSONObject("USER_INFO").get("EMAIL").toString();
        userData.contact = response.getJSONObject("USER_INFO").get("PHONE_MOBILE").toString();
        for(int i = 0; i < response.getJSONArray("YEAR_LIST").length(); i++) {
            userData.yearList.add(response.getJSONArray("YEAR_LIST").getJSONObject(i).get("value").toString());
        }
        return userData;
    }
    public LectureData getLectureData(String username, String accessToken, ArrayList<String> yearList) {
        JSONObject response;
        LectureData lectureData = new LectureData();
        for(String year : yearList) {
            for(int term = 1; term <= 2; term++) {
                JSONObject payload;
                JSONObject parameter;

                parameter = new JSONObject();
                parameter.put("ID", username);
                parameter.put("LECT_YEAR", year);
                parameter.put("LECT_TERM", term);
                parameter.put("STNT_NUMB", username);

                payload = new JSONObject();
                payload.put("MAP_ID", "education.ual.UAL_04004_T.select");
                payload.put("SYS_ID", "AL");
                payload.put("accessToken", accessToken);
                payload.put("parameter", parameter);
                payload.put("path", "common/selectList");
                payload.put("programID", "UAL_04004_T");
                payload.put("userID", username);

                response = Connector.getInstance().getResponseWithToken(lectURL, accessToken, payload);
                if (response.get("RTN_STATUS").toString().equals("S")) {
                    // 수강한 모든 수업을 순회하면서, 가장 최근에 들은 김태영 교수님 수업 년도 조회
                    for (int i = 0; i < response.getJSONArray("LIST").length(); i++) {
                        if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1102")) {
                            if (lectureData.cpp.equals("")) lectureData.cpp = year;
                        } else if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1047")) {
                            if (lectureData.mfc.equals("")) lectureData.mfc = year;
                        } else if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1053")) {
                            if (lectureData.cg.equals("")) lectureData.cg = year;
                        } else if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1168")) {
                            if (lectureData.ar.equals("")) lectureData.ar = year;
                        }
                    }
                } else {
                    System.out.println(year + "-" + term + " Lecture info 없음!");
                }
            }
        }
        return lectureData;
    }
}
