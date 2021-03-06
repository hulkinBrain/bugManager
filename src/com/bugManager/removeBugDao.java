package com.bugManager;

import java.sql.*;


public class removeBugDao {
    int status;
    int remove(dashboardAction da) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql, sql1;
            PreparedStatement ps, ps1;
            ResultSet rs;

            sql = "SELECT bugGeneratedById FROM bugs WHERE bugGeneratedById = ? AND bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.fetchUserId());
            ps.setString(2, da.getChosenBugId());

            rs = ps.executeQuery();

            if(rs.next()) {
                sql1 = "DELETE FROM bugs WHERE bugId = ?";
                ps1 = c.prepareStatement(sql1);
                ps1.setString(1, da.getChosenBugId());
                status = ps1.executeUpdate();
            }

            else
                status = 2;
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
