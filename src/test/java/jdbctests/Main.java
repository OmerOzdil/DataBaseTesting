package jdbctests;

import java.sql.*;

public class Main {
    
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.226.186.24:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        // create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object.
        ResultSet resultSet = statement.executeQuery("select* from regions");

        //move pinter to first row
        resultSet.next();

        System.out.println(resultSet.getString("region_name"));




        // close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
