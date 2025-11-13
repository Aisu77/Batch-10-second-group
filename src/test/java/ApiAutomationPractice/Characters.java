package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Characters {

   @Test
    public void charactersValidation(){

       RestAssured.baseURI = "https://thronesapi.com";
       RestAssured.basePath = "api/v2/Characters/10";

       Response response = RestAssured.given().header("Accept", "application/json")
               .when()
               .get()
               .then()
               .log().body().statusCode(200).extract().response();
       Map<String,Object> deserialized = response.as(new TypeRef<Map<String, Object>>() {});

       Assert.assertEquals(deserialized.get("id"),10);
       Assert.assertEquals(deserialized.get("lastName"), "Stark");
       Assert.assertEquals(deserialized.get("fullName"), "Catelyn Stark");
       Assert.assertEquals(deserialized.get("title"),"Lady of Winterfell");
       Assert.assertEquals(deserialized.get("family"),"House Stark");
       Assert.assertEquals(deserialized.get("image"),"catelyn-stark.jpg");
       Assert.assertEquals(deserialized.get("imageUrl"),"https://thronesapi.com/assets/images/catelyn-stark.jpg");
       Assert.assertEquals(deserialized.get("firstName"),"Catelyn");
       Assert.assertEquals(deserialized.get("lastName"), "Stark");

   }

     @Test
    public void validateContinents(){
       RestAssured.baseURI = "https://thronesapi.com";
       RestAssured.basePath = "api/v2/Continents";
       Response response = RestAssured.given().header("Accept","aplication/json")
               .when().get()
               .then().log().body().statusCode(200).extract().response();

         List<Map<String,Object>> deserialized = response.as(new TypeRef<List<Map<String, Object>>>() {
         });
         for(int i = 0;i<deserialized.size();i++){

             System.out.println("id: " + deserialized.get(i).get("id"));
             System.out.println("name: " + deserialized.get(i).get("name"));

             //add comments


         }
     }





}
