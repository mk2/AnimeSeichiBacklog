<%--
  Created by IntelliJ IDEA.
  User: lycaon_h
  Date: 2014/03/07
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/static-page-header.jsp" %>
    <title>${i18n.login.title}</title>
</head>
<body>

<%@ include file="common/static-page-nav.jsp" %>

<div class="container">
    <form:form action="/u/l" method="post" commandName="userFormModel" role="form" cssClass="form-signin">
        <h2 class="form-signin-heading">ログインする</h2>
        <form:input path="userEmail" cssClass="form-control" placeholder="メールアドレス" required="true"
                    autofocus="true" order="top"/>
        <form:password path="userPassword" cssClass="form-control" placeholder="パスワード" required="true"
                       order="bottom"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">ログイン</button>
    </form:form>
</div>

</body>
</html>
