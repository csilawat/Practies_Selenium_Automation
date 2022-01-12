package WebUI_Automation.utils;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {

    INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("web_UI_Automation/application.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public String getUrl(){
        return properties.getProperty("eComUrl");
    }
    public String getUploadFileUrl(){
        return properties.getProperty("uploadFile");
    }
    public String getE_comTitle(){
        return properties.getProperty("eComTitle");
    }
}
