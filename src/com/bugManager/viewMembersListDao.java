package com.bugManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class viewMembersListDao {

    private ArrayList<members> membersList = new ArrayList<>();

    public ArrayList<members> getMembersList() {
        return membersList;
    }

    public void setMembersList(ArrayList<members> membersList) {
        this.membersList = membersList;
    }

    public List<members> getMembers(dashboardAction da){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql = "SELECT id, firstName, lastName, username, email FROM users";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                members m = new members();
                m.setMembersId(rs.getString("id"));
                m.setMembersFirstName(rs.getString("firstName"));
                m.setMembersLastName(rs.getString("lastName"));
                m.setMembersUsername(rs.getString("username"));
                membersList.add(m);
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
        return membersList;
    }
}
