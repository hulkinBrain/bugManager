<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
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
        <link type="text/css" rel="stylesheet" href="css/viewMembers.css">

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
        <li class = "normalListItem" id = "projectMembers">
            <s:form action = "viewExistingMembersTabAction" class = "viewForm" id = "viewMembersForm">
                <s:hidden name = "membersToBeAddedInProjectId" value = "%{chosenViewMembers}"/>
                <a class = "white-text waves-effect waves-light" onclick="document.getElementById('viewMembersForm').submit()">Project Members</a>
            </s:form>
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
            <div class="nav-wrapper red darken-3">
                <a href="#" class="brand-logo left navHeading">Members</a>
            </div>
        </nav>
        <!--search bar for adding users-->
        <nav class = "searchBarContainer red darken-1 black-text">
            <div class="input-field red accent-1" id = "searchBar">
                <s:textfield id = "search" type="search" placeholder = "Enter name of the person to add" name = "search" class = "searchField"/>
                <label for="search"><i class="material-icons black-text">search</i></label>
                <i class="material-icons closeButton">close</i>
            </div>

            <div id = "membersListDropdown" class = "card">
                <s:form action = "addMembersAction" id = "addMembersForm">

                    <s:checkboxlist list="membersList" name = "membersToBeAdded" listKey="membersId" class = "checkbox"
                                    listValue="membersFirstName + ' ' + membersLastName + ' (Username: ' + membersUsername + ')' "/>
                    <s:hidden name = "membersToBeAddedInProjectId" value = "%{chosenViewMembers}"/>
                    <div class = "buttonContainer right-align">
                        <button class = "btn waves-effect waves-light grey darken-3 addUsersButton" type = "submit">Add</button>
                    </div>
                </s:form>
            </div>
        </nav>
        <!--search bar end-->
        <div id = "existingUsersContainer" class = "container">
            <ul class="collapsible popout" data-collapsible="accordion">
                
                <s:iterator value = "existingMembersList" status = "status" var = "projectDetails">
                    <li>
                        <div class="collapsible-header">
                            <i class="material-icons">person_pin</i>
                            <s:property value="membersFirstName"/> <s:property value="membersLastName"/>
                        </div>
                        <div class = "right deleteUserFormContainer">
                            <s:form action = "deleteUserAction" id = "deleteMember">
                                <s:hidden name = "memberIdToBeDeleted" value = "%{membersId}"/>
                                <s:hidden name = "membersToBeAddedInProjectId" value = "%{chosenViewMembers}"/>
                                <a class = "modal-trigger" style = "color: inherit;" href = "#confirm"><i class = "material-icons right">close</i></a>
                                <!-- userDelete confirmation start-->
                                <div id="confirm" class="modal">
                                    <div class="modal-content">
                                        <p class = "center-align" style = "font-size: 3vmin">Do you really want to delete this user?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type = "submit" class="modal-action modal-close waves-effect waves-green btn-flat">Yes</button>
                                        <a onclick = "$('#confirm').closeModal();" class=" modal-action modal-close waves-effect waves-green btn-flat">Cancel</a>
                                    </div>
                                </div>
                                <!--userDelete confirmation end-->
                            </s:form>
                        </div>
                        <div class="collapsible-body"><p>Username: <s:property value = "membersUsername"/></p></div>

                    </li>
                </s:iterator>

            </ul>
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
            $('#search')
                .focus(function(){
                    $('#membersListDropdown').slideDown("swing");
                })

                .keyup(function(){
                    var searchBoxValue = $('#search').val().toLowerCase();
                    var count = 0
                    $('label[class = "checkboxLabel"]')
                        .hide()
                        .filter(function(){
                            if(!(this.innerText.toLowerCase().indexOf(searchBoxValue) > -1)){

                                var $for = $(this).attr("for");
                                var $storeForValues = $($for);
                                $('#' + $storeForValues.selector).hide();
                            }
                            else{
                                var $for = $(this).attr("for");
                                var $storeForValues = $($for);
                                $('#' + $storeForValues.selector).show();
                            }
                            return this.innerText.toLowerCase().indexOf(searchBoxValue) > -1;
                        })
                        .show();
                })




        });
        $(document)
            .on('click', function(e){
                if(!$(e.target).hasClass('addMembersForm') && !$(e.target).hasClass('searchField') && !$(e.target).hasClass('checkboxLabel') && !$(e.target).hasClass('checkbox')){
                    $('#membersListDropdown').slideUp("swing");
                }
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
