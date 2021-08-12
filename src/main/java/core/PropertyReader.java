package core;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    protected Properties properties;

    public PropertyReader() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("base.url");
    }

    public String getBrowserName() {
        return properties.getProperty("browser");
    }

    public int getTimeOut() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
