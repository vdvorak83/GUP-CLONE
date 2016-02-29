<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 28.01.2016
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>Тендер | GUP</title>

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
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


<!--PAGE CONTENT START-->

<div class="container2">
    <!-- Add "vip" class for "vip" users :3 -->
    <div class="contentContainer" style="margin-top: 100px;">
        <div class="tenderContent">
            <div class="topSection">
                <div class="statInfo">
                    <div class="publishDate">Опубликовано: 22. 10. 16</div>
                    <span class="number visible">№953745195</span>

                    <div class="sum">00 000 000$</div>
                </div>
                <div class="clearfix"></div>
                <div class="tenderButtons">
                    <div class="participate">
                        <div class="clock">
                            <div class="time">15</div>
                        </div>
                        <button type="button" class="abutton blue">Участвовать</button>
                    </div>
                    <button type="button" class="abutton leaveProposal">Оставить предложение</button>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="title">Название тендера</div>
            <img src="/resources/css/images/sample/tender1.png" alt="" class="mainPhoto">

            <p class="text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc malesuada molestie euismod. In vulputate
                mi dictum orci vulputate auctor. Fusce sit amet orci suscipit neque lacinia pulvinar nec in est. Proin
                neque ante, elementum at maximus a, luctus nec diam. Mauris convallis tellus nec sapien accumsan, quis
                posuere odio congue. Etiam eu tincidunt arcu, pellentesque euismod odio. Class aptent taciti sociosqu ad
                litora torquent per conubia nostra, per inceptos himenaeos. Donec gravida dolor ac laoreet ultrices.
                Praesent ut turpis sit amet ante interdum vulputate ultricies ac neque. Maecenas placerat tortor magna,
                quis consequat turpis feugiat non. Nunc vel ultricies metus. Morbi facilisis maximus augue, sed sagittis
                ligula tincidunt at. Mauris sed vestibulum sapien. Fusce ut iaculis sem. Nulla vitae lacus enim. Etiam
                malesuada ex at velit faucibus ultricies. Nunc quis tincidunt urna. Sed a erat faucibus, sodales velit
                at, lacinia massa. Aliquam porta at libero et gravida. Vivamus viverra ut odio blandit posuere. Nam
                sagittis justo at elit ornare dignissim. Maecenas eros tortor, sollicitudin non aliquet id, blandit id
                velit. Aenean scelerisque feugiat lacus eget mattis. Etiam convallis lacinia mauris at aliquam. Ut in
                nibh nisi. Nam tincidunt sollicitudin enim nec aliquet. Cras consectetur vitae erat eget porta. Aenean
                efficitur eget dolor nec suscipit. Curabitur sit amet tellus nec lorem maximus lobortis euismod quis
                libero. Donec molestie nibh ac consequat auctor. Aliquam sagittis blandit mauris. Phasellus eu ornare
                nisi. Aenean sagittis vestibulum pellentesque. Nam venenatis condimentum sapien, sed ornare neque porta
                fringilla. Vestibulum quis eleifend lacus, in molestie nulla. Suspendisse potenti. Sed rutrum tortor sit
                amet tortor gravida sollicitudin. Phasellus quis condimentum lectus. Quisque sapien eros, tristique et
                venenatis ultrices, volutpat vitae libero. In velit enim, tristique quis est feugiat, interdum laoreet
                augue. Sed maximus sollicitudin neque non sodales. Vestibulum tempus mauris neque, eu pretium sa
            </p>

            <div class="bottomSection">
                lololololololo i'm bottom section)))))azzaz
            </div>

        </div>
    </div>

    <div class="downComments"><p>КОММЕНТАРИИ</p></div>

    <div class="colNewsComments">
        <div class="newsComments">
            <div class="clearfix"></div>
            <p class="newsCommentsHeader">ПРЕДЛОЖЕНИЯ</p>

            <form action="#" role="form" id="newsCommentsForm">
                <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий"
                          maxlength="2000" required></textarea>
                <button type="submit" class="newsFormSubmit">Отправить</button>
            </form>
            <p id="chars">2000 символов осталось</p>
        </div>
    </div>
    <div class="colComments">
        <div class="comments">
            <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
            <a class="NameUser" href="#">Вася Петров</a>

            <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui quisquam, voluptate at
                magni neque. Ab illum hic asperiores voluptate voluptatem. Optio alias, numquam sint delectus quod
                recusandae dolores tempora. Aliquam!</p>
        </div>
        <div class="comments">
            <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
            <a class="NameUser" href="#">Вася Петров</a>

            <p class="commentUser">Интересно было узнать, история повторяется циклично!</p>
        </div>
    </div>
    <div class="clearfix"></div>
</div>

<!--PAGE CONTENT END-->


<%--<section>--%>
<%--<div class="tender-wrap">--%>
<%--<div class="tender-tabs-wrap">--%>
<%--<div class="tabs">--%>

<%--<div>--%>

<%--<!-- Repeated section with tender -->--%>

<%--<div class="tender-tabs-items-wrap">--%>

<%--<div class="tender-item-wrapper">--%>
<%--<div class="tender-item-leftside">--%>
<%--&lt;%&ndash;<div class="tender-pic-wrap">&ndash;%&gt;--%>
<%--&lt;%&ndash;<img src="#">&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--<div class="tender-subpic-stuff">--%>
<%--<p style="margin-top: 0px; display: inline-block;">Предложений:<span--%>
<%--class="tender-proposal-count"></span></p>--%>

<%--<p style="margin-top: 0px; display: inline-block; float: right;">--%>
<%--Просмотров:<span--%>
<%--class="tender-veiws"></span></p>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="tender-item-rightside">--%>
<%--<div class="tender-item-header-wrap">--%>
<%--<div class="tender-name">--%>
<%--<p></p>--%>
<%--</div>--%>
<%--<div class="tender-item-info">--%>
<%--<p class="tender-publish-date">Опубликовано:<span--%>
<%--class="date-create"></span></p>--%>

<%--<p class="tender-number">№<span></span></p>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="tender-item-text">--%>
<%--<p></p>--%>
<%--</div>--%>
<%--<div class="tender-item-subtext-stuff">--%>
<%--<div class="tender-time-remain">--%>
<%--<img src="/resources/img/alarm.png">--%>

<%--<p class="tender-time date-create"></p>--%>

<%--<div class="tender-cost-wrap">--%>
<%--<p><span class="tender-cost"></span>$</p>--%>
<%--<button class="tender-apply-for">Участвовать</button>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>
<%--<div class="imgGal"></div>--%>

<%--<div class="map">--%>
<%--</div>--%>

<%--<sec:authorize access="isAuthenticated()">--%>
<%--<div id="no-propose" style="display: none">Нет предложений. Будье первыми!--%>
<%--</div>--%>

<%--<div id="start">--%>
<%--<div class="proposes-wraper" style="outline: 2px solid #000;">--%>
<%--<div class="propose-author">Вася</div>--%>
<%--<img class="member-pic" src="#" width="50" height="50">--%>

<%--<div class="propose-date"> 1 февраля</div>--%>
<%--<button class="chooseWinner">Выбрать победителем</button>--%>
<%--<div class="poropse-text">Азазаз</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="offer-wraper" style="height: 200px; background-color: #006dcc">--%>
<%--<div class="offer-input-group">--%>
<%--<textarea id="tenderPropose"></textarea>--%>

<%--<div id="textLength"></div>--%>
<%--</div>--%>

<%--<input id="visionSelect" type="checkbox"><label for="visionSelect">Скрыть--%>
<%--предложение от других участников</label>--%>

<%--<button id="makePropose" disabled>Отправить</button>--%>
<%--</div>--%>
<%--</sec:authorize>--%>

<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--&lt;%&ndash;<!-- End of repeated section with tender -->&ndash;%&gt;--%>

<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</section>--%>


<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()"/>
<c:choose>
    <c:when test="${loggedIn}">
        <script src="/resources/js/autorizedHeader.js"></script>
    </c:when>
    <c:otherwise>
        <script src="/resources/js/anonymHeader.js"></script>
    </c:otherwise>
</c:choose>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script>

    var proposes;

    var firstBlock = $('#start').html();


    // ----------------------- Begin Tender propose text length counter ------------------------------
    $("#tenderPropose").on('keyup', function (event) {
        var button = $('#makePropose');
        var counter = $("#textLength");

        var currentString = $("#tenderPropose").val();
        counter.html(currentString.length);
        if (currentString.length <= 50) {  /*or whatever your number is*/
            button.attr("disabled", true);
            counter.css("color", "red");
        } else {
            if (currentString.length > 500) {
                button.attr("disabled", true);
                counter.css("color", "red");
            } else {
                button.attr("disabled", false);
                counter.css("color", "green");
            }
        }
    });
    // ----------------------- End Tender propose text length counter ------------------------------


    var tenderId = '${id}';

    function sliderImg(arr) {
        var url = '';
        var imgId = '';
        for (var i in arr) {
            if (arr[i] === 'image') {
                imgId = i;
                url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;

                var element = '<img src="' + url + '" width="200" height="200"/>';
                $('.imgGal').append(element)
            }
        }
    }

    function localDateTime(long) {
        long = new Date(parseInt(long));
        long = moment(long).locale("ru").format('LLL');
        return long;
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/tenderService/tender/read/id/" + tenderId,
        success: function (response) {
            var data = response;

            sliderImg(data.uploadFilesIds);
            $(".tender-item-text p").last().html(data.body);
            $(".tender-number").last().text(data.tenderNumber);
            $(".tender-publish-date span").last().text(localDateTime(data.begin));
            $(".tender-veiws").last().text(data.visited);
            $(".tender-proposal-count").last().text(data.proposeNumber);
            $(".tender-name p").last().text(data.title);
            $(".date-create").last().text(localDateTime(data.end));


            var map = '<iframe width="500" height="400" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=place_id:' + data.address.googleMapKey + '&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww" allowfullscreen></iframe>';
            $('.map').append(map);


            if (data.proposes === undefined) {
                if (data.proposes.length < 1) {
                    $('#no-propose').attr('style', 'display: ');
                    $('#start').attr('style', 'display: none')
                }
            }


            for (var i in data.proposes) {
                $(".propose-author").last().text(data.proposes[i].authorId);
                $(".propose-date").last().text(localDateTime(data.proposes[i].time));
                $(".poropse-text").last().text(data.proposes[i].body);
                $(".chooseWinner").last().attr('id', data.proposes[i].authorId);
                $('#start').append(firstBlock);
            }

            $(".chooseWinner").on('click', function () {
                alert("Азазаз");

                var Tender = {};
                Tender.winnerId = $(this).attr('id');
                Tender.id = tenderId;

                $.ajax({
                    type: "POST",
                    url: "/api/rest/tenderService/tender/chooseWinner",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify(Tender),
                    statusCode: {
                        200: function () {
                            alert("Победитель выбран!")
                        }
                    }
                });

            });


            $('.proposes-wraper').last().attr('style', 'display: none;');

            for (var j in data.members) {
                $('.propose-author').each(function (index) {
                    if ($(this).text() === data.members[j].id) {
                        $(this).text(data.members[j].name);
                        $(this).next('.member-pic').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data.members[j].userPic)
                    }
                });
            }
        },
        statusCode: {
            403: function () {
                $('.tender-tabs-items-wrap').detach();
                $('.tender-wrap').text("Войдите в систему, чтобы просмотреть информацию о тендере.");
            }
        }
    });


    $(document).ready(function () {
        $('.slider1').bxSlider({
            slideWidth: 200,
            minSlides: 2,
            maxSlides: 3,
            slideMargin: 5
        });
    });

    // ----------------- BEGIN Propose sent -------------------------------------------------
    $('#makePropose').on('click', function () {
        var Propose = {};
        Propose.body = $('#tenderPropose').val();
        Propose.hidden = $('#visionSelect').prop('checked');


        $.ajax({
            type: "POST",
            url: "/api/rest/tenderService/tender/id/" + tenderId + "/propose/create/",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(Propose),
            statusCode: {
                201: function () {
                    window.location.href = '/tender/' + tenderId;
                }
            }
        });
    });

    // ----------------- END Propose sent -------------------------------------------------
</script>
</body>
</html>