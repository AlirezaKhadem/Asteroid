package main.java.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Urls extends Properties {
    private static Urls urls;
    private static String defaultAddress = "resources/configFiles/logicConfigFiles/MainConfigFile";
    private Urls(String address){
        FileReader reader = null;
        try {
            reader = new FileReader(address);
            this.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("main config file doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static Urls createUrls(String address){
        if(urls == null){
            if(address.equals("default")){
                address = defaultAddress;
            }
            urls = new Urls(address);

        }
        return urls;
    }

}
