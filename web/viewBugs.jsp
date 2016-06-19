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
        <link type="text/css" rel="stylesheet" href="css/viewBugs.css">

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
                <a href="#" class="brand-logo left navHeading black-text">Bugs</a>
            </div>
        </nav>

        <div id = "existingBugsContainer" class = "container">
            <ul class="collapsible popout" data-collapsible="accordion">

                <s:iterator value = "existingBugsList" status = "status" var = "bugsDetails">
                    <li>
                        <div class="collapsible-header">
                            <i class="material-icons left">bug_report</i>
                            <span class = "bugStatus">[<s:property value = "bugSolvedOn"/>]</span>
                            <s:property value="bugTitle"/>
                            <span class = "right" style = 'margin-right: 5%; font-family: "robotoBold", sans-serif'>
                                Generated on: <s:property value="bugGeneratedOn"/>
                            </span>
                        </div>
                        <div class = "right bugListOptions">
                            <s:a class = "dropdown-button" data-beloworigin="true" data-activates = "bugList%{#status.count}"><i class = "material-icons right">list</i></s:a>
                        </div>
                        <div class="collapsible-body white">
                            <p>Solved By: <s:property value="bugSolvedByName"/> </p>

                                <p style = 'font-family: "robotoBold", sans-serif'>Bug Description:</p> <br>
                                <pre><s:property value = "bugDesc"/></pre>

                        </div>
                    </li>
                    <s:div id='bugList%{#status.count}' class='dropdown-content bugListItems' data-constrainwidth = "false">
                        <s:url id = "urlValue" action = "checkIsEditable">
                            <s:param name = "chosenViewMembers"><s:property value = "%{chosenViewMembers}"/></s:param>
                            <s:param name = "chosenBugId"><s:property value = "%{bugId}"/></s:param>
                        </s:url>
                        <li><s:a href = '%{urlValue}'>Edit</s:a></li>

                        <s:url id = "urlValue2" action = "setBugStatusAction">
                            <s:param name = "chosenViewMembers"><s:property value = "%{chosenViewMembers}"/></s:param>
                            <s:param name = "chosenBugId"><s:property value = "%{bugId}"/></s:param>
                        </s:url>

                        <li><s:a href = "%{urlValue2}">Mark Solved/Unsolved</s:a></li>


                        <s:url id = "urlValue1" action = "removeBugAction">
                            <s:param name = "chosenViewMembers"><s:property value = "%{chosenViewMembers}"/></s:param>
                            <s:param name = "chosenBugId"><s:property value = "%{bugId}"/></s:param>
                        </s:url>

                        <li><s:a class = "modal-trigger" href = "#confirm%{#status.count}">Delete</s:a></li>

                    </s:div>

                    <!-- userDelete confirmation start-->

                    <s:div id="confirm%{#status.count}" class="modal">
                        <div class="modal-content">
                            <p class = "center-align" style = "font-size: 3vmin">Do you really want to delete this user?</p>
                        </div>
                        <div class="modal-footer">
                            <s:a href = "%{urlValue1}" class="modal-action modal-close waves-effect waves-green btn-flat">Yes</s:a>
                            <a class=" modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                        </div>
                    </s:div>

                    <!--userDelete confirmation end-->

                </s:iterator>

            </ul>
        </div>
    </div>

    <!--floating button start-->
    <div class="fixed-action-btn" style="bottom: 40px; right: 40px;">
        <a class="btn-floating btn-large waves-effect waves-light red tooltipped modal-trigger" href = "#addBugModal" data-position="left" data-delay="50" data-tooltip="Add Bug"><i class="material-icons">add</i></a>
    </div>
    <!--floating button end-->

    <!-- addBugModal start-->
    <div id="addBugModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class = "modalHeader" style = "margin-left: 0.75rem; font-family: 'robotoBold', sans-serif">Bug Details</h4>
            <div class = "row formContainer">
                <s:form action = "addBugAction" id = "addBugForm">
                    <div class = "row">
                        <div class="input-field col s6">
                            <s:textfield id="bugTitle" name = "bugTitle"/>
                            <label for="bugTitle">Enter Bug Title</label>
                        </div>
                    </div>
                    <s:hidden name = "chosenViewMembers" value = "%{chosenViewMembers}"/>
                    <div class="row">
                        <div class="input-field col s12">
                            <s:textarea id="bugDesc" class="materialize-textarea" name = "bugDesc"/>
                            <label for="bugDesc">Enter Bug Details</label>
                        </div>
                    </div>
                </s:form>
            </div>
        </div>
        <div class="modal-footer orange">
            <a class="modal-action modal-close waves-effect waves-dark btn-flat addFooterButton" style = 'font-family: "robotoBold", sans-serif'
               onclick="document.getElementById('addBugForm').submit();">Add</a>
        </div>
    </div>
    <!-- addBugModal end-->

    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.modal-trigger').leanModal();
            $(".button-collapse").sideNav();
            // Initialize collapsible (uncomment the line below if you use the dropdown variation)
            $('.collapsible').collapsible();
            $('.dropdown-button').dropdown({
                constrain_width: false
            });

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