package com.myself.tank.resourceMge;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyMgr {

    private static volatile PropertyMgr pm = null;

    private PropertyMgr(){}

    public static PropertyMgr getInstance(){
        if (pm == null){
            synchronized (PropertyMgr.class){
                if (pm == null){
                    pm = new PropertyMgr();
                }
            }
        }
        return pm;
    }

    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (Objects.isNull(key)) return null;
        return properties.get(key);
    }

}
