package com.bugManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addBugDao {

    int status = 0;
    public int add(dashboardAction da){

        try{
            Class.forName("com.jdbc.mysql.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");

            String sql = "INSERT INTO users bugs ()";

        }
        catch(SQLException e){
            System.out.println("SQLException detected");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException detected");
            e.printStackTrace();
        }
        return status;
    }
}
