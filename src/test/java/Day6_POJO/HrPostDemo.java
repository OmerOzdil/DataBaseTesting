package Day6_POJO;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import  static org.hamcrest.Matchers.*;

public class HrPostDemo {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void PostRegion1(){

        RegionPost regionPost = new RegionPost(25,"Cybertek England");

        given().log().all().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(regionPost)
                .and().when()
                .post("/regions/")
                .then()
                .statusCode(201)
                .and()
                .body("region_id",is(25));
    }


}
