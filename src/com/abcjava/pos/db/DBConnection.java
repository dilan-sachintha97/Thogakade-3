package com.abcjava.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class DBConnection {
    //1 st rule
    private static DBConnection dbConnection;

    private Connection connection;

    //2 nd rule
    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Thogakade",
                "root",
                "7911"
        );
    }

    //3rd rule
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbConnection==null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
