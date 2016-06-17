<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/index.css">

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>

        <div class = "valign-wrapper">
            <div id = "bugIconContainer" class = "center-align valign purple accent-4 z-depth-1">
                <img src = "images/bug.svg" alt = "bug image"  id = "bugIcon">
            </div>
        </div>
        <h3 class = "center-align bugManager">Bug Manager</h3>


        <div class="loginDiv container">
            <div class="row">
                <s:form class="col l12 s12 loginForm" action = "login">
                    <div class="row">
                        <div class="input-field col l12 s12">
                            <s:textfield id="username" type="text" class="validate" name = "username"/>
                            <label for="username">Username</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col l12 s12">
                            <s:password id="password" type="password" class="validate" name = "password"/>
                            <label for="password">Password</label>
                        </div>
                    </div>
                    <div class = "row formButtons">
                        <button class="btn waves-effect waves-light right submitButton purple accent-4" type="submit">Login</button>
                        <button class = "btn waves-effect waves-dark right forgotPasswordButton purple accent-1" name = "forgotPassword">Forgot Password</button>
                    </div>
                </s:form>
            </div>
        </div>
        <footer class="purple accent-2 registerDiv">
            <span class="white-text">Not yet registered?</span>
            <a href= "register.jsp">
                <button class="btn waves-effect waves-light grey darken-4" name="register" id="registerButton">Register
                </button>
            </a>

        </footer>

    </body>
</html>
