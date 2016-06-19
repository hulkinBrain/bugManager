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
        <link type="text/css" rel="stylesheet" href="css/editBug.css">

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
            <s:form action = "viewProjectsAction" class = "viewForm" id = "viewForm">
                <a class = "white-text waves-effect waves-light" onclick="document.getElementById('viewForm').submit()">View Projects</a>
            </s:form>
        </li>
        <li class = "normalListItem" id = "projectMembers"><a class = "white-text waves-effect waves-light">Project Members</a></li>
        <li class="no-padding normalListItem">
            <ul class="collapsible collapsible-accordion" >
                <li class = "viewBugsTab">
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
            <div class="nav-wrapper orange">
                <a href="#" class="brand-logo left navHeading black-text">Edit Bug</a>
            </div>
        </nav>

        <div class = "editFormContainer white">
            <s:form id = "editBugForm" action = "editBugAction">
                <s:property value="chosenViewMembers"/>
                <s:hidden name = "chosenViewMembers" value = "%{chosenViewMembers}"/>
                <s:hidden name = "chosenBugId" value = "%{chosenBugId}"/>
                <div class = "row">
                    <div class="input-field col s6">
                        <s:textfield id="bugTitle" name = "bugTitle" value = "%{editBugTitle}" class = "bugTitle"/>
                        <label for="bugTitle">Enter Bug Title</label>
                        <span class = "errorMsg" id = "bugTitleError"></span>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s12">
                        <s:textarea id="bugDesc" class="materialize-textarea bugDesc" name = "bugDesc" value = "%{editBugDesc}"/>
                        <label for="bugDesc">Enter Bug Details</label>
                        <span class = "errorMsg" id = "bugDescError"></span>
                    </div>
                </div>

                <div class = "row formButtons">
                    <a class = 'btn waves-effect waves-light right submitButton' onclick = 'document.getElementById("editBugForm").submit();'>Update</a>
                    <a class = 'btn waves-effect waves-dark right cancelButton purple accent-1'>Cancel</a>
                </div>
            </s:form>
        </div>


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