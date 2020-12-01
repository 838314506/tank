package com.myself.tank.design;

/**
 * 静态内部类写法
 */
public class SingleStaticClass {

    private SingleStaticClass(){}

    private static class SingleInstance{
        private static final SingleStaticClass INSTANCE = new SingleStaticClass();
    }

    public static SingleStaticClass getInstance(){
        return SingleInstance.INSTANCE;
    }
}
