package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

public class FakeProductInformation {

    @Test
    public void validateProductInformation(){

        RestAssured.baseURI="https://fakestoreapi.com";
        RestAssured.basePath="products/1"; //end point

        Response response=RestAssured.given().header("Accept","application/json")
                .when()
                .get()
                .then()
                .log().body().statusCode(200).extract().response();//sout body

        //DESERIALIZATION (THE MOST INTERVIEW QUESTION) //Type Ref
        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});

        System.out.println(deserializedResponse);

        Assert.assertEquals(deserializedResponse.get("id"),1);
        Assert.assertEquals(deserializedResponse.get("title"),"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        Assert.assertEquals(deserializedResponse.get("price"),109.95);
        Assert.assertTrue(deserializedResponse.get("description").toString().contains("perfect"));

        Map<String,Object> convertedRating = (Map<String, Object>) deserializedResponse.get("rating");
        Assert.assertEquals(convertedRating.get("rate"), 3.9);

    }









}
