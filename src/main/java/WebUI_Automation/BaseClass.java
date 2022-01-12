package WebUI_Automation;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Setter
@Getter
public class BaseClass {

    protected WebDriver driver;
    public ExtentTest extentTest;
    public ExtentReports extentReports;

    public BaseClass(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;

    }

    public void elementWait(WebElement webElement) {

        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            System.out.println(webElement + "  element not visible");
            Assert.fail();
        }
    }

    public void clickOnElement(WebElement element, String message) {


        int waitTime = 50;
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        try {

            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            String capturePath = takeScreenSHot();

            extentTest.log(LogStatus.PASS,  extentTest.addScreenCapture((capturePath)));
            extentTest.log(LogStatus.PASS,"tested for Capture");
            extentTest.addScreencast(capturePath);


        } catch (TimeoutException exception) {
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(takeScreenSHot()));
            Assert.fail();
            throw new TimeoutException("Element not clickable after " + waitTime);
        } catch (WebDriverException exception) {
            if (exception.getMessage().contains("is not clickable")) {

                try {
                    sleep(10000);
                    wait.until(ExpectedConditions.visibilityOf(element));
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    element.click();

                } catch (Exception e) {
                    extentTest.log(LogStatus.FAIL, "Failed:- " + message + extentTest.addScreenCapture(takeScreenSHot()));

                    System.out.println("element not clickable after 10 second wait and click again...................");
                    e.printStackTrace();

                }

            } else {
                System.out.println("Element not Clickable .............");
                extentTest.log(LogStatus.FAIL, "Failed:- " + message + extentTest.addScreenCapture(takeScreenSHot()));

                exception.printStackTrace();

            }
        }
    }


    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String takeScreenSHot() {

        String dataNTime = LocalDateTime.now().toString();
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYY_hh_mm_ss");
        String strDate = formatter.format(date);


        String dest = System.getProperty("user.dir") + "\\Reports\\WebApp\\" + strDate + ".png";
        File destination = new File(dest);


        try {
            FileUtils.copyFile(screenShot, destination);
            return dest;
        } catch (IOException exception) {
            exception.printStackTrace();
            Assert.fail();
            return null;
        }


    }
}
