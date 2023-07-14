package com.fastcampus.ch2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

// 1. 원격 프로그램으로 등록
@Controller
public class HomeController { // 원격 프로그램
    // 2. URL과 메소드를 연결
    @RequestMapping("/")
    public String main() {
        return "index"; // templates/index.html
    }

    @GetMapping("/test")
    public String test(Model model, HttpServletRequest request) {
        request.setAttribute("year", 2022); // request 객체에 저장

        HttpSession session = request.getSession(); // 요청으로부터 session 객체를 얻어온다.
        session.setAttribute("id", "asdf"); // session객체에 id를 저장

        ServletContext application = session.getServletContext();
        application.setAttribute("email", "jyeon@aaa.com");

        model.addAttribute("lastName", "Jyeon");
        model.addAttribute("firstName", "C");
        model.addAttribute("list", Arrays.asList("aaa","bbb","ccc","ddd","eee"));
        model.addAttribute("bno", "123");
        model.addAttribute("user", new User("aaa",20));

        return "test"; // templates/test.html
    }
}

