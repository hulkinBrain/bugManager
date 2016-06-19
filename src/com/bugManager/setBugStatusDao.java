package com.bugManager;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class setBugStatusDao {
    int status;
    int setStatus(dashboardAction da) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            sql = "SELECT bugSolvedById FROM bugs WHERE bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenBugId());
            rs = ps.executeQuery();
            if(rs.next()) {
                if (rs.getString("bugSolvedById") == null) {

                    sql = "UPDATE bugs SET bugSolvedById = ?, bugSolvedOn = ? WHERE bugId = ?";
                    ps = c.prepareStatement(sql);
                    ps.setString(1, da.fetchUserId());

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);

                    ps.setString(2, currentDate);
                    ps.setString(3, da.getChosenBugId());
                    status = ps.executeUpdate();
                }

                else{
                    sql = "UPDATE bugs SET bugSolvedById = ?, bugSolvedOn = ? WHERE bugId = ?";
                    ps = c.prepareStatement(sql);
                    ps.setString(1, null);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);

                    ps.setString(2, null);
                    ps.setString(3, da.getChosenBugId());
                    status = ps.executeUpdate();
                }

            }

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
