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
            PreparedStatement ps, ps2;
            ResultSet rs, rs2;

            // fields have been SELECTed individually for easier deletion or addition of fields in future
            sql = "SELECT bugId, bugTitle, bugDesc, bugGeneratedById, bugGeneratedOn, bugSolvedById, bugSolvedOn, relatedProjectid, updateLock "+
                    "FROM bugs WHERE relatedprojectId = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.getChosenViewMembers());

            rs = ps.executeQuery();
            while(rs.next()){
                bugs bug = new bugs();
                bug.setBugId(rs.getString("bugId"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugDesc(rs.getString("bugDesc"));
                bug.setBugGeneratedById(rs.getString("bugGeneratedById"));
                bug.setBugGeneratedOn(rs.getString("bugGeneratedOn"));

                ps2 = c.prepareStatement("SELECT firstName, lastName FROM users WHERE id = ?");
                ps2.setString(1, rs.getString("bugGeneratedById"));
                rs2 = ps2.executeQuery();
                if(rs2.next())
                    bug.setBugRaisedByName(rs2.getString("firstName") + " " + rs2.getString("lastName"));

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
                        bug.setBugSolvedByName(rs1.getString("firstName") + " " +rs1.getString("lastName"));
                }

                if(rs.getString("bugSolvedOn") == null)
                    bug.setBugSolvedOn("UNSOLVED");
                else
                    bug.setBugSolvedOn("SOLVED: " + rs.getString("bugSolvedOn"));

                bug.setRelatedProjectId(rs.getString("relatedProjectId"));
                bug.setUpdateLock(rs.getString("updateLock"));

                existingBugsList.add(bug);
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
        return existingBugsList;
    }

    public List<bugs> getExistingBugsList() {
        return existingBugsList;
    }

    public void setExistingBugsList(ArrayList<bugs> existingBugsList) {
        this.existingBugsList = existingBugsList;
    }
}
