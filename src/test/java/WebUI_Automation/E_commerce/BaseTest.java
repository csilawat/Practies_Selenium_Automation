package WebUI_Automation.E_commerce;

import WebUI_Automation.utils.ApplicationProperties;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseTest {

    private ApplicationProperties applicationProperties;
    WebDriver driver;
    public static ExtentReports extentReport;
    public ExtentTest extentTest;
    static Logger logger = Logger.getLogger(String.valueOf(BaseTest.class));
    public FileHandler fh;

    @BeforeTest
    public void beforeTests() {

        String baseUrl = applicationProperties.INSTANCE.getUrl();
        String E_ComTitle = applicationProperties.INSTANCE.getE_comTitle();
        addLogFileDetail();
        logger.info("======================================================");
        logger.info("======================**************************================================");
        logger.info("======================Applications Started ================================");

        logger.info("Base url is :- " + baseUrl);
        logger.info("Web TItle is  :- " + E_ComTitle);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_YYY_hh_mm_ss");
        String strDate = formatter.format(date);
        extentReport = new ExtentReports(System.getProperty("user.dir") + "./Reports/Looper/" + strDate + ".html", true, DisplayOrder.NEWEST_FIRST);
        extentReport.addSystemInfo("Application Name", "APPLICATION_NAME");

        assertThat(driver.getTitle(), is(E_ComTitle));
        assertThat(driver.getCurrentUrl(), is(baseUrl));

//        extentTest.log(LogStatus.PASS, "Navigated to the specified URL");
    }

    @BeforeMethod
    public void setup(Method method) {
        System.out.println(method.getName().toString());

        String Description = method.getDeclaredAnnotation(Test.class).description();
        extentTest = extentReport.startTest(Description);
    }

    @AfterTest
    public void afterTests() {
        logger.info("After method called");
        driver.quit();
        extentReport.endTest(extentTest);
        extentReport.flush();
        logger.info("======================================================");
        logger.info("======================**************************================================");
        logger.info("======================Applications End ================================");


    }

    public void waitTime(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public void addLogFileDetail() {


        try {

            fh = new FileHandler("C:\\Users\\csilawat\\IdeaProjects\\store_Api_Automation\\MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
