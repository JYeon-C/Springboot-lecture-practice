package com.fastcampus.ch2;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. 원격 프로그램으로 등록
@Controller
public class HomeController { // 원격 프로그램
    // 2. URL과 메소드를 연결
    @RequestMapping("/")
    public String main() {
        return "index"; // templates/index.html
    }
}

