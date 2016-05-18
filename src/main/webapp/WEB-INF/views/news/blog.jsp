<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${blog.title} | Портал GUP</title>
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>


    <link href="/resources/css/bootstrap.css" rel="stylesheet">

    <link href="/resources/css/com.css" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="/resources/css/simplePagination.css"/>
    <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link href="/resources/css/main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

</head>
<body>
<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>
<!--END 2nd section -->

<div class="container2">
    <div class="createBlog">

        <p class="blogName">${blog.title}</p>

        <div class="clearfix"></div>

        <div class="boxRight">
            <div class="creatrBlogText">
                <p class="createBlogDescriptionText">
                    ${blog.description}
                </p>
            </div>


        </div>

        <div class="boxLeft">
            <div class="createBlogLogo"><c:choose>
                <c:when test="${not empty blog.imageId}">
                    <img id="imgPreview" src="/api/rest/fileStorage/NEWS/file/read/id/${blog.imageId}" width="200"
                         height="200">
                </c:when>
                <c:otherwise>
                    <img id="imgPreview" src="/resources/images/no_photo.jpg">
                </c:otherwise>
            </c:choose>

            </div>

            <div class="createBlogSocialIcon">
                <c:forEach var="socLink" items="${blog.socLinks.entrySet()}">
                    <c:choose>
                        <c:when test="${socLink.getKey() == 'FACEBOOK'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-facebook fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'VKONTAKTE'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-vk fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'ODNOKLASSNIKI'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-odnoklassniki fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'LINKEDIN'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-linkedin fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'GOOGLEPLUS'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-google-plus fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'YOUTUBE'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-youtube fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'TWITTER'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-twitter fa-border"></i></a>
                        </c:when>
                        <c:when test="${socLink.getKey() == 'SKYPE'}">
                            <a href="${socLink.getValue()}"><i class="fa fa-skype fa-border"></i></a>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>

            <p class="authorblog">Автор: <a href="/profile?id=${blog.authorId}">${username}</a></p>

            <c:if test="${check}">
                <a href="/blog-post/create/${blog.id}">
                    <button type="button" class="SubmitArticles">Добавить статью</button>
                </a>
                <a href="/blog/${blog.seoUrl}/edit">
                    <button type="button" class="SubmitArticles">Редактировать блог</button>
                </a>
            </c:if>
            <a href="/blog-post/view-all/blogId/${blog.id}">
                <button type="button" class="SubmitArticles">Все новости блога</button>
            </a>
        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<script>
    var flag = '${flag}';
</script>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src="/resources/js/common.js"></script>
</body>
</html>
