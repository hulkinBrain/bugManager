package com.bugManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addBugDao {

    int status = 0;
    public int add(dashboardAction da){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");

            String sql;

            sql= "INSERT INTO bugs (bugTitle, bugDesc, bugGeneratedById, bugGeneratedOn, relatedProjectId, updateLock) "+
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            ps.setString(1, da.getBugTitle());
            ps.setString(2, da.getBugDesc());
            ps.setString(3, da.fetchUserId());
            ps.setString(4, currentDate);
            ps.setString(5, da.getChosenViewMembers());
            ps.setString(6, "0");

            status = ps.executeUpdate();

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
