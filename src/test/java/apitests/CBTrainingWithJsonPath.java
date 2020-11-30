package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeclass(){
        baseURI = ConfigurationReader.get("cbt_api_url");
    }
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 17982)
                .when().get("/student/{id}");

        JsonPath jsonPath = response.jsonPath();

         String city = jsonPath.getString("students.company[0].address.city");
        System.out.println(city);


    }

}
