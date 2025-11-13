package ApiAutomationPractice;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingPojo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GetBooking {


    @Test
    public void validateBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";

        Response response = RestAssured.given().header("Accept", "application/json")
                .pathParams("id", 1295)
                .when()
                .get()
                .then()
                .log().body().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});
        Assert.assertEquals(deserializedResponse.get("firstname"),"John");
        Map<String,Object> bookingDates = (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assert.assertEquals(bookingDates.get("checkin"),"2018-01-01");




    }

    @Test
    public void validateToken(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "auth";
        Response response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .with()
                .post()
                .then()
                .log().body().statusCode(200).extract().response();


        Map<String,Object> deserialization = response.as(new TypeRef<Map<String, Object>>() {});
        Assert.assertNotNull(deserialization.get("token"));

    }

    @Test
    public void updateBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";

        Response response = RestAssured.given().header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .pathParams("id",306)
                .body("{\n" +
                        "    \"firstname\" : \"Aya\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 555,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when()
                .put()
                .then()
                .log().body().statusCode(200).extract().response();

        Map<String,Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {});

        Assert.assertEquals(deserializedResponse.get("lastname"),"Brown");

    }

@Test
    public void validateBookingIdIsInTheList(){
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    RestAssured.basePath = "booking";
    Response response = RestAssured.given().header("Accept", "application/json")
            .when().get()
            .then().log().body().statusCode(200).extract().response();


    List<Map<String,Object>> deserializedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {});

    for (int i = 0;i<deserializedResponse.size();i++){
        if(deserializedResponse.get(i).get("bookingid").equals(614)) {
            Assert.assertEquals(deserializedResponse.get(i).get("bookingid"), 614);
            System.out.println("I found item");
            break;
        }
    }
    }


    @Test
    public void validateBookingWithPOJO(){

      RestAssured.baseURI = "https://restful-booker.herokuapp.com";
      RestAssured.basePath = "booking/3964";
      Response response = RestAssured.given().accept("application/json")
              .when().get()
              .then().log().body().statusCode(200).extract().response();


        BookingPojo deserializedResponse = response.as(BookingPojo.class);
        Assert.assertEquals(deserializedResponse.getFirstname(),"Aya");
        Assert.assertTrue(deserializedResponse.isDepositpaid());

        Assert.assertEquals(deserializedResponse.getBookingdates().getCheckin(),"2018-01-01");


    }

}
