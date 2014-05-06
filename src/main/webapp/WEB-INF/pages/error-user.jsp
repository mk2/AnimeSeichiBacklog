<%--
  Created by IntelliJ IDEA.
  User: lycaon_h
  Date: 2014/03/14
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/static-page-header.jsp" %>
    <title>${i18n.error.user.title}</title>
</head>
<body>

<div class="container">
    <div class="panel panel-danger">
        <div class="panel-heading">
            <h3 class="panel-title"><c:out value="${msg.title}"/></h3>
        </div>
        <div class="panel-body">
            <c:out value="${msg.body}"/>
        </div>
    </div>
    <p><a role="button" class="btn btn-danger btn-lg" href="${recovery.link}"><c:out value="${recovery.msg}"/></a></p>
</div>

</body>
</html>
