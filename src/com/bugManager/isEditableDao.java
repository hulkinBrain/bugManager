package com.bugManager;

import java.sql.*;

/**
 * Created by soorya on 18-Jun-16.
 */
public class isEditableDao {

    int status;
    String editBugTItle, editBugDesc;
    int canEdit(dashboardAction da){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            sql = "SELECT bugTitle, bugDesc, updateLock FROM bugs WHERE bugId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenBugId());
            rs = ps.executeQuery();

            while(rs.next()){
                if(rs.getString("updateLock").equals("0")) {
                    status = 1;
                    editBugTItle = rs.getString("bugTitle");
                    editBugDesc = rs.getString("bugDesc");
                }
                else
                    status = 2;
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

    String returnEditBugTitle(){
        return editBugTItle;
    }

    String returnEditBugDesc(){
        return editBugDesc;
    }
}
