package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class jsonToJavaCollection {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanToMap(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //we will convert json response to java map
        Map<String,Object> jsonDataMap = response.body().as(Map.class);

        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        assertEquals(name,"Meta");

    }
    @Test
    public void allSpartaansToListOfMap(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        assertEquals(response.statusCode(),200);

        //de serialize Json respnse to list of maps

        List<Map<String,Object>> allSpartanList= response.body().as(List.class);

        System.out.println(allSpartanList);

        //print second spartan first name
        System.out.println(allSpartanList.get(1).get("name"));

        //save spartan 3 in a map
        Map<String,Object> spartan3 =allSpartanList.get(2);
        System.out.println(spartan3);

    }
    @Test
    public void regionToMap(){

        Response response = when().get("http://52.55.102.92:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        Map<String,Object> regionMap = response.body().as(Map.class);


        System.out.println(regionMap.get("count"));

        System.out.println(regionMap.get("limit"));

        System.out.println(regionMap.get("hasMore"));

        System.out.println(regionMap.get("items"));

        List<Map<String,Object>>  itemsList = (List<Map<String, Object>>) regionMap.get("items");
        System.out.println(itemsList.get(0).get("region_name"));


        //List<Map<String,Object>>  linkList = (List<Map<String, Object>>) regionMap.get("links");
       //System.out.println(linkList.get(2).get("rel"));

    }



}
