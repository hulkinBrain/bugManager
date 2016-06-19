package com.bugManager;

import java.sql.*;


public class removeBugDao {
    int status;
    int remove(dashboardAction da) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;

            System.out.println("IN DELETE BUG DAO");
            sql = "DELETE FROM bugs WHERE bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenBugId());
            status = ps.executeUpdate();
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
