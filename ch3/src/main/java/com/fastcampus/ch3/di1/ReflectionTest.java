package com.fastcampus.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        Class carClass = car.getClass(); // 1. 객체로부터 Class객체(설계도) 얻기
        carClass = Car.class; // 2.객체 리터럴로부터 Class객체 얻기
        carClass = Class.forName("com.fastcampus.ch3.di1.Car");

        // 1. 설계도 객체로부터 객체 생성하기
        Car car2 = (Car)carClass.newInstance();
        System.out.println("car2 = " + car2);


        // 2. 클래스에 선언된 멤버변수(field)와 method목록 열기
        Field[] mvArr = carClass.getDeclaredFields();
//        Method[] methodArr = carClass.getDeclaredMethods();
        Method[] methodArr = carClass.getMethods();

        for(Field mv : mvArr) System.out.println(mv.getName());
        for(Method method : methodArr) System.out.println(method.getName());

        // 정보를 메소드 객체에 담아서 반환
        Method method = carClass.getMethod("setEngine", Engine.class);
        // 호출
        method.invoke(car, new Engine()); // car.setEngine(new Engine());
        System.out.println("car = " + car);

        // 3. mv에 set을 붙여서 setter를 호출하기
        for(Field mv : mvArr) {
            System.out.println("mv = " + mv);
            String methodName = "set" + StringUtils.capitalize(mv.getName()); // "set" + "Engine" = "setEngine"
            System.out.println("methodName = " + methodName);
            method = carClass.getMethod(methodName, mv.getType()); // carClass.getMethod("setEngine", Engine.class);
            method.invoke(car, mv.getType().newInstance()); // car.setEngine(new Engine()), car.setDoor(new Door())
        }
        System.out.println("car = " + car);
        for(Field mv : mvArr){
            Annotation[] annoArr = mv.getDeclaredAnnotations();
            for(Annotation anno : annoArr) {
                System.out.println("mv.getName() = " + mv.getName());
                System.out.println("anno.annotationType().getSimpleName() = " + anno.annotationType().getSimpleName());
                System.out.println(anno.annotationType() == Autowired.class);
            }
        }
        car = new Car();

        for(Field mv : mvArr) {
            Annotation[] annoArr = mv.getDeclaredAnnotations();
            for(Annotation anno : annoArr) {
                System.out.println("mv.getName() = " + mv.getName());
                if(anno.annotationType() == Autowired.class){
                    // setter 호출
                    String methodName = "set" + StringUtils.capitalize(mv.getName()); // "setEngine";
                    method = carClass.getMethod(methodName, mv.getType());
                    method.invoke(car, mv.getType().newInstance());// car.setEngine(new Engine())
                }
            }
        }
        System.out.println("car = " + car);
    }
}
