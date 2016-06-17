package com.bugManager;

import java.sql.*;

/**
 * Created by soorya on 05-Jun-16.
 */
public class addProjectDao {

    int status = 0;
    int add(dashboardAction da){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql, username = "";
            sql = "SELECT username FROM users WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, da.getUserId());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                username = rs.getString("username");

            sql = "INSERT INTO projects (projectTitle, projectDetails, projectLeaderId, projectHeadUsername) VALUES(?, ?, ?, ?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getProjectTitle());
            ps.setString(2, da.getProjectDetails());
            ps.setString(3, da.getUserId());
            ps.setString(4, username);
            status = ps.executeUpdate();


            if(status == 1){
                System.out.println("succes in writing project");
            }

            else if(status == 0){
                System.out.println("couldnt write project in projectDao");
            }
            c.close();

        }
        catch (SQLException e) {
            System.out.println("SQLException detected");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException detected");
            e.printStackTrace();
        }
        return status;
    }
}
