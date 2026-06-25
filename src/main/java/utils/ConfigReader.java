package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        try {

            FileInputStream fis =
                    new FileInputStream(
                            "src/main/resources/config/config.properties");

            properties = new Properties();
            properties.load(fis);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ConfigReader(){}

    public static String get(String key){
        return properties.getProperty(key);
    }
}