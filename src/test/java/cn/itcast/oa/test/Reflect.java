package cn.itcast.oa.test;

import java.lang.reflect.ParameterizedType;

public class Reflect<T> {

    private Class<T> clazz;

    public Reflect (){
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
        System.out.println(clazz);
    }
}
