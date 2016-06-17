package com.bugManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class viewProjectsDao {

    private ArrayList<projects> projectList = new ArrayList<>();

    public ArrayList<projects> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<projects> projectList) {
        this.projectList = projectList;
    }

    public List<projects> getProjects(dashboardAction da) {

        System.out.println("userId viewProjects: " + da.fetchUserId());
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");
            System.out.println("Chosen projectID: " + da.getChosenViewMembers());
            String sql;
            sql = "SELECT projectId, projectTitle, projectDetails, projectLeaderId, projectHeadUsername FROM projects WHERE projectLeaderId = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            System.out.println(da.fetchUserId());
            ps.setString(1, da.fetchUserId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projects project = new projects();
                project.setProjectId(rs.getString("projectId"));
                project.setProjectTitle(rs.getString("projectTitle"));
                project.setProjectDetails(rs.getString("projectDetails"));
                project.setProjectLeaderId(rs.getString("projectLeaderId"));
                project.setProjectHeadUsername(rs.getString("projectHeadUsername"));
                projectList.add(project);
            }

            sql = "SELECT projectMembers.projectId FROM projectMembers WHERE projectMembers.projectMemberId = ? "+
                    "AND ? != projectMembers.projectLeaderId";
            ps = c.prepareStatement(sql);
            ps.setString(1, da.fetchUserId());
            ps.setString(2, da.fetchUserId());

            rs = ps.executeQuery();
            ArrayList<String> projectId = new ArrayList<>();
            while(rs.next())
                projectId.add(rs.getString("projectMembers.projectId"));

            sql = "SELECT projectId, projectTitle, projectDetails, projectLeaderId, projectHeadUsername FROM projects WHERE projectId = ?";
            for(int i=0; i< projectId.size(); i++) {
                ps = c.prepareStatement(sql);
                ps.setString(1, projectId.get(i));
                rs = ps.executeQuery();
                while (rs.next()) {
                    projects project = new projects();
                    project.setProjectId(rs.getString("projectId"));
                    project.setProjectTitle(rs.getString("projectTitle"));
                    project.setProjectDetails(rs.getString("projectDetails"));
                    project.setProjectLeaderId(rs.getString("projectLeaderId"));
                    project.setProjectHeadUsername(rs.getString("projectHeadUsername"));
                    projectList.add(project);
                }
            }
            System.out.println("projectList size = " + projectList.size());

        } catch (SQLException e) {
            System.out.println("SQLException Found");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException Found");
            e.printStackTrace();
        }
        return projectList;
    }
}
