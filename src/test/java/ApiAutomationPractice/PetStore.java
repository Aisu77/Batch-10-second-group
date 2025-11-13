package ApiAutomationPractice;

import apiutils.MockData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.PetStoresPojo;

public class PetStore {

    @Test(priority = 1)
    public void validatePetInfo(){

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";
        Response response = RestAssured.given().accept("application/json").header("Content-Type","application/json")
                .body(MockData.petBody(8234,"Goggy","available"))
                .with().post()
                .then().log().body().statusCode(200).extract().response();

        PetStoresPojo desResponse = response.as(PetStoresPojo.class);

        Assert.assertEquals(desResponse.getId(),8234);
        Assert.assertEquals(desResponse.getCategory().getId(),8);
        Assert.assertEquals(desResponse.getCategory().getName(),"pamuk Sever ");
        Assert.assertEquals(desResponse.getName(),"Goggy");
        Assert.assertEquals(desResponse.getPhotoUrls().get(0), "www.amazon.com/cat");
        Assert.assertEquals(desResponse.getTags().get(0).getId(),91);
        Assert.assertEquals(desResponse.getTags().get(0).getName(),"No Tags");
        Assert.assertEquals(desResponse.getStatus(),"available");


    }

    @Test(priority = 2)
    public void updatePetInfo() throws InterruptedException {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";
        Response response = RestAssured.given().contentType("application/json").accept("application/json")
                .body(MockData.petBody(9898,"Graf","sold"))
                .when().put()
                .then().log().body().statusCode(200).extract().response();

        PetStoresPojo desResponse = response.as(PetStoresPojo.class);
        Assert.assertEquals(desResponse.getId(),9898);
        Assert.assertEquals(desResponse.getName(),"Graf");
        Assert.assertEquals(desResponse.getStatus(),"sold");
        Thread.sleep(7000);

    }

    @Test(priority = 3)
    public void deletePetInfo(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet/9898";
        Response response = RestAssured.given().contentType("application/json").accept("application/json")
                //.queryParam("id","7777")
                .when().delete()
                .then().log().body().statusCode(200).extract().response();

        PetStoresPojo desResponse = response.as(PetStoresPojo.class);
        Assert.assertEquals(desResponse.getCode(),200);
        Assert.assertEquals(desResponse.getType(),"unknown");
        Assert.assertEquals(desResponse.getMessage(),"9898");


    }

    @Test(priority = 4)
    public void getStatus(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet/8234";
        Response response = RestAssured.given().accept("application/json")
                //.queryParam("id","7777")
                .when().get()
                .then().log().body().statusCode(200).extract().response();
        PetStoresPojo desResponse = response.as(PetStoresPojo.class);
        Assert.assertEquals(desResponse.getCode(),0);
        Assert.assertEquals(desResponse.getType(),"null ");
        Assert.assertEquals(desResponse.getMessage(),"Pet not found");



    }


    @Test(priority = 4)
    public void getStatusWithThJsonPath(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet/8234";
        Response response = RestAssured.given().accept("application/json")
                //.queryParam("id","7777")
                .when().get()
                .then().log().body().statusCode(200).extract().response();

        JsonPath deserializeResponse = response.jsonPath();
        Assert.assertEquals(deserializeResponse.getInt("id"),8234);
        Assert.assertEquals(deserializeResponse.getString("name"),"Goggy");
        Assert.assertEquals(deserializeResponse.getInt("category.id"),8);
        Assert.assertEquals(deserializeResponse.getInt("tags[0].id"),91);





    }




}
