package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {
    String dbUrl = "jdbc:oracle:thin:@54.226.186.24:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        // create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object.
        ResultSet resultSet = statement.executeQuery("select* from departments");

        // how to find how many row we have for the query.
        //go to last row.
        resultSet.last();

        int rowNum= resultSet.getRow();
        System.out.println(rowNum);

        // we need move before first row to get full list since we are at the last row right now.
        resultSet.beforeFirst();


        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

    }

    @Test
    public void CountNavigate() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);
        //we need move before first row to get full list since we are at he last row right now.
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get tge database related data inside the dbMetadata object.

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println("User = "+databaseMetaData.getUserName());
        System.out.println("Database Product Name "+ databaseMetaData.getDatabaseProductName());
        System.out.println("Database Product Version"+ databaseMetaData.getDatabaseProductVersion());
        System.out.println("Driver Name "+databaseMetaData.getDriverName());
        System.out.println("Driver version"+ databaseMetaData.getDriverVersion());


        //get tge resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many coloums we have?
        int columnCount = rsMetadata.getColumnCount();
        System.out.println(columnCount);


        //column name
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        for(int i=1; i<=columnCount;i++){
            System.out.println(rsMetadata.getColumnName(i));
        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
