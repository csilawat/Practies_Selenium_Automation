package WebUI_Automation.models.eCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//a[@title='Women']")
    private WebElement WomenTab;

    @FindBy(xpath = "//div/ul/li/a[@title='Dresses']")
    private WebElement DressesTab;

    @FindBy(xpath = "//div/ul/li/a[@title='T-shirts']")
    private WebElement T_ShirtsTab;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cartTab;


    @FindBy(xpath = "//li/ul/li/a[@title='Dresses']")
    private WebElement dressesHeadingInsideWomenTab;

 @FindBy(xpath = "//li/ul/li/a[@title='Tops']")
    private WebElement topsHeadingInsideWomenTab;

 @FindBy(xpath = "//li/ul/li/a[@title='T-shirts']")
    private WebElement T_ShirtsInsideWomenTab;
 @FindBy(xpath = "//li/ul/li/a[@title='Blouses']")
    private WebElement BlousesInsideWomenTab;

    @FindBy(xpath = "//li/ul/li/a[@title='Casual Dresses']")
    private WebElement CasualDressesInsideWomenTab;

    @FindBy(xpath = "//li/ul/li/a[@title='Evening Dresses']")
    private WebElement EveningDressesInsideWomenTab;

    @FindBy(xpath = "//li/ul/li/a[@title='Summer Dresses']")
    private WebElement SummerDressesInsideWomenTab;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public WebElement getWomenTab() {
        return WomenTab;
    }

    public WebElement getDressesTab() {
        return DressesTab;
    }

    public WebElement getT_ShirtsTab() {
        return T_ShirtsTab;
    }

    public WebElement getCartTab() {
        return cartTab;
    }

    public WebElement getDressesHeadingInsideWomenTab() {
        return dressesHeadingInsideWomenTab;
    }

    public WebElement getTopsHeadingInsideWomenTab() {
        return topsHeadingInsideWomenTab;
    }

    public WebElement getBlousesInsideWomenTab() {
        return BlousesInsideWomenTab;
    }

    public WebElement getT_ShirtsInsideWomenTab() {
        return T_ShirtsInsideWomenTab;
    }

    public WebElement getCasualDressesInsideWomenTab() {
        return CasualDressesInsideWomenTab;
    }

    public WebElement getEveningDressesInsideWomenTab() {
        return EveningDressesInsideWomenTab;
    }

    public WebElement getSummerDressesInsideWomenTab() {
        return SummerDressesInsideWomenTab;
    }
}
