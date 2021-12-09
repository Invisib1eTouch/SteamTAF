package core;

import utils.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    protected static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            Logger.log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("base.url");
    }

    public static String getLocalization() {
        return properties.getProperty("localization");
    }

    public static String getBrowserName() {
        return properties.getProperty("browser");
    }

    public static int getTimeOut() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
