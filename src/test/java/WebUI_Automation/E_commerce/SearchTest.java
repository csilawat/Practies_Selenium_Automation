package WebUI_Automation.E_commerce;

import WebUI_Automation.dataProvider.SearchItemDP;
import WebUI_Automation.models.eCommerce.SearchPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchTest extends BaseTest {



    @Test(description = "Verify Search product", dataProvider = "search Product detail", dataProviderClass = SearchItemDP.class
    )
    public void verifySearchField(LinkedHashMap<String, String> data) {

        String itemName = data.get("itemName");
        SearchPage searchPage = new SearchPage(driver,extentTest);
        searchPage.searchItem(data.get("itemName"));

        assertThat(searchPage.getSearchValueTitle().getText().toLowerCase(Locale.ROOT), is(itemName.toLowerCase(Locale.ROOT)));
        assertThat(searchPage.getVerifySearchValue().getText(), containsString(itemName));
        waitTime(5000);
    }

    @Test(description = "verify Add product to Cart", dataProvider = "add product to cart", dataProviderClass = SearchItemDP.class)
    public void verifyCartAfterAddSingleProduct(LinkedHashMap<String, String> data) {

        String itemName = data.get("itemName");
        String successMessage = data.get("addItemSuccessMessage");

        SearchPage searchPage = new SearchPage(driver,extentTest);
        searchPage.searchItem(itemName);

        assertThat(searchPage.getSearchValueTitle().getText().toLowerCase(Locale.ROOT), is(itemName.toLowerCase(Locale.ROOT)));
        assertThat(searchPage.getVerifySearchValue().getText(), containsString(itemName));

        searchPage.addProductToCart();
        waitTime(10000);
        assertThat(searchPage.getVerifyAddProductMessage().getText(), is(successMessage));

        searchPage.proceed();
        assertThat(searchPage.getTableHeading_Product().getText(), is("Product"));
        assertThat(searchPage.getTableHeading_Description().getText(), is("Description"));
        assertThat(searchPage.getTableHeading_Unit_Price().getText(), is("Unit price"));
        assertThat(searchPage.getTableHeading_Avail().getText(), is("Avail."));
        assertThat(searchPage.getTableHeading_Qty().getText(), is("Qty"));
        assertThat(searchPage.getTableHeading_Total().getText(), is("Total"));


    }
}
