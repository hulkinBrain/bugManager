package com.bugManager;

import java.sql.*;

/**
 * Created by soorya on 18-Jun-16.
 */
public class isEditableDao {

    int status;
    int canEdit(dashboardAction da){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            sql = "SELECT updateLock FROM bugs WHERE bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenBugId());
            rs = ps.executeQuery();

            if(rs.next()){
                if(rs.getString("updateLock").equals("0"))
                    status = 1;
                else
                    status = 2;
            }
        }
        catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundExcetion found");
            e.printStackTrace();
        }
        catch(SQLException e) {
            System.out.println("SQLExcetion found");
            e.printStackTrace();
        }
        return status;
    }
}
