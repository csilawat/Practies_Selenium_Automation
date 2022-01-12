package apiAutomation;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;


public class BaseTest {

    String baseUrl = ApplicationProperties.INSTANCE.getBaseUrl();

    //    Logger logger = LOGGER
    @BeforeTest
    public void beforeTestMethod() {
        RestAssured.baseURI = baseUrl;
    }



}