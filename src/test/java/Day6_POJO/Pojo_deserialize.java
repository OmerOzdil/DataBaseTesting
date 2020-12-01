package Day6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;
import static io.restassured.RestAssured.*;

public class Pojo_deserialize {
    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://52.55.102.92:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);


        spartan spartan15 = response.body().as(spartan.class);

        System.out.println(spartan15);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //assertion
        assertEquals(spartan15.getId(),15);
        assertEquals(spartan15.getName(),"Meta");
    }
    @Test
    public void regionToPojo(){

        Response response = given().accept(ContentType.JSON)
                .when().get("http://52.55.102.92:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //JSON to POJO(regions class)

        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());
        List<Item> items =regions.getItems();

    }
    @Test
    public void gson_example(){
        Gson gson = new Gson();

        String myJsonData ="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";


        //JSON to JAva collections or Pojo --> De-serialization


        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);

        System.out.println(map);

        //-----------SERIALIZATION---------------
        //JAVA Collection or POJO to JSON
        spartan spartanEU = new spartan(200,"Mike","Male",123123123);

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);
    }

}
