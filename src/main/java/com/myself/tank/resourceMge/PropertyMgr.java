package com.myself.tank.resourceMge;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyMgr {

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
