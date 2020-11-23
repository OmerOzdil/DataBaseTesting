package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@3.80.189.73:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";
    @Test
    public void MetaDataExample2() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colNum = rsMetadata.getColumnCount();


        //loop through each loop
        while(resultSet.next()){
            Map<String,Object> row = new HashMap<>();


            for (int i = 1; i <=colNum; i++) {

                row.put(rsMetadata.getColumnName(i),resultSet.getObject(i));

            }

            //add you map to your list
            queryData.add(row);

        }

        // print out all the map
        for (Map<String, Object> map : queryData) {
            System.out.println(map.toString());

        }
        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
