<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<%  session = request.getSession(false);
    Cookie[] cookies = request.getCookies();
    String loginId = null;
    String username = null;
    Cookie usernameCookie = null;
    Cookie userIdCookie = null;
    for(int i = 0; i < cookies.length; i++) {
        Cookie c = cookies[i];

        if (c.getName().equals("userIdCookie")) {
            loginId = c.getValue();
            userIdCookie = c;
            System.out.println("settingValue of userIdCookie in dashboard");
        }
        if (c.getName().equals("usernameCookie")){
            username = c.getValue();
            usernameCookie = c;
        }
    }
    if(loginId != null){
        System.out.println("lodinId: " + userIdCookie.getValue());
        System.out.println("username: " + usernameCookie.getValue());
%>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/dashboard.css">

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<!--Import jQuery before materialize.js-->



<ul id = "slide-out" class="side-nav fixed indigo accent-3">
    <li class = "logoContainer center-align">
        <img src="images/bug.svg" class = "logoImage" /><br>
        <span class = "welcomeUserText black-text white">Welcome <% out.print(username); %>!</span>
    </li>
    <li class = "normalListItem" id = "viewProjects">

            <a class = "white-text waves-effect waves-light" type="submit">View Projects</a>
    </li>
    <li class = "normalListItem" id = "projectMembers"><a class = "white-text waves-effect waves-light">Project Members</a></li>
    <li class="no-padding normalListItem">
        <ul class="collapsible collapsible-accordion" >
            <li  class = "viewBugsTab">
                <a class="collapsible-header white-text waves-effect waves-light">View Bugs</a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="#!" class = "waves-effect waves-dark">Unsolved Bugs</a></li>
                        <li><a href="#!" class = "waves-effect waves-dark">Solved Bugs</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </li>
    <li>
        <s:form action = "logout" class = "center-align" id = "logoutButtonForm">
            <button class = "btn white waves-effect waves-dark black-text" value = "submit">Logout</button>
        </s:form>
    </li>
</ul>
<a href="#" data-activates="slide-out" class="button-collapse"><i class="mdi-navigation-menu"></i></a>

<div class = "restOfThePage">

    <nav>
        <div class="nav-wrapper indigo accent-2">
            <a href="#" class="brand-logo left navHeading">Projects</a>
        </div>
    </nav>

    <!-- floating button start-->
    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
        <a class="btn-floating btn-large red addButton center-align">
            +
        </a>
        <ul>
            <li><a class="btn-floating red accent-3 tooltipped modal-trigger" href = "#addProjectModal" data-position="left" data-delay="50" data-tooltip="Add Project"><i class="material-icons">note_add</i></a></li>
            <li><a class="btn-floating indigo accent-3 tooltipped modal-trigger" href="#addBugModal" data-position="left" data-delay="50" data-tooltip="Add Bug" id = "addBug"><i class="material-icons">bug_report</i></a></li>
            <li><a class="btn-floating blue tooltipped" data-position="left" data-delay="50" data-tooltip="Add Person"><i class="material-icons">contacts</i></a></li>
        </ul>
    </div>
    <!-- floating button end -->

    <!--search bar for adding users-->
    <nav class = "searchBarContainer indigo accent-1 black-text">
        <div class="input-field white z-depth-1" id = "searchBar">
            <s:textfield id="search" type="search" placeholder = "Enter person's name here" name = "search"/>
            <label for="search"><i class="material-icons black-text">search</i></label>
            <i class="material-icons">close</i>
        </div>
    </nav>
    <!--search bar end-->



    <!-- all the modals -->

    <!-- addBugModal -->
    <div id="addBugModal" class="modal cyan darken-3 modal-fixed-footer">
        <div class="modal-content">
            <h4 class = "white-text modalHeader">Bug Details</h4>
            <div class = "row formContainer">
                <s:form action = "addBug">
                    <div class = "row">
                        <div class="input-field col s6 white-text">
                            <s:textfield id="bugTitle" type="text"/>
                            <label for="bugTitle">Enter Bug Title</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 white-text">
                            <s:textarea id="bugDesc" class="materialize-textarea"/>
                            <label for="bugDesc" class = "white-text">Enter Bug Details</label>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>
        <div class="modal-footer cyan darken-4">
            <a class="modal-action modal-close waves-effect waves-green btn-flat addFooterButton white-text" type = "submit">Add</a>
        </div>
    </div>

    <!--add project modal -->

    <div id="addProjectModal" class="modal cyan darken-3 modal-fixed-footer">
        <div class="modal-content">
            <h4 class = "white-text modalHeader">Project Details</h4>
            <div class = "row formContainer">
                <s:form action = "addProject">
                <div class = "row">
                    <div class="input-field col s6 white-text">
                        <s:textfield id="projectTitle" name = "projectTitle" type="text"/>
                        <label for="projectTitle">Enter Project Title</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12 white-text">
                        <s:textarea id="projectDesc" name = "projectDetails" class="materialize-textarea"/>
                        <label for="projectDesc" class = "white-text">Enter Project Details</label>
                    </div>
                </div>

            </div>
        </div>
        <div class="modal-footer cyan darken-4">
            <button class="modal-action modal-close waves-effect waves-green btn-flat addFooterButton white-text" type = "submit">Add</button>
        </div>
        </s:form>
    </div>
    <!-- modals end -->
</div>

<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        $('.modal-trigger').leanModal();
        $(".button-collapse").sideNav();
        // Initialize collapsible (uncomment the line below if you use the dropdown variation)
        $('.collapsible').collapsible();

    });
</script>

</body>
<%
    }
    else {
        System.out.println("dashboard null cookie");
        response.sendRedirect("index.jsp");
    }
%>
</html>
