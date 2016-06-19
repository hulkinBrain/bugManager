package com.bugManager;


import java.sql.*;

public class deleteProjectDao {


    int status;
    int removeProject(dashboardAction da) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql, sql1;
            PreparedStatement ps, ps1;
            ResultSet rs, rs1;

            sql = "SELECT projectLeaderId FROM projects WHERE projectId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenViewMembers());

            rs = ps.executeQuery();
            if (rs.next()) {

                //check if the user is projectLeader
                if (rs.getString("projectLeaderId").equals(da.fetchUserId())) {
                    sql1 = "DELETE FROM projects WHERE projectId = ?";
                    ps1 = c.prepareStatement(sql1);
                    ps1.setString(1, da.getChosenViewMembers());
                    ps1.executeUpdate();

                    sql1 = "DELETE FROM projectMembers WHERE projectId = ?";
                    ps1 = c.prepareStatement(sql1);
                    ps1.setString(1, da.getChosenViewMembers());
                    ps1.executeUpdate();

                    sql1 = "DELETE FROM bugs WHERE relatedProjectId = ?";
                    ps1 = c.prepareStatement(sql1);
                    ps1.setString(1, da.getChosenViewMembers());
                    ps1.executeUpdate();

                    status = 1;
                }
                else if(!rs.getString("projectLeaderId").equals(da.fetchUserId()))
                    status = 2;

                else
                    status = 0;

            }
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLException found");
            e.printStackTrace();
        }
        return status;
    }
}
