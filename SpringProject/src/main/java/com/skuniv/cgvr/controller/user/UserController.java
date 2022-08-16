package com.skuniv.cgvr.controller.user;

import com.skuniv.cgvr.domain.user.User;
import com.skuniv.cgvr.dto.user.UserFormDto;
import com.skuniv.cgvr.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpRequest;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password){
        try {
            URL url = new URL("https://sportal.skuniv.ac.kr/sportal/auth2/login.sku");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("userType", "sku");
            conn.setRequestProperty("grant_type", "password");
            conn.setRequestProperty("username", username);
            conn.setRequestProperty("password", password);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            // Request
            String requestData = "username=" + URLEncoder.encode(username)
                    + "&password=" + URLEncoder.encode(password);
            DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
            outputStream.write(requestData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();


            // Response
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            System.out.println("Connection Check Point1");

            String line = null;
            String response = null;
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }
            conn.disconnect();
            bufferedReader.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (MalformedURLException e) {
            System.out.println("URL Error StackTrace: ");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("URL Connection Error StackTrace: ");
            System.out.println(e);
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
