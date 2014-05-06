<%--
  Created by IntelliJ IDEA.
  User: lycaon_h
  Date: 2014/03/06
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>${i18n.map.title}</title>

    <!-- Leafletjs -->
    <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.2/leaflet.css"/>

    <!-- Leafletjs plugins -->
    <link rel="stylesheet" href="${baseUrls.rsrcBaseUrl}/scripts/lib/L.Control.MousePosition.css"/>

    <link rel="stylesheet" href="${baseUrls.rsrcBaseUrl}/scripts/lib/leaflet-draw/leaflet.draw.css"/>

    <link rel="stylesheet" href="${baseUrls.rsrcBaseUrl}/scripts/lib/L.Control.Sidebar.css"/>

    <!-- Bootstrap -->
    <link rel="stylesheet"
          href="${baseUrls.rsrcBaseUrl}/scripts/lib/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${baseUrls.rsrcBaseUrl}/scripts/lib/bootstrap-3.1.1-dist/css/bootstrap-theme.min.css"/>

    <script type="application/javascript">
        // 各ロールをシンボルとして定義する
        ANONYMOUS = parseInt("01", 2);
        ROLE_USER = parseInt("10", 2);

        // global variables
        window.system = {
            <c:choose>
            <c:when test="${empty userAlias}">
            "notification": "${i18n.system.title}:${i18n.system.version}",
            </c:when>
            <c:when test="${!empty userAlias}">
            "notification": "${i18n.system.title}:${i18n.system.version} ${i18n.map.notification.welcome.pre}${userAlias}${i18n.map.notification.welcome.post}",
            </c:when>
            </c:choose>
            "app": null,
            "initBbox": [${bbox[0]}, ${bbox[1]}, ${bbox[2]}, ${bbox[3]}],
            "userRoles": [].concat(${userRoles}),
            "photoBaseUrl": "${baseUrls.photoBaseUrl}",
            "suffixForThumbnail": "_thumbnail.png",
            "debug": true
        };
    </script>

    <!-- user css,js -->
    <link rel="stylesheet" href="${baseUrls.rsrcBaseUrl}/app.css"/>

    <script data-main="${baseUrls.rsrcBaseUrl}/scripts/require-config"
            src="${baseUrls.rsrcBaseUrl}/scripts/require.js"></script>

</head>
<body>
<div id="map">
    <sec:authorize access="hasRole('ROLE_USER')">
        <div class="leaflet-sidebar left">
            <div id="featureFormViewSidebar" class="leaflet-control">
                <div id="featureFormView">
                    <form:form action="/f/c" method="post" enctype="multipart/form-data"
                               commandName="featureFormModel" role="form">
                        <form:hidden path="featureGeomAsGeoJson" id="_feature_geomAsGeoJson"/>
                        <form:hidden path="bbox[0]" id="_feature_bbox0"/>
                        <form:hidden path="bbox[1]" id="_feature_bbox1"/>
                        <form:hidden path="bbox[2]" id="_feature_bbox2"/>
                        <form:hidden path="bbox[3]" id="_feature_bbox3"/>
                        <input type="hidden" name="_feature_featureId"/>

                        <div class="form-group">
                            <label for="feature_featureName">名称</label>
                            <form:input path="featureName" class="form-control" id="feature_featureName"
                                        placeholder="名称"/>
                        </div>

                        <div class="form-group">
                            <label for="feature_featureDescription">説明</label>
                            <form:textarea path="featureDescription" rows="12"
                                           id="feature_featureDescription" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="feature_tags">タグ</label>
                            <form:input path="featureTags"
                                        id="feature_tags" class="form-control" placeholder="タグ"/>
                        </div>

                        <div class="form-group">
                            <label for="feature_imageFile">画像添付</label>
                            <input type="file" id="feature_imageFile" name="featureImageFile">

                            <span class="help-block">10メガバイトまでの各種画像ファイル</span>
                        </div>

                        <button type="submit"
                                class="btn btn-default btn-sm"
                                style="width:100%;">投稿する
                        </button>
                        <span class="help-block">※ページのリロードが発生します</span>
                    </form:form>
                </div>
            </div>
            <a class="close">x</a>
        </div>
    </sec:authorize>
    <div class="leaflet-sidebar right">
        <div id="remarkViewSidebar" class="leaflet-control">
            <div id="remarkView">
                <div id="remarksHolder">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12" id="feature-remark">
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12" id="remarks" style="margin-bottom:10px;">
                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="container-fluid">--%>
                <%--<div class="row">--%>
                <%--<div class="col-md-12">--%>
                <%--<ul class="pager">--%>
                <%--<li><a href="#">Prev</a></li>--%>
                <%--<li><a href="#">Next</a></li>--%>
                <%--</ul>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="container-fluid">
                    <form:form action="/r/c"
                               method="post"
                               commandName="remarkFormModel"
                               enctype="multipart/form-data"
                               role="form">
                        <form:hidden path="featureId" id="_remark_featureId"/>
                        <form:hidden path="bbox[0]" id="_remark_bbox0"/>
                        <form:hidden path="bbox[1]" id="_remark_bbox1"/>
                        <form:hidden path="bbox[2]" id="_remark_bbox2"/>
                        <form:hidden path="bbox[3]" id="_remark_bbox3"/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <form:textarea cssStyle="resize:vertical;"
                                                   path="remarkMessage" class="form-control" rows="2"
                                                   id="remark_message"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label for="remark_imageFile">画像添付</label>
                                    <input type="file" id="remark_imageFile" name="remarkImageFile">

                                    <span class="help-block">10メガバイトまでの各種画像ファイル</span>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <button style="float:right;width:100%;"
                                            type="submit" class="btn btn-default btn-sm">
                                        コメントする
                                    </button>
                                    <span class="help-block">※ページのリロードが発生します</span>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <a class="close">x</a>
    </div>
</div>
</body>
</html>
