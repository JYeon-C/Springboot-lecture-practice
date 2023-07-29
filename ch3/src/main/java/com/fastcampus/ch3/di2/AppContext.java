package com.fastcampus.ch3.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AppContext {
    Map map = new HashMap();

    AppContext() {
        map.put("car", new SportsCar());
        map.put("engine", new Engine());
        map.put("door", new Door());
    }

    AppContext(Class clazz) {
        Object config = null;
        try {
            config = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getDeclaredMethods();

        for(Method m : methods) {
            System.out.println("m = " + m.getName());
            for(Annotation anno : m.getDeclaredAnnotations()){
                if(anno.annotationType() == Bean.class)
                    try {
                        map.put(m.getName(), m.invoke(config, null));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                // map.put("car", new Car());
            }
        }
        doAutowired(); // @Autowired를 찾아서 빈(객체)간의 자동 연결처리
        doResource(); // @Resource를 찾아서 빈(객체)간의 자동 연결처리
    }

    private void doResource() {
        for(Object bean : map.values()) {
            for(Field fld : bean.getClass().getDeclaredFields()) {
                if(fld.getAnnotation(Resource.class)!=null)
                    try {
                        fld.set(bean, getBean(fld.getName())); // car.engine = ob;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private void doAutowired() {
        for(Object bean : map.values()) {
            for(Field fld : bean.getClass().getDeclaredFields()) {
                if(fld.getAnnotation(Autowired.class)!=null)
                    try {
                        fld.set(bean, getBean(fld.getType())); // car.engine = ob;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public Object getBean(Class clazz) {
        for(Object obj : map.values())
            if(clazz.isInstance(obj))
                return obj;
        return null;
    }

    public Object getBean(String id) {
        return map.get(id);
    }
}
