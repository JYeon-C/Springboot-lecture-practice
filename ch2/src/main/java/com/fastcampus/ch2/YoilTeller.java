package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

// 년월일을 입력하면 요일을 알려주는 원격 프로그램
@Controller
public class YoilTeller {
    @RequestMapping("/yoil")
    public ModelAndView main(int year, int month, int day) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("yoilError");

        if(!isValue(year, month, day)) {
            return mv;
        }

        // 2. 작업 - 요일을 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear(); // cal의 모든 필드를 초기화(정확한 계산을 위해)
        cal.set(year, month-1, day); // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1-7을 반환. 1 : 일요일, 2 : 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // 배열은 0부터 시작하니 1을 빼줌. 1~7 -> 0~6

        // 작업한 결과를 Model에 저장(DispatcherServlet이 Model을 View로 전달)
        mv.addObject("year", year);
        mv.addObject("month", month);
        mv.addObject("day", day);
        mv.addObject("yoil", yoil);
        mv.setViewName("yoil");

        return mv;

    }

    private boolean isValue(int year, int month, int day) {
        return true;
    }
}
