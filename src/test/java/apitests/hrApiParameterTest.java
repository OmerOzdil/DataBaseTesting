package apitests;

import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class hrApiParameterTest {
    @BeforeClass
    public  void beforeclass(){
        baseURI = ConfigurationReader.get("hr_api_url");

    }
}
