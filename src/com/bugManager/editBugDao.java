package com.bugManager;

import java.sql.*;

/**
 * Created by soorya on 18-Jun-16.
 */
public class editBugDao {

    int status;
    int edit(dashboardAction da){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            System.out.println("bugID = " + da.getChosenBugId() + " " + da.getEditBugTitle() + " " + da.getEditBugDesc());

            sql = "UPDATE bugs SET bugTitle = ?, bugDesc = ? WHERE bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getEditBugTitle());
            ps.setString(2, da.getEditBugDesc());
            ps.setString(3, da.getChosenBugId());

            status = ps.executeUpdate();

            if(status == 1)
                System.out.println("successfully edited");
            else
                System.out.println("couldnt update");
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
