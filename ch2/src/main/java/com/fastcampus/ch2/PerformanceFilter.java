package com.fastcampus.ch2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*") // 모든 요청에 PerformanceFilter 적용.
public class PerformanceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();

        // 2. 서블릿(컨트롤러)또는 다음 필터
        filterChain.doFilter(request, response);

        // 3. 후처리 작업
        long endTime = System.currentTimeMillis();
        System.out.print("["+ ((HttpServletRequest)request).getRequestURI() + "]"); // 어떤걸 호출했을 때 소요시간이 얼마인지 알도록 함
        System.out.println("time=" + (endTime-startTime));

    }
}

