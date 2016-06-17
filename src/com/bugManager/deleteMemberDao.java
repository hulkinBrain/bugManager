package com.bugManager;

import java.sql.*;

public class deleteMemberDao {
    int delete(dashboardAction da) {

        int status = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            sql = "SELECT projectLeaderId FROM projectMembers WHERE ? = projectLeaderId AND projectid = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.fetchUserId());
            ps.setString(2, da.getMembersToBeAddedInProjectId());
            rs = ps.executeQuery();
            if(rs.next()) {
                sql = "DELETE FROM projectMembers WHERE projectMemberId = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, da.getMemberIdToBeDeleted());
                status = ps.executeUpdate();
            }
            else
                status = 3;
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
