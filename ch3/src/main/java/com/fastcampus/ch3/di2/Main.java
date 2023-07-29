package com.fastcampus.ch3.di2;


import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

class Car{
    // @Resource(name="door")
        @Resource Engine engine;
        @Autowired Door door;

        @Override
        public String toString() {
            return "Car{" +
                    "engine=" + engine +
                    ", door=" + door +
                    '}';
        }

        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public void setDoor(Door door) {
            this.door = door;
        }
    }

    class SportsCar extends Car {
        @Override
        public String toString() {
            return "SportsCar{" +
                    "engine=" + engine +
                    ", door=" + door +
                    '}';
        }
    }
    class Engine {}
    class Door {}


public class Main {
    public static void main(String[] args) {
        // AppContext(Class clazz) - 생성파일 자바클래스 지정
         AppContext ac = new AppContext(AppConfig.class);
         Car car = (Car)ac.getBean("car"); // by Name
         Car car2 = (Car)ac.getBean(Car.class); // by Name
         Engine engine = (Engine) ac.getBean("engine"); // by Name
         Door door = (Door) ac.getBean(Door.class); // by Type

        // 빈들끼리의 관계를 설정 - 수동
//        car.setEngine(engine);
//        car.setDoor(door);

         System.out.println("car = " + car);
         System.out.println("car2 = " + car2);
         System.out.println("door = " + door);
         System.out.println("engine = " + engine);
    }
}
