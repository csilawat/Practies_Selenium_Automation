package apiAutomation.testClasses;

import apiAutomation.BaseTest;
import apiAutomation.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ProductTest extends BaseTest {

    String urlPath = "products";

//    {"id":1,"title":"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops","price":109.95,
//            "description":"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
//            "category":"men's clothing","image":"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
//            "rating":{"rate":3.9,"count":120}}

    @BeforeTest
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void getALlProducts1() {
        Response response = given().when().get(urlPath).then().extract().response();

    }

        @Test
    public void getALlProducts() {

        Response response = given().when().get(urlPath).then().extract().response();

        JSONArray jsonArray = new JSONArray(response.asString());
        int productCount = jsonArray.length();
        int expectedProductCount = 20;
        assertThat("product count should be 20 but is "+expectedProductCount,productCount,is(expectedProductCount));
    }

    @Test
    public void getSingleProductById_1() {

        Response response = given().when().get(urlPath+"/1").then().extract().response();
        Product product = response.getBody().as(Product.class);

        assertThat(product.getId(),equalTo(1));
        assertThat(product.getTitle(),is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"));
        assertThat(product.getDescription(),containsString("Stash your laptop (up to 15 inches) in the padded sleeve, your everyday"));
        assertThat(product.getCategory(),is("men's clothing"));
        assertThat(product.getRating().getCount(),is(120));
    }

    @Test
    public void getProductByRating_rate() throws IOException {

        Response response = given().when().get("products").then().extract().response();
        JSONArray jsonArray = new JSONArray(response.asString());
        for (Object jsonObject : jsonArray){

            JSONObject js = (JSONObject) jsonObject;

            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(js.toString(), Product.class);

            if (product.getRating().getRate()<2){
                assertThat(product.getId(),equalTo(8));
                assertThat(product.getTitle(),is("Pierced Owl Rose Gold Plated Stainless Steel Double"));
                assertThat(product.getDescription(),containsString("Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel"));
                assertThat(product.getCategory(),is("jewelery"));
                assertThat(product.getRating().getCount(),is(100));
            }
        }
    }


    @Test
    public void getProductByCategory() throws IOException {

        Response response = given().when().get("products").then().extract().response();
        JSONArray jsonArray = new JSONArray(response.asString());
        for (Object jsonObject : jsonArray){

            JSONObject js = (JSONObject) jsonObject;

            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(js.toString(), Product.class);

            if (product.getCategory().equals("jewelery")&&product.getRating().getRate()==8){
                assertThat(product.getId(),equalTo(8));
                assertThat(product.getTitle(),is("Pierced Owl Rose Gold Plated Stainless Steel Double"));
                assertThat(product.getDescription(),containsString("Rose Gold Plated Double Flared Tunnel Plug Earrings. Made of 316L Stainless Steel"));
                assertThat(product.getCategory(),is("jewelery"));
                assertThat(product.getRating().getCount(),is(100));
            }
        }
    }


    public void uploadTextFile(String XpathOfChooseFileButton, String fileName){
        String pathOfTextFIle = System.getProperty("user.dic")+fileName;
//        XpathOfChooseFileButton.sendKeys(fileName);
    }

}
