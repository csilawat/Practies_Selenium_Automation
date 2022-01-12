package WebUI_Automation.uploadDownloadFIles;

import WebUI_Automation.utils.ApplicationProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTestUploadDownloadFile {

    protected WebDriver driver;
    //   ApplicationProperties properties;

    @BeforeTest
    public void setup() {

        System.out.println("Before  method called ");

        String baseUrl = ApplicationProperties.INSTANCE.getUploadFileUrl();
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get(baseUrl);

    }

    @AfterTest
    public void close() {
        System.out.println("After test method called ");
        driver.quit();
    }

    public String takeScreenShot()
    {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYY_hh_mm_ss");
        String strDate = formatter.format(date);
        String path = "Reports/WebApp/" + strDate + ".png";
        try {
            FileUtils.copyFile(screenshotFile , new File(path));
            return path;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }


    }

}
