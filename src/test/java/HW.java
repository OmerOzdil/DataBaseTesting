import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HW {

    @BeforeClass
    public void beforeclass(){
        baseURI = ConfigurationReader.get("hr_api_url");
    }
    @Test
    /*
    Given accept type is Json
    Path param value- US
    When users sends request to /countries
    Then status code is 200
    And Content - Type is Json
    And country_id is US
    And Country_name is United States of America
    And Region_id is 2
    */
    public void Q1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country","US")
                .when().get("/countries/{country}");

        //verify status code
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(),"application/json");
        //verify country_id

        String country_id = response.path("country_id");
        assertEquals(country_id,"US");

        //verify country name
        String country_name=response.path("country_name");
        assertEquals(country_name,"United States of America");

        //verify region_id is 2
        int region_id = response.path("region_id");
        assertEquals(region_id,2);

    }
    /*
       Given accept type is Json
       Query param value - q={"department_id":80}
       When users sends request to /employees
       Then status code is 200
       And Content - Type is Json
       And all job_ids start with 'SA'
       And all department_ids are 80
       Count is 25
     */
    @Test
    public void QA2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        //verify status code
        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.contentType(),"application/json");

        JsonPath jsonPath = response.jsonPath();

        List<String> job_ids= jsonPath.getList("items.job_id");
        System.out.println(job_ids);

        for (String job_id : job_ids) {
            assertTrue(job_id.contains("SA"));
        }


        List<Object> department_ids = jsonPath.getList("items.department_id");
        for (Object department_id : department_ids) {
            assertEquals(department_id,80);

        }

        int count = jsonPath.get("count");
        assertEquals(count,25);

//        String o = jsonPath.get("items.links[0].href[0]");
//        System.out.println(o);

    }
    @Test
    public void QA3(){

        given().accept(ContentType.JSON)
                .and().queryParam("q","");
    }
}
