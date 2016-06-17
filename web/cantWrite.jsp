<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/cantWrite.css">

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body style = "background-color: rgb(240, 240, 240) ;" class = "container">

    <div class = 'card hoverable grey darken-2'>
        <h1 style = "font-family: 'robotoLight', sans-serif;">Can't Write Data</h1>
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
</html>
