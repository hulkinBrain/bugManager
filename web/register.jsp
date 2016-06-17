<%--
  Created by IntelliJ IDEA.
  User: soorya
  Date: 28-May-16
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="css/register.css">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <div class = "background">
            <h1 class = "center-align white-text">Register</h1>
        </div>
        <div class = "container formContainer white z-depth-2">
            <div class = "row formParent">
                <s:form class = "col l12 s12" action = "register">
                    <div class = "row">
                        <div class="input-field col l6 s6">
                            <s:textfield name = "firstName" id="firstName" type="text"/>
                            <label for="firstName">First Name</label>
                            <s:fielderror class = "fieldError">
                                <s:param>firstName</s:param>
                            </s:fielderror>
                        </div>
                        <div class="input-field col l6 s6">
                            <s:textfield name = "lastName" id="lastName" type="text"/>
                            <label for="lastName">Last Name</label>
                            <s:fielderror class = "fieldError">
                                <s:param>lastName</s:param>
                            </s:fielderror>
                        </div>
                    </div>
                    <div class = "row">
                        <div class="input-field col l12 s12">
                            <s:textfield name = "username" id="username" type="text"/>
                            <label for="username">Username</label>
                            <s:fielderror class = "fieldError">
                                <s:param>username</s:param>
                            </s:fielderror>
                        </div>
                    </div>
                    <div class = "row">
                        <div class="input-field col s6">
                            <s:password name = "password" id="password" type="password"  onfocus= "Materialize.toast('Password must be atleast 8 characters long', 5000)"/>
                            <label for="password">Enter password</label>
                            <s:fielderror class = "fieldError">
                                <s:param>password</s:param>
                            </s:fielderror>
                        </div>
                        <div class = 'input-field col s6'>
                            <s:password name = "repeatPassword" id="repeatPassword" type="password"/>
                            <label for="repeatPassword">Repeat password</label>
                            <s:fielderror class = "fieldError">
                                <s:param>repeatPassword</s:param>
                            </s:fielderror>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <s:textfield name = "email" id="email" type="email"/>
                            <label for="email">Email</label>
                            <s:fielderror class = "fieldError">
                                <s:param>email</s:param>
                            </s:fielderror>
                        </div>
                    </div>
                    <div class = "row formButtons">
                        <button class = 'btn waves-effect waves-light right' type = 'submit' name = 'submit'>Submit</button>
                        <a href = 'index.jsp' class = 'btn waves-effect waves-dark right cancelButton purple accent-1'>Cancel</a>
                    </div>
                </s:form>
            </div>
        </div>
    </body>
</html>
