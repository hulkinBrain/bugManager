package com.bugManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class viewExistingBugsListDao {

    ArrayList<bugs> existingBugsList = new ArrayList<>();

    public List<bugs> show(dashboardAction da){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            String sql;
            PreparedStatement ps;
            ResultSet rs;

            // fields have been SELECTed individually for easier deletion or addition of fields in future
            sql = "SELECT bugId, bugTitle, bugDesc, bugGeneratedById, bugGeneratedOn, bugSolvedById, bugSolvedOn, relatedProjectid, updateLock "+
                    "FROM bugs WHERE relatedprojectId = ?";
            ps = c.prepareStatement(sql);
            System.out.println("getMemberstoBeAddedInProjectId = " + da.getChosenViewMembers());
            ps.setString(1, da.getChosenViewMembers());

            rs = ps.executeQuery();
            while(rs.next()){
                bugs bug = new bugs();
                bug.setBugId(rs.getString("bugId"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugDesc(rs.getString("bugDesc"));
                bug.setBugGeneratedById(rs.getString("bugGeneratedById"));
                bug.setBugGeneratedOn(rs.getString("bugGeneratedOn"));

                if(rs.getString("bugSolvedById") == null)
                    bug.setBugSolvedById("");
                else {
                    bug.setBugSolvedById(rs.getString("bugSolvedById"));
                    PreparedStatement ps1;
                    ps1 = c.prepareStatement("SELECT firstName, lastName FROM users WHERE id = ?");
                    ps1.setString(1, rs.getString("bugSolvedById"));
                    ResultSet rs1;
                    rs1 = ps1.executeQuery();

                    if(rs1.next())
                        bug.setBugSolvedByName(rs.getString("firstName") + " " +rs.getString("lastName"));
                }

                if(rs.getString("bugSolvedOn") == null)
                    bug.setBugSolvedOn("UNSOLVED");
                else
                    bug.setBugSolvedOn("SOLVED: " + rs.getString("bugSolvedOn"));

                bug.setRelatedProjectId(rs.getString("relatedProjectId"));
                bug.setUpdateLock(rs.getString("updateLock"));

                System.out.println("adding a bug into the list");
                existingBugsList.add(bug);
            }
            System.out.println("size of bugList = " + existingBugsList.size());
        }
        catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundExcetion found");
            e.printStackTrace();
        }
        catch(SQLException e) {
            System.out.println("SQLExcetion found");
            e.printStackTrace();
        }
        return existingBugsList;
    }

    public List<bugs> getExistingBugsList() {
        return existingBugsList;
    }

    public void setExistingBugsList(ArrayList<bugs> existingBugsList) {
        this.existingBugsList = existingBugsList;
    }
}
