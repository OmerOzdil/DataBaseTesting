package jdbctests;

import org.testng.annotations.Test;
import utilities.DButils;

import java.util.List;
import java.util.Map;

public class dbutils_practice {

    @Test
    public void test1() {
        //create connection
        DButils.createConnection();

        //using the method to get list of map
        List<Map<String, Object>> queryResult = DButils.getQueryResultMap("select * from employees");

        for (Map<String, Object> map : queryResult) {
            System.out.println(map.toString());

        }

        // close the connection
        DButils.destroy();

    }

    @Test
    public void test2() {
        //create connection
        DButils.createConnection();

        Map<String, Object> rowMap = DButils.getRowMap("select  first_name, last_name,salary, job_id\n" +
                "from employees\n" +
                "where employee_id = 100");
        System.out.println(rowMap.toString());

        // close the connection
        DButils.destroy();
    }
}
