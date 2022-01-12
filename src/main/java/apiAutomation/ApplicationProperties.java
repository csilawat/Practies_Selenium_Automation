package apiAutomation;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {

    INSTANCE;
    private final Properties properties;

    ApplicationProperties() {

        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("api_Automation/application.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
}
