package com.bugManager;

import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.lang.*;
import java.util.List;

public class dashboardAction implements ServletRequestAware{

    HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        servletRequest = httpServletRequest;
    }

    private ArrayList<userDetails> userList;
    private List<projects> projectList;
    private List<members> membersList;
    private List<members> existingMembersList;
    private List<bugs> existingBugsList;

    private String term;
    private String bugTitle;
    private String bugDesc;
    private String projectTitle;
    private String projectDetails;
    private String chosenViewMembers;   //to store the projectId of the project whose list option 'view members' has been chosen
    private String memberIdToBeDeleted;
    private String localProjectId;
    private String editBugTitle;
    private String editBugDesc;

    public String getEditBugDesc() {
        return editBugDesc;
    }

    public void setEditBugDesc(String editBugDesc) {
        this.editBugDesc = editBugDesc;
    }

    private String membersToBeAddedInProjectId; //to store the projectId of the project whose list option 'view members' has been chosen (for viewMembers.jsp)
    private String chosenBugId; //to store the id of the bug which has been chosen for editing

    private String userId;
    private String membersToBeAdded;


    /*public String execute() throws Exception{


        userList = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugmanager", "username", "qwer");

            PreparedStatement ps = c.prepareStatement("SELECT firstName FROM users WHERE firstName = ?");
            ps.setString(1, term + "%");
            /*ps.setString(2, term + "%");
            ps.setString(3, term + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                userDetails ud = new userDetails();
                ud.setFullName(rs.getString("firstName") + " " );
                System.out.println("First name = " + rs.getString("firstName"));
            }
            c.close();
        }
        catch(SQLException e){
            System.out.println("sqlException found");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("classNotFoundException found");
            e.printStackTrace();
        }

        System.out.println(userList);
        return "success";


    }*/

    public String addBug(){
        addBugDao abd = new addBugDao();
        int status;
        status = abd.add(this);
        if(status == 1)
            return "success";
        else
            return "error";
    }

    public String addProject(){
        // Load from cookie
        fetchUserId();
        loginAction la = new loginAction();
        System.out.println("USERID: " + userId);
        addProjectDao ap = new addProjectDao();
        int status;
        status =  ap.add(this);
        if(status == 1){
            System.out.println("in dashboard after writing project");
            return "success";
        }
        else
            return "error";
    }

    public String viewProjects(){

        viewProjectsDao vpd = new viewProjectsDao();
        projectList = vpd.getProjects(this);
        if(projectList == null)
            System.out.println("projectList is null");
        return "success";
    }

    /*public String viewBugs(){
        viewBugsDao vb = new viewBugsDao();

    }*/

    public String viewMembersList(){

        viewMembersListDao vmd = new viewMembersListDao();
        membersList = vmd.getMembers(this);
        System.out.println("CHOSEN VIEW MEMBERS: " + chosenViewMembers);

        if(membersList == null)
            System.out.println("membersList is null");
        else
            System.out.println("membersList is full" + membersList.size());
        return "success";
    }

    public String addMembers(){

        viewMembersList();
        int status;
        System.out.println("CHOSEN VIEW MEMBERS IN addMembers: " + membersToBeAddedInProjectId);
        addMembersDao amd = new addMembersDao();
        status = amd.add(this);

        if(status == 1)
            return "success";
        else if(status == 2)
            return "userExists";
        else if(status == 3)
            return "noPermission";
        else
            return "error";
    }

    public String deleteMember(){
        System.out.println("in deleteMember");
        System.out.println("membersToBeaddedInProjectId" + localProjectId);
        int status;
        deleteMemberDao dmd = new deleteMemberDao();
        status = dmd.delete(this);
        if(status == 1)
            return "success";
        else if(status == 3)
            return "noPermission";
        else
            return "error";
    }

    public String viewExistingMembers(){
        viewExistingMembersDao vemd = new viewExistingMembersDao();
        existingMembersList = vemd.getMembers(this);
        System.out.println("existingMembersList.size() = " + existingMembersList.size());
        if(existingMembersList.size() == 0)
            System.out.println("existingMembersList is null");
        else
            System.out.println("existingMembersList is full" + existingMembersList.size());

        return "success";
    }

    public String viewExistingBugsList(){
        viewExistingBugsListDao vebld = new viewExistingBugsListDao();
        System.out.println("chosenViewMembers in viewExistingBugs: " + chosenViewMembers);
        existingBugsList = vebld.show(this);
        if(existingBugsList.size() == 0){
            System.out.println("existingBugsList is null");
        }
        else{

            System.out.println("existingBugsList is full");
        }
        return "success";
    }

    public String isEditable(){
        int status;
        viewExistingBugsList();
        System.out.println("chosenViewmembers: " + chosenViewMembers);
        isEditableDao ied = new isEditableDao();
        status = ied.canEdit(this);
        editBugTitle = ied.returnEditBugTitle();
        editBugDesc = ied.returnEditBugDesc();
        System.out.println("editBugTitle = " + editBugTitle);
        System.out.println("editBugDesc = " + editBugDesc);
        System.out.println("bug ID in dashboard : " + chosenBugId);
        if(status == 1)
            return "success";
        else
            return "error";

    }
    public String editBug(){
        this.viewExistingBugsList();
        int status;
        editBugDao ebd = new editBugDao();
        status = ebd.edit(this);
        if(status == 1)
            return "success";
        else
            return "error";
    }

    public String cancelEditBug(){
        int status;
        cancelEditBugDao cebd = new cancelEditBugDao();
        status = cebd.canceEdit(this);
        if(status == 1)
            return "success";
        else
            return "error";
    }

    public String removeBug(){
        int status;
        removeBugDao rbd = new removeBugDao();
        status = rbd.remove(this);
        if(status == 1) {
            System.out.println("deleted bug");
            return "success";
        }
        else if(status == 2)
            return "noPermission";
        else {
            System.out.println("couldnt delete bug");
            return "error";
        }
    }

    public String setBugStatus(){
        int status;
        setBugStatusDao sbsd = new setBugStatusDao();
        status = sbsd.setStatus(this);
        if(status == 1)
            return "success";
        else
            return "error";
    }
    public String doSomething(){
        return "success";
    }

    public String deleteProject(){

        int status;
        deleteProjectDao dpd = new deleteProjectDao();
        status = dpd.removeProject(this);
        if(status == 1)
            return "success";

        else if(status == 2)
            return "noPermission";

        else
            return "error";
    }

    String fetchUserId(){
        for(Cookie c : servletRequest.getCookies()) {
            if (c.getName().equals("userIdCookie"))
                userId = c.getValue();
        }
        return userId;
    }



    //getters and setters

    public String getEditBugTitle() {
        return editBugTitle;
    }

    public void setEditBugTitle(String editBugTitle) {
        this.editBugTitle = editBugTitle;
    }

    public String getChosenBugId() {
        return chosenBugId;
    }

    public void setChosenBugId(String chosenBugId) {
        this.chosenBugId = chosenBugId;
    }

    public List<bugs> getExistingBugsList() {
        return existingBugsList;
    }

    public void setExistingBugsList(List<bugs> existingBugsList) {
        this.existingBugsList = existingBugsList;
    }

    public String getLocalProjectId() {
        return localProjectId;
    }

    public void setLocalProjectId(String localProjectId) {
        this.localProjectId = localProjectId;
    }

    public String getMemberIdToBeDeleted() {
        return memberIdToBeDeleted;
    }

    public void setMemberIdToBeDeleted(String memberIdToBeDeleted) {
        this.memberIdToBeDeleted = memberIdToBeDeleted;
    }

    public List<members> getExistingMembersList() {
        return existingMembersList;
    }

    public void setExistingMembersList(List<members> existingMembersList) {
        this.existingMembersList = existingMembersList;
    }

    public String getMembersToBeAddedInProjectId() {
        return membersToBeAddedInProjectId;
    }

    public void setMembersToBeAddedInProjectId(String membersToBeAddedInProjectId) {
        System.out.println("SETTING VALUE OF membersTOBEADDED");
        this.membersToBeAddedInProjectId = membersToBeAddedInProjectId;
    }

    public String getMembersToBeAdded() {
        return membersToBeAdded;
    }

    public void setMembersToBeAdded(String membersToBeAdded) {
        this.membersToBeAdded = membersToBeAdded;
    }

    public List<members> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<members> membersList) {
        this.membersList = membersList;
    }

    public String getChosenViewMembers() {
        return chosenViewMembers;
    }

    public void setChosenViewMembers(String chosenViewMembers) {
        this.chosenViewMembers = chosenViewMembers;
    }

    public List<projects> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<projects> projectList) {
        this.projectList = projectList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getBugDesc() {
        return bugDesc;
    }

    public void setBugDesc(String bugDesc) {
        this.bugDesc = bugDesc;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public ArrayList<userDetails> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<userDetails> userList) {
        this.userList = userList;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
