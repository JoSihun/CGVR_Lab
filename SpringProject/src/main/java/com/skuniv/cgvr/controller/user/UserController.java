package com.skuniv.cgvr.controller.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserFormDto;
import com.skuniv.cgvr.dto.user.UserListResponseDto;
import com.skuniv.cgvr.dto.user.UserSaveRequestDto;
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

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/*  [org.json.* Library]
    build.gradle >> implementation group: 'org.json', name: 'json', version: '20220320' 추가하였음

    [mustache template session setup]
    application.properties >> spring.mustache.expose-session-attributes=true
 */

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private static final String loginURL = "https://sportal.skuniv.ac.kr/sportal/auth2/login.sku";

    class UserData {    // for Login
        String accessToken; // bearing token
        String korName;        // Korean Name
        String grade;       // grade
        String email;       // e-mail
        String contact;     // phone_number
    }

    /* 로그인 화면이동 */
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    /* 로그인 요청 */
    @PostMapping("/signin")
    public String signin(@RequestParam String userId, @RequestParam String password, HttpSession session) throws IOException {
        // 유저 정보 가져오기
        UserData userData = getUserData(userId, password);
        // 로그인 실패시
        if(userData == null)
            return "redirect:/signin";
        // 세션 설정
        session.setAttribute("sessionUserId", userId);              // 학번
        session.setAttribute("sessionKorName", userData.korName);   // 이름
        session.setAttribute("sessionGrade", userData.grade);       // 학년
        session.setAttribute("sessionContact", userData.contact);   // 연락처
        session.setAttribute("sessionEmail", userData.email);       // 이메일
        // 권한이 ADMIN 경우에만 설정되는 세션
        List<UserListResponseDto> responseDtoList = this.userService.findAllAsc();
        for(UserListResponseDto responseDto : responseDtoList) {
            if((userId.equals(responseDto.getUserId())) && (responseDto.getRole().toString().equals("ADMIN"))) {
                session.setAttribute("admin", "ADMIN");
            }
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
            User user = User.createUser(userFormDto);
            userService.createUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }
        return "redirect:/signin";
    }

    /* 관리자 목록보기 */
    @GetMapping("/admin")
    public String admin(Model model) {
        List<UserListResponseDto> responseDtoList = this.userService.findAllAsc();
        model.addAttribute("users", responseDtoList);
        return "admin";
    }

    /* 로그아웃 */
    @GetMapping("/signout")
    public String signout(HttpSession session) {
        // 세션 제거
        session.removeAttribute("sessionUserId");
        session.removeAttribute("sessionKorName");
        session.removeAttribute("sessionGrade");
        session.removeAttribute("sessionContact");
        session.removeAttribute("sessionEmail");
        if(session.getAttribute("admin") != null)
            session.removeAttribute("admin");
        return "redirect:/";
    }

    public UserData getUserData(String userId, String passWord) {
        UserData userData = new UserData();
        JSONObject response;
        // 포탈 로그인
        JSONObject payload = new JSONObject();

        payload.put("username", userId);
        payload.put("password", passWord);
        payload.put("grant_type", "password");
        payload.put("userType", "sku");

        response = Connector.getInstance().getResponse(loginURL, payload);
        // 로그인 성공 실패 유무 출력
        if (response.get("RTN_STATUS").toString().equals("S")) {
            System.out.println("Login Success!");
        } else {
            System.out.println("Login Failed!");
            return null;
        }
        userData.accessToken = response.get("access_token").toString();
        userData.korName = response.getJSONObject("USER_INFO").get("KOR_NAME").toString();
        userData.grade = response.getJSONObject("USER_INFO").get("SCHYR").toString();
        userData.email = response.getJSONObject("USER_INFO").get("EMAIL").toString();
        userData.contact = response.getJSONObject("USER_INFO").get("PHONE_MOBILE").toString();
        return userData;
    }
}
