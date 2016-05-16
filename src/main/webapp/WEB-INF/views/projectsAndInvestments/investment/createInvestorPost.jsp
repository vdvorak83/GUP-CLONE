<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/mini.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>
<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<!--PAGE CONTENT START-->

<div id="investor-post-create" class="container2">
    <div class="contentContainer editor" id="tab-container-pVSi">
        <div class="title">Создание инвестиции</div>
        <div class="investor" id="tabs1-investment">
            <div class="field required kvd">
                <label for="categoriesOfIndustry" class="editorLabel">Выберите отрасль</label>
                <select  id="categoriesOfIndustry" class="chosen" multiple="true" data-placeholder="Выберите отрасли" style="width: 550px;">
                    <option value="engineering">Инженерно-строительные услуги</option>
                    <option value="repair">Ремонт и обслуживание техники и оборудования</option>
                    <option value="rent">Услуги проката и аренды</option>
                    <option value="vehicles">Ремонт и техническое обслуживание автотранспорта</option>
                    <option value="logistics">Логистические и складские услуги</option>
                    <option value="health">Услуги в сфере медицины, здоровья и красоты</option>
                    <option value="recreation">Услуги досуга, отдыха, культуры</option>
                    <option value="education">Услуги в сфере образования, тренинги</option>
                </select>
            </div>
            <div class="sum">
                <div class="field required">
                    <label for="sum1" class="editorLabel">Минимальная сумма</label>
                    <input id="sum1" type="number" name='sum1' class="editorInput">
                    <span class="currency">₴</span>
                </div>
                <div class="field required">
                    <label for="sum2" class="editorLabel">Максимальная сумма</label>
                    <input id="sum2" type="number" name='sum2' class="editorInput">
                    <span class="currency">₴</span>
                </div>
            </div>
            <div class="field description required">
                <label for="description" class="editorLabel">Описание</label>
                <textarea id="description" name='description' class="editorInput" placeholder="От 50 до 5000 символов"></textarea>
            </div>
            <div class="field">
                <button id="createInvestorPost" class="info-submit">Сохранить</button>
            </div>
        </div>
    </div>
</div>

<div id="gup-validator-popup" class="gup-popup-overlay">
    <div class="gup-popup">
        <h2>Ошибка создания инвестиции</h2>
        <a class="popup-close" href="#">&times;</a>
        <div class="popup-content">

        </div>
    </div>
</div>

<!--PAGE CONTENT END-->

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

<script src="/resources/js/investorPostCreate.js"></script>
</body>
</html>


