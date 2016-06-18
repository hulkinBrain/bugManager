package com.bugManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class viewExistingMembersDao {
    private ArrayList<members> existingMembersList = new ArrayList<>();

    public List<members> getMembers(dashboardAction da){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql = "SELECT users.id, firstName, lastName, username FROM users, projectMembers WHERE users.id = projectMemberId AND ? = projectId";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenViewMembers());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                members m = new members();
                m.setMembersId(rs.getString("users.id"));
                m.setMembersFirstName(rs.getString("firstName"));
                m.setMembersLastName(rs.getString("lastName"));
                m.setMembersUsername(rs.getString("username"));
                existingMembersList.add(m);
            }
        }
        catch(SQLException e){
            System.out.println("SQLException found");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException found");
            e.printStackTrace();
        }
        System.out.println("SIZE OF EXISTING MEMBERS LIST = " + existingMembersList.size());
        return existingMembersList;

    }
    public ArrayList<members> getExistingMembersList() {
        return existingMembersList;
    }

    public void setExistingMembersList(ArrayList<members> existingMembersList) {
        this.existingMembersList = existingMembersList;
    }
}
