<%--
  Created by IntelliJ IDEA.
  User: soorya
  Date: 15-Jun-16
  Time: 12:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<body>
<h1>Struts 2 multiple check boxes example</h1>

<h2>
    Favor colors : <s:property value="membersToBeAdded"/>
</h2>

<s:iterator value = "existingMembersList">
    <s:property value = "membersFirstName"/>
</s:iterator>

</body>
</html>
