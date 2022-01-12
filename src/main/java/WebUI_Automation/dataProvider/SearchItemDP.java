package WebUI_Automation.dataProvider;

import org.testng.annotations.DataProvider;

import java.util.LinkedHashMap;

public class SearchItemDP {

    @DataProvider(name = "search Product detail")
    public static Object [] [] searchItem(){

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("itemName", "FADED SHORT SLEEVE T-SHIRTS");
        return new Object[][]{ new Object[]{ map }};
    }


    @DataProvider(name = "add product to cart")
    public static Object [] [] cartData(){

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("itemName", "FADED SHORT SLEEVE T-SHIRTS");
        map.put("addItemSuccessMessage", "Product successfully added to your shopping cart");

        return new Object[][]{new Object[]{map}};

    }
}
