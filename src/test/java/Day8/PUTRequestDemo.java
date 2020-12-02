package Day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import  static org.hamcrest.Matchers.*;

public class PUTRequestDemo {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){

        //create one map for the put request json body
        Map<String,Object>  putRequestMap = new HashMap<>();
        putRequestMap.put("name","IntName");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",12121212123l);

        given().log().all()
                .and()
                .pathParam("id",171)
                .contentType(ContentType.JSON)
                .and()
                .body(putRequestMap)
        .when()
                .put("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);



        //send get request to verify body.
    }

    @Test
    public void PatchTest(){

        //create one map for the put request json body
        Map<String,Object>  pacthRequestMap = new HashMap<>();
        pacthRequestMap.put("name","UpdateName");

        given().log().all()
                .and()
                .pathParam("id",171)
                .contentType(ContentType.JSON)
                .and()
                .body(pacthRequestMap)
                .when()
                .patch("/api/spartans/{id}")
                .then().log().all()
                .assertThat().statusCode(204);
    }


}
