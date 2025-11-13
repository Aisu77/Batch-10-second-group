package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CatFacts {

    @Test
    public void validateCatFactsInformation(){

        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts";
        Response response = RestAssured.given().header("Accept","application/json")
                .queryParam("limit",332)
                .when().get()
                .then().log().body().statusCode(200).extract().response();

        Map<String,Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {});

        Assert.assertEquals(deserializedResponse.get("current_page"),1);

        int moreThan50=0;
        int lessThan200=0;
        int more50less200=0;
        int notContainsCat=0;

        List<Map<String,Object>> convertedData= (List<Map<String, Object>>) deserializedResponse.get("data");

        for(Map<String,Object> data:convertedData){

            int convertedValue=Integer.parseInt(data.get("length").toString());

            if(convertedValue>50){
                moreThan50++;
            }
            if(convertedValue<200){
                lessThan200++;
            }
            if(convertedValue>50 && convertedValue<200){
                more50less200++;
            }
            if(!data.get("fact").toString().toLowerCase().contains("cat")){
                notContainsCat++;
            }
        }
        Assert.assertEquals(moreThan50,299);
        Assert.assertEquals(lessThan200,293);
        Assert.assertEquals(more50less200,260);
        Assert.assertEquals(notContainsCat,25);




        }








    }












