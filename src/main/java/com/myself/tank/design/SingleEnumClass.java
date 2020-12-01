package com.myself.tank.design;

/**
 * 不仅可以保证线程安全，还可以防止反序列化
 * 反射时生成class文件load到内存，枚举单例能够反序列化，是因为枚举类没有构造方法，拿INSTANCE后反序列化出来还是跟
 * 原来的单例相同
 * 出自effctive java作者中的写法
 * 心中有剑，手中无剑
 */
public enum SingleEnumClass {
    INSTANCE;
    
    public static void main(String[] args){
        for (int i = 0; i < 100;i ++){
            new Thread(() -> {
                System.out.println(SingleEnumClass.INSTANCE.hashCode());
            }
            ).start();
        }
    }
}
