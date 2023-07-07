package com.fastcampus.ch2;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PerformanceInterceptor implements HandlerInterceptor { // 단일 책임의 원칙(SRP) - 하나의 메소드는 하나의 책임만 갖는다.
//    long startTime; // iv  인스턴스 변수. 싱글톤(하나의 객체)이라서 여러 쓰레드가 하나의 객체를 공유.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime); // request객체에 startTime을 저장

        // handler - 요청하고 연결된 컨트롤러의 메소드
        HandlerMethod method = (HandlerMethod) handler;
        System.out.println("method.getMethod() = " + method.getMethod()); // URL하고 연결된 메소드
        System.out.println("method.getBean() = " + method.getBean()); // URL하고 연결된 메소드

        // return true; // 다음 인터셉터나 컨트롤러를 호출 false면 호출 안함.
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 2. 후처리 작업
        long startTime = (long)request.getAttribute("startTime"); // 객체를 저장하기 때문에 long타입 형변환 필요
        long endTime = System.currentTimeMillis();
        System.out.print("["+ ((HttpServletRequest)request).getRequestURI() + "]"); // 어떤걸 호출했을 때 소요시간이 얼마인지 알도록 함
        System.out.println("time=" + (endTime-startTime));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
