package com.an.mybit.login.controller;

import com.an.mybit.login.service.LoginService;
import com.an.mybit.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    private static final String COOKIE_NAME = "mybitCookie";

    @PostMapping
    public ResponseEntity<?> login(UserDTO userDTO, HttpServletResponse response){

        log.info("login = {}", userDTO);

        try {
            loginService.login(userDTO);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        String cookieValue = makeSessionId();
        Cookie cookie = new Cookie(COOKIE_NAME, cookieValue);
        cookie.setPath("/");
        response.addCookie(cookie);

        log.info("쿠키생성 = {}", cookieValue);
        return ResponseEntity.ok().body("ok");
    }

    public String makeSessionId(){
        return "mybit" + UUID.randomUUID().toString();
    }
}
