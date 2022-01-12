package WebUI_Automation.models.eCommerce;

import WebUI_Automation.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertTrue;

@Getter
@Setter
public class SearchPage extends BaseClass {

    @FindBy(xpath = "//input[@id='search_query_top']")
    private WebElement searchField;
    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[@class='lighter']")
    private WebElement verifySearchValue;

    @FindBy(xpath = "//h5/a[@title='Faded Short Sleeve T-shirts']")
    private WebElement searchValueTitle;

    @FindBy(xpath = "//span[contains(text(),'Quick view')]")
    private WebElement verifyQuickViewButton;

    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[contains(text(),'More')]")
    private WebElement moreButton;

    @FindBy(xpath = "//img[@title='Faded Short Sleeve T-shirts']")
    private WebElement image;

    @FindBy(xpath = "(//div/h2[contains(text(),'')])[1]")
    private WebElement verifyAddProductMessage;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "(//table/thead/tr/th)[1]")
    private WebElement tableHeading_Product;

    @FindBy(xpath = "(//table/thead/tr/th)[2]")
    private WebElement tableHeading_Description;

    @FindBy(xpath = "(//table/thead/tr/th)[3]")
    private WebElement tableHeading_Avail;

    @FindBy(xpath = "(//table/thead/tr/th)[4]")
    private WebElement tableHeading_Unit_Price;

    @FindBy(xpath = "(//table/thead/tr/th)[5]")
    private WebElement tableHeading_Qty;

    @FindBy(xpath = "(//table/thead/tr/th)[6]")
    private WebElement tableHeading_Total;

    public SearchPage(WebDriver driver, ExtentTest extentTest) {
        super(driver,extentTest);
        PageFactory.initElements(driver, this);

    }

    public void searchItem(String searchItem) {
        getSearchField().clear();
        getSearchField().sendKeys(searchItem);
        clickOnElement(getSearchButton(), "CLicked on Search Button");
    }

    public void addProductToCart() {

        Actions actions = new Actions(driver);
        actions.moveToElement(getImage()).perform();
        assertTrue(getAddToCartButton().isDisplayed());
        assertTrue(getMoreButton().isDisplayed());
        elementWait(getAddToCartButton());
        clickOnElement(getAddToCartButton(), "Clicked on Add To Cart Button");
    }

    public void proceed() {

        clickOnElement(getCheckoutButton(), "click on CheckOut button");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
