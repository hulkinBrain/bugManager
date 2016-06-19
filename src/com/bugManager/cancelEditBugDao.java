package com.bugManager;

import java.sql.*;

/**
 * Created by soorya on 18-Jun-16.
 */
public class cancelEditBugDao {

    int status;
    int canceEdit(dashboardAction da){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            //setting updateLock back to 0 so that others can edit the bug
            sql = "UPDATE bugs SET updateLock = ? WHERE bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, "0");
            ps.setString(2, da.getChosenBugId());
            status = ps.executeUpdate();
            System.out.println("successfully edited");

        }
        catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException found");
            e.printStackTrace();
        }
        catch(SQLException e) {
            System.out.println("SQLException found");
            e.printStackTrace();
        }
        return status;
    }
}
