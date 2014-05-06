<%--
  Created by IntelliJ IDEA.
  User: lycaon_h
  Date: 2014/03/06
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/static-page-header.jsp" %>
    <title>${i18n.register.title}</title>
</head>
<body>

<%@ include file="common/static-page-nav.jsp" %>

<div class="container" style="width:75%;">
    <form:form action="/u/c" method="post" commandName="userFormModel"
               role="form" cssClass="form-horizontal" enctype="multipart/form-data">
        <h2 class="form-signin-heading">ユーザーを作成する</h2>

        <div class="form-group">
            <label for="userAlias" class="col-sm-2 control-label">表示名</label>

            <div class="col-sm-10">
                <form:input path="userAlias" cssClass="form-control" placeholder="表示名" required="true"
                            autofocus="true" id="userAlias"/>
                <span class="help-block">他のユーザーに公開されます。</span>
            </div>
        </div>

        <div class="form-group">
            <label for="userEmail" class="col-sm-2 control-label">メールアドレス</label>

            <div class="col-sm-10">
                <form:input path="userEmail" cssClass="form-control" placeholder="メールアドレス"
                            required="true" id="userEmail"/>
                <span class="help-block">他のユーザーには公開されません。</span>
            </div>
        </div>

        <div class="form-group">
            <label for="userPassword" class="col-sm-2 control-label">パスワード</label>

            <div class="col-sm-10">
                <form:password path="userPassword" cssClass="form-control" placeholder="パスワード"
                               required="true" id="userPassword"/>
            </div>
        </div>

        <div class="form-group">
            <label for="userTags" class="col-sm-2 control-label">タグ</label>

            <div class="col-sm-10">
                <form:input path="userTags" cssClass="form-control" placeholder="タグ" id="userTags"/>
            </div>
        </div>

        <div class="form-group">
            <label for="userImageFile" class="col-sm-2 control-label">プロフィール画像</label>

            <div class="col-sm-10">
                <input type="file" name="userImageFile" id="userImageFile"/>
                <span class="help-block">10メガバイトまでの各種画像ファイル</span>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">ユーザー作成</button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
