package com.fastcampus.ch3.di1;

class Car {}

class SportsCar extends Car {}

class Truck extends Car {}
public class Main {
    public static void main(String[] args) {
        Car car = new Truck();
        car = getCar();
        System.out.println("car = " + car);
    }

    static Car getCar() {
        return new Truck(); // getCar(); 를 호출하는 부분이 많아도 여기만 수정하면 된다.
    }
}