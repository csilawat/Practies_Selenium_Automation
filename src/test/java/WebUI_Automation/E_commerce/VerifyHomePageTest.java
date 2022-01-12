package WebUI_Automation.E_commerce;

import WebUI_Automation.models.eCommerce.HomePage;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyHomePageTest extends BaseTest{

    @Test
    public void verifyTabOnHomePage(){
        HomePage homePage = new HomePage(driver);
        String womenTab = homePage.getWomenTab().getText();
        String dressTab = homePage.getDressesTab().getText();
        String tShitsTab = homePage.getT_ShirtsTab().getText();
        String cartTab = homePage.getCartTab().getText();

        assertThat(womenTab,is("WOMEN"));
        assertThat(dressTab,is("DRESSES"));
        assertThat(tShitsTab,is("T-SHIRTS"));
        assertThat(cartTab,is("Cart (empty)"));
    }

    @Test
    public void HoverOnWomenTabAndVerifyTabs(){
        HomePage homePage = new HomePage(driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(homePage.getWomenTab()).build().perform();
        waitTime(5000);

        assertThat(homePage.getTopsHeadingInsideWomenTab().getText(),is("TOPS"));
        assertThat(homePage.getDressesHeadingInsideWomenTab().getText(),is("DRESSES"));
        assertThat(homePage.getT_ShirtsInsideWomenTab().getText(),is("T-shirts"));
        assertThat(homePage.getBlousesInsideWomenTab().getText(),is("Blouses"));
        assertThat(homePage.getCasualDressesInsideWomenTab().getText(),is("Casual Dresses"));
        assertThat(homePage.getEveningDressesInsideWomenTab().getText(),is("Evening Dresses"));
        assertThat(homePage.getSummerDressesInsideWomenTab().getText(),is("Summer Dresses"));

    }

}
