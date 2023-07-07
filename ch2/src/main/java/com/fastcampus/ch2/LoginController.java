package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;

@Controller
@RequestMapping ("/login")// 클래스 앞에 @RequestMapping("/login") 써주면 /login/login -> /login으로 생략 가능
public class LoginController {
//    @RequestMapping(value = "/login/login", method = RequestMethod.GET) // 아래와 같다. 기본은 GET
//    @RequestMapping("/login/login") // 더 짧게 아래 문장과 같이 사용 가능
    @GetMapping("/login")
    public String showLogin() {
        return "login"; // login.html
    }

    //  하나의 메소드로 GET, POST 둘 다 처리하는 경우
    //    @RequestMapping(value = "/login/login", method = {RequestMethod.POST,RequestMethod.GET})
    //    @RequestMapping(value = "/login/login", method = RequestMethod.POST) // 아래와 같다.
    @PostMapping("/login")
    public String login(String id, String pwd, Model model) throws Exception{
        // 1. id, pwd를 확인
        if(loginCheck(id,pwd)) {
            // 2. 일치하면, userInfo.html
            model.addAttribute("id", id);
            model.addAttribute("pwd", pwd);
            return "userInfo"; // userInfo.html
        } else {
            // 일치하지 않으면, login.html로 이동
            String msg = URLEncoder.encode("id 또는 패스워드가 일치하지 않습니다.", "utf-8");
            return "redirect:/login/login?msg=" + msg; // redirect는 GET요쳥 -> GetMapping("/login") 받음
        }
    }

    private boolean loginCheck(String id, String pwd) {
        return id.equals("asdf") && pwd.equals("1234");
    }
}
