package main.java.Util;

import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader loader;
    private Properties properties;
    private ConfigLoader(){

    }
    public static ConfigLoader getInstance(String address){
        if (loader == null){
            loader = new ConfigLoader();
        }
        return loader;
    }
}
