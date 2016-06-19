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
                <s:form action = "viewProjectsAction" class = "viewForm" id = "viewForm">
                    <a class = "white-text waves-effect waves-light" onclick="document.getElementById('viewForm').submit()">View Projects</a>
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
                </ul>
            </div>
            <!-- floating button end -->

            <div class = "projectCardsContainer">

                <s:iterator value = "projectList" status = "status" var = "projectDetails">
                    <div id = "projectCard" class="card projectCard">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" src="images/office.jpg">
                        </div>
                        <div class="card-content">
                            <span class="projectCardTitle card-title grey-text text-darken-4"><s:property value="projectTitle"/></span>
                            <s:div id = 'listExpand' class = "material-icons medium dropdown-button waves-light waves-effect right" data-activates='divId%{#status.count}'>list</s:div>
                            <p class="activator">Click to expand</p>
                        </div>

                        <div class="card-reveal">
                            <span class="card-title grey-text text-darken-4"><s:property value="projectTitle"/><i class="material-icons right">close</i></span>
                            <p><b>Project Head:</b> <s:property value="projectHeadUsername"/></p>
                            <p><b>Project Details:</b><br><s:property value="projectDetails"/></p>
                        </div>
                    </div>

                        <s:div id='divId%{#status.count}' class='dynamicViewProjectViewOptions dropdown-content'>
                            <s:url id = "urlValue1" action = "viewBugsAction">
                                <s:param name = "chosenViewMembers"><s:property value = "projectId"/></s:param>
                            </s:url>
                            <li><s:a href = "%{urlValue1}" class = "waves-effect waves-dark">View Bugs</s:a></li>

                            <s:url id = "urlValue" action = "viewMembersAction">
                                <s:param name = "chosenViewMembers"><s:property value = "projectId"/></s:param>
                            </s:url>
                            <li><s:a href = "%{urlValue}" class = "waves-effect waves-dark">View Members</s:a></li>

                            <s:url id = "urlValue2" action = "deleteProjectAction">
                                <s:param name = "chosenViewMembers"><s:property value = "projectId"/></s:param>
                            </s:url>
                            <li><s:a href = "%{urlValue2}" class = "waves-effect waves-dark">Delete Project</s:a></li>
                        </s:div>

                </s:iterator>

                <!--/s:form-->
            </div>


            <!-- all the modals -->


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

            //for expanding the list options in project cards
            $(document)
                .on('click', function (e) {
                    if (!$(e.target).hasClass('dropdown-button')) {
                        $('.card').removeClass('card--visible-overflow');
                    }
                    else if($(e.target).hasClass('dropdown-button active')){
                        $('.card').removeClass('card--visible-overflow');
                    }
                })

                .on('click', '.card .dropdown-button', function () {
                    var $card = $(this).closest('.card'),
                            openedClass = 'card--visible-overflow',
                            dropDownIsOpened = $card.hasClass(openedClass);

                    if (!dropDownIsOpened) {
                        $card.addClass(openedClass);
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
