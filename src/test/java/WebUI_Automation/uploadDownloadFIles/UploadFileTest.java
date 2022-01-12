package WebUI_Automation.uploadDownloadFIles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class UploadFileTest extends BaseTestUploadDownloadFile {

    @Test
    public void UploadFile(){

        System.out.println("test method called ");

        WebElement uploadButton = driver.findElement(By.xpath("//li/a[contains(text(),'File Upload')]"));
        uploadButton.click();
        WebElement chooseFile = driver.findElement(By.xpath("//input[@id='file-upload']"));

        String pathOfTextFIle = System.getProperty("user.dir")+"\\src\\test\\resources\\web_UI_Automation\\test.xlsx";
        chooseFile.sendKeys(pathOfTextFIle);

        WebElement submitFile = driver.findElement(By.xpath("//input[@id='file-submit']"));
        submitFile.click();

        WebElement uploadedFile = driver.findElement(By.xpath("//div[contains(text(),'test.xlsx')]"));
        assertThat("Upload file not available ",uploadedFile.getText(),containsString("test.xlsx"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
