package com.fastcampus.ch3.di3;

import com.fastcampus.ch3.di3.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

@Component
class Car {
    Engine engine;
    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

@Component
class Engine {}
@Component
class Door {}


public class Main {
    public static void main(String[] args) {
        // AC를 생성 = AC의 설정파일은 AppConfig.class 자바 설정
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//        Car car = (Car)ac.getBean("car"); // byName 객체(빈)을 조회
//        Car car = ac.getBean("car", Car.class); // 위와 동일. 형변환 귀찮으면 뒤에 클래스 지정
//        Engine engine = ac.getBean(Engine.class);
//        Engine engine2 = ac.getBean(Engine.class);
//        Engine engine3 = ac.getBean(Engine.class);
//        System.out.println("car = " + car);
//        System.out.println("engine = " + engine);
//        System.out.println("engine2 = " + engine2);
//        System.out.println("engine3 = " + engine3);

        SysInfo info = ac.getBean(SysInfo.class);
        System.out.println("info = " + info);
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        System.out.println("ac.getBeanDefinitionNames() = " + ac.getBeanDefinitionNames());
//        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
//        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car"));
//        System.out.println("ac.isPrototype(\"engine\") = " + ac.isPrototype("engine"));

        Map<String, String> env = System.getenv();
        System.out.println("System.getenv() = " + env);

        Properties prop = System.getProperties();
        System.out.println("System.getProperties = " + prop);

        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
    }
}
