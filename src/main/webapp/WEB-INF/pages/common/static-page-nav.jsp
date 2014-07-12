<%--
  Created by IntelliJ IDEA.
  User: lycaon_h
  Date: 2014/03/14
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">${i18n.index.title}</a>
        </div>
        <!-- .navbar-header -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/about"><span
                        class="glyphicon glyphicon-info-sign"></span>&NonBreakingSpace;紹介</a>
                </li>
                <li><a href="/usage"><span class="glyphicon glyphicon-question-sign"></span>&NonBreakingSpace;使い方</a>
                </li>
                <li><a href="/map"><span class="glyphicon glyphicon-globe"></span>&NonBreakingSpace;ログインせずに利用</a></li>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span>&NonBreakingSpace;ログインして利用</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <c:choose>
                        <c:when test="${empty userAlias}">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span
                                        class="glyphicon glyphicon-user"></span>&nbsp;ユーザー管理 <b class="caret"></b></a>
                        </c:when>
                        <c:when test="${!empty userAlias}">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <span
                                        class="glyphicon glyphicon-user"></span>&nbsp;${userAlias}さん <b
                                    class="caret"></b></a>
                        </c:when>
                    </c:choose>
                    <ul class="dropdown-menu">
                        <li><a href="/register">ユーザー作成</a></li>
                        <li><a href="/update">ユーザー更新</a></li>
                        <li><a href="/u/o">ログアウト</a></li>
                        <li class="divider"></li>
                        <li><a href="/withdraw">ユーザー削除</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- .container-fluid -->
</nav>

