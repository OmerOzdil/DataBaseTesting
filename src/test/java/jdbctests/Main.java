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
        ResultSet resultSet = statement.executeQuery("select* from departments");

        //move pinter to first row
//        resultSet.next();
//
//        System.out.println(resultSet.getString(1)+"-"+ resultSet.getString(2));
//
//        resultSet.next();
//        System.out.println(resultSet.getString(1)+"-"+ resultSet.getString("region_name"));
//
//        resultSet.next();
//        System.out.println(resultSet.getString(1)+"-"+ resultSet.getString("region_name"));


        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"-"+ resultSet.getString(2)+" - "+resultSet.getInt(3)+" - "+resultSet.getInt(4));
        }






        // close all connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
