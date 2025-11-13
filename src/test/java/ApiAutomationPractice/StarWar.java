package ApiAutomationPractice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.StarWarsPojo;

public class StarWar {

    @Test
    public void validateInfo(){
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/planets/1";
        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().log().body().statusCode(200).extract().response();


        StarWarsPojo starWarsPojo = response.as(StarWarsPojo.class);
        Assert.assertEquals(starWarsPojo.getName(),"Tatooine");
        Assert.assertEquals(starWarsPojo.getTerrain(),"desert");
        Assert.assertEquals(starWarsPojo.getClimate(),"arid");
        Assert.assertEquals(starWarsPojo.getResidents().size(),10);
    }



    @Test
    public void validateCharactersInfoWithJsonPath(){
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people/1";
        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().log().body().statusCode(200).extract().response();


        JsonPath deserializeResponse = response.jsonPath();


        Assert.assertEquals(deserializeResponse.getString("name"),"Luke Skywalker");
        Assert.assertEquals(deserializeResponse.getString("eye_color"),"blue");
        Assert.assertEquals(deserializeResponse.getString("gender"),"male");
        Assert.assertEquals(deserializeResponse.getList("films").size(),4);
        Assert.assertEquals(deserializeResponse.getList("films").get(0),"https://swapi.dev/api/films/1/");



    }
    @Test
    public void validateCharactersInfo(){
        RestAssured.baseURI = "https://swapi.dev";
        RestAssured.basePath = "api/people/4";
        Response response = RestAssured.given().accept("application/json").when().get()
                .then().log().body().statusCode(200).extract().response();

        JsonPath deserializeResponse = response.jsonPath();

        Assert.assertEquals(deserializeResponse.getString("name"),"Darth Vader");
        Assert.assertEquals(deserializeResponse.getString("height"),"202");
        Assert.assertEquals(deserializeResponse.getString("skin_color"),"white");
        Assert.assertEquals(deserializeResponse.getString("eye_color"),"yellow");
        Assert.assertEquals(deserializeResponse.getString("gender"),"male");

    }

}
