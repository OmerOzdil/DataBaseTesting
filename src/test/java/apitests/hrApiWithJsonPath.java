package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;

public class hrApiWithJsonPath {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }



    @Test
    public void test1(){

        Response response = get("/countries");


        JsonPath jsonPath = response.jsonPath();

        String secondcountryName = jsonPath.getString("items.country_name[1]");
        System.out.println("secondcountryName = " + secondcountryName);

        //gel all country ids

        List<String> countryIds = jsonPath.getList("items.country_id");
        System.out.println(countryIds);

        //get all country name where their region id is equal to 2
        List<String> countryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id ==2}.country_name");
        System.out.println(countryNamesWithRegionId2);


        //path is also working with findall method.
        List<String> countryNamesWithRegionId2path = response.path("items.findAll {it.region_id ==2}.country_name");
        System.out.println(countryNamesWithRegionId2path);
    }

    @Test
    public void test2(){

        Response response = given().queryParam("limit", 107)
                .when().get("/employees");


        JsonPath jsonPath = response.jsonPath();

        List<String> firstNames = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(firstNames);

        //get me all firstname of employees who is making more then 10000
        List<String> firstNames2 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(firstNames2);

        String kingName = jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println(kingName);
    }




}
