package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

import constants.FrameworkConstants;
import enums.ConfigProperties;
import exceptions.PropertyFileUsageException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

//private constructor - no object can be created
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertiesUtil {
    //final class cant be inherited and modified
    private static Properties property = new Properties();
    private static final HashMap<String, String> CONFIGMAP = new HashMap<>();
    // static final - constants

    static {
        //runs only once when class is loaded to JVM
        try (FileInputStream fis =
                     new FileInputStream(FrameworkConstants.getConfigFilePath())) {
            property.load(fis);
            property.entrySet().forEach(entry -> CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
            System.out.println(CONFIGMAP);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0); // if any error exit execution
        }
    }

//    private PropertiesUtil() {
//    }

    public static String getValue(ConfigProperties key) {
        if(Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.toString().toLowerCase()))){
            throw new PropertyFileUsageException("The given property or value is not found, please check the property file. Key given: " + key);
        }
        return CONFIGMAP.get(key.toString().toLowerCase());
    }
}