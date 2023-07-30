package com.fastcampus.ch3.di4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.relational.core.sql.TrueCondition;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

class Car {
    public String toString() {
        return "Car{}";
    }
}

class SportsCar extends Car {
    public String toString() {
        return "SportsCar{}";
    }
}

class SportsCar2 extends Car {
    public String toString() {
        return "SportsCar2{}";
    }
}

@Component
@Conditional(FalseCondition.class)
class Engine {
    @Override
    public String toString() {
        return "Engine{}";
    }
}

@Component
@Conditional(OSCondition.class)
class Door {
    @Override
    public String toString() {
        return "Door{}";
    }
}
class OSCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.getProperty("sun.desktop").equals("windows");
    }
}

class FalseCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}

//@Import({Config1.class, Config2.class})

//@Import(MyImportSelector.class)

@EnableMyAutoConfiguration("test")
class MainConfig{ @Bean Car car() {return new Car();}}

class Config1{ @Bean Car sportsCar() {return new SportsCar();}}

class Config2{ @Bean Car sportsCar() {return new SportsCar2();}}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImportSelector.class)
@interface EnableMyAutoConfiguration {
    String value() default "";
}

class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attr =
                AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyAutoConfiguration.class.getName(), false));
//        String mode = "test";
        String mode = attr.getString("value");
        if(mode.equals("test"))
        return new String[]{Config1.class.getName()};  // import할 클래스 지정
        else
            return new String[]{Config2.class.getName()};  // import할 클래스 지정
    }
}


//@SpringBootApplication // 은 아래의 3개 애너테이션을 붙인것과 동일
@Configuration
//@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String[] args) {
//        ApplicationContext ac = SpringApplication.run(Main.class, args);
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, Config1.class, Config2.class);
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        System.out.println("ac = " + ac);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames); // 빈 목록이 담긴 배열을 정렬
        Arrays.stream(beanDefinitionNames) // 배열의 스트림을 반환
                .filter(b->!b.startsWith("org"))
                .forEach(System.out::println); // 스트림의 요소를 하나씩 꺼내서 출력

        System.out.println("ac.getBean(\"sportsCar\") = " + ac.getBean("sportsCar"));
    }

    @Bean
    MyBean myBean() {return new MyBean();}
}

    class MyBean {}