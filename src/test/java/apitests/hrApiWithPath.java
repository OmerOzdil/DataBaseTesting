package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;


import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class hrApiWithPath {
    @BeforeClass
    public void beforeclass(){
        baseURI = ConfigurationReader.get("hr_api_url");

    }
    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");


        assertEquals(response.statusCode(),200);

        //print the value of limit
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        String fiestCountry_id = response.path("items.country_id[0]");
        System.out.println("fiestCountry_id = " + fiestCountry_id);


        String secondCountryName = response.path("items.country_name[4]");
        System.out.println("secondCountryName = " + secondCountryName);

        String links2 = response.path("items.links[2].href[0]");
        System.out.println("links2 = " + links2);

        //get all countries
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all region ids are equal to 2

        List<Integer> region_ids = response.path("items.region_id");

        for (int region_id : region_ids) {
            System.out.println("region_id = " + region_id);
            assertEquals(region_id,2);

        }

    }
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        // make sure we have only IT_PROG as a job id.
        List<String> jobs = response.path("items.job_id");
        for (String job : jobs) {
            System.out.println("job = " + job);
            assertEquals(job,"IT_PROG");

        }




    }

}
