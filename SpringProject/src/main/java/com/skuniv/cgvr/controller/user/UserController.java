package com.skuniv.cgvr.controller.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserFormDto;
import com.skuniv.cgvr.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
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
    HttpURLConnection connection;
    private static final String loginURL = "https://sportal.skuniv.ac.kr/sportal/auth2/login.sku";
    private static final String lectURL = "https://sportal.skuniv.ac.kr/sportal/common/selectList.sku";

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password) throws IOException {
        // 로그인
        String accessToken; // bearing token
        String name;        // Korean Name
        String schyr;       // grade
        String email;       // e-mail
        String contact;     // phone_number
        ArrayList<String> yearList = new ArrayList<>();

        // 강의정보
        String cpp = "";         // c++ year (1학기) CE1102
        String mfc = "";         // mfc year (2학기) CE1047
        String cg = "";          // graphics year (1학기) CE1053
        String ar = "";          // vr/ar year (2학기) CE1168

        try {
            // 포탈 로그인
            JSONObject payload = new JSONObject();
            try {
                payload.put("username", username);
                payload.put("password", password);
                payload.put("grant_type", "password");
                payload.put("userType", "sku");
            } catch(JSONException e) {
                e.printStackTrace();
            }
            JSONObject response = Connector.getInstance().getResponse(loginURL, payload);
            // 로그인 성공 실패 유무 출력
            if(response.get("RTN_STATUS").toString().equals("S")) {
                System.out.println("Login Success!");
            }
            else {
                System.out.println("Login Failed!");
            }
            // 유저 정보 및 베어링토큰 저장
            accessToken = response.get("access_token").toString();
            name = response.getJSONObject("USER_INFO").get("KOR_NAME").toString();
            schyr = response.getJSONObject("USER_INFO").get("SCHYR").toString();
            email = response.getJSONObject("USER_INFO").get("EMAIL").toString();
            contact = response.getJSONObject("USER_INFO").get("PHONE_MOBILE").toString();
            for(int i = 0; i < response.getJSONArray("YEAR_LIST").length(); i++) {
                yearList.add(response.getJSONArray("YEAR_LIST").getJSONObject(i).get("value").toString());
            }
            // 확인용 출력문
            System.out.println("acessToken = " + accessToken);
            System.out.println("name = " + name);
            System.out.println("schyr = " + schyr);
            System.out.println("email = " + email);
            System.out.println("contact = " + contact);

            // 년도,학기 별 수업 수강여부
            for(String year : yearList) {
                for(int term = 1; term <= 2; term++) {
                    try {
                        JSONObject parameter = new JSONObject();
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
                        if(response.get("RTN_STATUS").toString().equals("S")) {
                            for(int i = 0; i < response.getJSONArray("LIST").length(); i++) {
                                if(response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1102")){
                                    if(cpp.equals("")) cpp = year;
                                } else if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1047")) {
                                    if(mfc.equals("")) mfc = year;
                                } else if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1053")) {
                                    if(cg.equals("")) cg = year;
                                } else if (response.getJSONArray("LIST").getJSONObject(i).get("SUBJ_CD").toString().equals("CE1168")) {
                                    if(ar.equals("")) ar = year;
                                }
                            }
                        }
                        else {
                            System.out.println(year + "-" + term + " Lecture info 없음!");
                        }
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("cpp = " + cpp);
            System.out.println("mfc = " + mfc);
            System.out.println("cg = " + cg);
            System.out.println("ar = " + ar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
}
