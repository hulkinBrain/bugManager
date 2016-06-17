package com.bugManager;


import java.sql.*;
import java.util.ArrayList;

public class addMembersDao {


    int add(dashboardAction da){
        int status=0;
        Boolean memberExists = true;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql, projectLeaderId = "";
            PreparedStatement ps;
            ResultSet rs;

            sql = "SELECT projectLeaderId FROM projects WHERE ? = projectLeaderId AND ? = projectId";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.fetchUserId());
            ps.setString(2, da.getMembersToBeAddedInProjectId());
            rs = ps.executeQuery();
            if(rs.next()) {

                sql = "SELECT projectLeaderId FROM projects WHERE projectId = ?";
                ps = c.prepareStatement(sql);
                System.out.println("da.getMembersToBeAddedInProjectId() = " + da.getMembersToBeAddedInProjectId());
                ps.setString(1, da.getMembersToBeAddedInProjectId());
                rs = ps.executeQuery();
                if (rs.next()) {
                    projectLeaderId = rs.getString("projectLeaderId");
                }



                String letter = "";
                ArrayList<String> members = new ArrayList<>();
                String membersToBeAdded = da.getMembersToBeAdded();

                //extracting ids of members from the string getMemberstoBeAdded() if it contains one or more than one ids(selected through the checkboxlist in viewMembers.jsp
                // separated by a comma(,)

                for (int i = 0; i < membersToBeAdded.length(); i++) {

                    if (membersToBeAdded.charAt(i) != ',' && membersToBeAdded.charAt(i) != ' ') {
                        letter += membersToBeAdded.charAt(i);
                    }
                    if (membersToBeAdded.charAt(i) == ' ') {
                        members.add(letter);                        //adding the id if space is encountered into an arraylist
                        letter = "";
                    }
                    if (i == membersToBeAdded.length() - 1) {
                        members.add(letter);                        //adding the id, if string gets completely traversed, into an arraylist
                        letter = "";
                    }
                }


                //executing update for each of the element in arraylist
                for (int i = 0; i < members.size(); i++) {
                    memberExists = checkIfAlreadyExists(da, members.get(i));
                    if (!memberExists) {
                        ps = c.prepareStatement("INSERT INTO projectMembers (projectId, projectLeaderId, projectMemberId) VALUES (?, ?, ?)");
                        ps.setString(1, da.getMembersToBeAddedInProjectId());
                        ps.setString(2, projectLeaderId);
                        ps.setString(3, members.get(i));
                        System.out.println(ps);
                        status = ps.executeUpdate();
                    } else
                        status = 2;
                }
            }
            else
                status = 3;

        }
        catch(ClassNotFoundException e){
            System.out.println("ClassNotFound ");
            e.printStackTrace();
        }
        catch(SQLException e){
            System.out.println("SQLException found");
            e.printStackTrace();
        }
        return status;
    }

    boolean checkIfAlreadyExists(dashboardAction da, String checkData){

        boolean exists = false;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql = "SELECT projectMemberId FROM projectMembers WHERE projectMemberId = ? AND projectId = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, checkData);
            ps.setString(2, da.getMembersToBeAddedInProjectId());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                exists = true;
            else
                exists = false;
        }
        catch(ClassNotFoundException e){
            System.out.println("ClassNotFound ");
            e.printStackTrace();
        }
        catch(SQLException e){
            System.out.println("SQLException found");
            e.printStackTrace();
        }

        return exists;
    }
}
