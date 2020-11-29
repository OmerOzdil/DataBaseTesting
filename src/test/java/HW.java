import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Test
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

    /*
            Q3:
            Given accept type is Json
            Query param value q= region_id 3
            When users sends request to /countries
            Then status code is 200
            And all regions_id is 3
            And count is 6
            And hasMore is false
            And Country_name are;
            Australia,China,India,Japan,Malaysia,Singapore
    */
    @Test
    public void QA3(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("countries");

        JsonPath jsonPath = response.jsonPath();

        //verify status code
        assertEquals(response.statusCode(),200);

        //verify all region_ids is 3
        List<Integer> regionids=response.path("items.region_id");
        for (int regionid : regionids) {
            System.out.println(regionid);
            assertEquals(regionid,3);

        }
        //verify count is 6

        int count = jsonPath.getInt("count");
        assertEquals(count,6);

        //verify hasMore
        Object hasMore = response.path("hasMore");
        assertEquals(hasMore,false);

        //List<String> expectedNames = Arrays.asList("Australia, China, India, Japan, Malaysia, Singapore");
        List<String> expectedNames= new ArrayList<>();
        expectedNames.add("Australia");
        expectedNames.add("China");
        expectedNames.add("India");
        expectedNames.add("Japan");
        expectedNames.add("Malaysia");
        expectedNames.add("Singapore");
        System.out.println(expectedNames);

        List<String> actualNames = jsonPath.getList("items.country_name");
        System.out.println(actualNames);

        assertEquals(actualNames,expectedNames);
    }
}
