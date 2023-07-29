package com.fastcampus.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.util.Properties;

class Car {
    @Autowired
    Engine engine;

    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
class Engine {}
class Door{}
class SportsCar extends Car {}
class Truck extends Car {}

public class Main {
    public static void main(String[] args) throws Exception{
        Car car = (Car)getObject("car");
        System.out.println("car = " + car);
    }
    static Object getObject(String key) throws Exception{
        Properties prop = new Properties();
        Class clazz = null;

        prop.load(new FileReader("config.txt"));
        String className = prop.getProperty(key);
        clazz = Class.forName(className);

        return clazz.newInstance();

    }

    static Car getCar() {
        return new Truck(); // getCar(); 를 호출하는 부분이 많아도 여기만 수정하면 된다.
    }
}