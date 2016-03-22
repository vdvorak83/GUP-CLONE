<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: RAYANT
  Date: 25.01.2016
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Редактирование тендера</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="/resources/images/favicon.ico" />

  <link rel="stylesheet" href="/resources/css/bootstrap.css">
  <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
  <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <link rel="stylesheet" href="/resources/css/main.css">
  <link rel="stylesheet" href="/resources/css/font-awesome.css">
  <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
  <link rel="stylesheet" href="/resources/css/mini.css">
  <link rel="stylesheet" href="/resources/css/confirmDeleteAlert.css">
  <link rel="stylesheet" href="/resources/css/offer-filter-region.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<div class="container2">
  <div class="tenderMake">
    <h1>РЕДАКТИРОВАНИЕ ТЕНДЕРА</h1>
    <form id="tender-make-form" action="#">
      <label for="EnterTheTitle">Введите название</label>
      <input type="text" id="EnterTheTitle" required value="${tender.title}">

      <label for="selectKved">Выберите отрасль</label>
      <select id="selectKved" class="chosen" multiple data-placeholder="Выберите отрасль" style="width: 553px;">
      </select>

      <div class="clearfix"></div>

      <label for="tender-date" class="label-notRequered">Сроки</label>
      <div id="tender-date">
        <input type="text" id="tender-datepicker1" class="datepicker-input" placeholder="Дата начала"> - <input type="text" id="tender-datepicker2" class="datepicker-input" placeholder="Дата окончания">
        <span>Дата начала не должна превышать дату окончания</span>
      </div>
      <%--<p class="datePickPi">Сроки <input type="text" id="datepicker"></p>
      <p class="datePickPi">&nbsp;- <input type="text" id="datepicker2"></p>--%>

      <div class="clearfix"></div>

      <h2>Укажите адрес</h2>
      <div class="location">
        <label for="SelectArea" class="label-notRequered">Выберете область</label>
        <input type="text" id="SelectArea" value="${tender.address.area}">

        <div class="clearfix"></div>

        <label for="SelectCity" class="label-notRequered">Выберете город</label>
        <input type="text" id="SelectCity" value="${tender.address.area}">
      </div>
      <label>Тип</label>
      <div class="tenderRadio">
        <label><input class="input-tenderRadio" type="radio" value="open" name="k" data-type="OPEN"/><span></span></label><p>открытый</p>
        <label><input class="input-tenderRadio" type="radio" value="open" name="k" data-type="CLOSE"/><span></span></label><p>закрытый</p>
      </div>

      <div class="clearfix"></div>

      <div class="description">
        <label for="HideBidders">Скрывать участников тендера</label>
        <label><input type="checkbox" id="HideBidders" value="open" name="k"/><span></span></label>
        <label for="InviteBidders">Пригласить участников тендера</label>
        <input type="text" id="InviteBidders" placeholder="Название">

        <div class="clearfix"></div>

        <label for="ExpectedValue">Ожидаемая стоимость</label>
        <input type="text" id="ExpectedValue" placeholder="456" value="${tender.expectedPrice}">
        <label for="TenderNumber">Номер тендера</label>
        <input type="text" id="TenderNumber" placeholder="XX12345678-90" value="${tender.tenderNumber}">

        <div class="clearfix"></div>

        <label for="Description">Описание</label>
        <textarea name="Description" id="Description"></textarea>
      </div>

      <div class="titleFile" data-title="Добавить изображение"><button type="submit" class="blogCreationSubmit" form="photoForm"></button></div>
      <img id="tender-btn-addDoc" src="/resources/images/clip.png" alt="clip">

      <div class="clearfix"></div>

      <div id="drop_zone">
        <ul id="tender-img-block" class="ul-img-container ul-img-container-green">
          <li class="li-containerIMG li-defaultIMG">
            <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
            <img src="/resources/images/no_photo.jpg" alt="defaultIMG">
          </li>
        </ul>
        <ul id="tender-doc-block" class="ul-img-container ul-img-container-green">
          <li class="li-containerIMG li-defaultIMG">
            <span class="descr"><i class="fa fa-trash-o fa-2x"></i></span>
            <img src="http://www.uzscience.uz/upload/userfiles/images/doc.png" alt="defaultIMG">
            <div style="width: 100%; text-align: center; font-weight: bold"></div>
          </li>
        </ul>
      </div>

      <button id="tender-btn-save" type="submit" form="tender-make-form">Сохранить</button>
      <button id="tender-btn-delete" type="button">Удалить</button>

      <div class="confirm" id="confirmTenderDelete" style="display: none">
        <h1>Подтвердите удаление</h1>
        <p>Объявление будет навсегда удалено</p>
        <button id="cancelTenderDelBtn" autofocus>Отмена</button>
        <button id="confirmTenderDelBtn">Удалить</button>
      </div>

      <form id="photoForm" enctype="multipart/form-data" method="post" style="display:none">
        <input id="photoInput" type="file" style="display: none;" multiple="multiple">
      </form>

    </form>

  </div>
</div>

<sec:authorize access="isAuthenticated()">
  <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Создание тендера</title>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <link href="/resources/css/com.css" rel="stylesheet">
  <link href="/resources/css/bootstrap-datetimepicker.css" rel="stylesheet">
  <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" rel="stylesheet"
        type="text/css"/>
  <link href="/resources/css/mini.css" rel="stylesheet">
</head>
<body>
<h1>Создание тендера</h1>
<input id="areaInp" type="text" name="area" style="display: none">
<input id="cityInp" type="text" name="city" style="display: none">

<div class="row">
  <div class="col-xs-4">
    <p> Название тендера </p>
    <input id="title" type="text" class="form-control" value="${tender.title}">
  </div>

</div>
<br>

<div class="row">
  <div class="col-xs-4">
    <p>Введите номер тендера если он есть</p>
    <input id="tenderNumber" type="text" class="form-control" value="${tender.tenderNumber}">
  </div>
</div>
<br>

<div class="row">
  <div class="col-xs-4">
    <p>Ожидаемая стоимость</p>
    <input id="price" type="number" class="form-control" value="${tender.expectedPrice}">
  </div>
</div>
<div class="row">
  <div class="col-xs-2">
    <div class="radio">
      <label><input type="radio" id="open" name="optradio" checked>Открыйтый</label>
    </div>
  </div>
  <div class="col-xs-2">
    <div class="radio">
      <label><input type="radio" id="close" name="optradio">Закрытый</label>
    </div>
  </div>
</div>
<div class="row" id="members" style="display: none">
  <div class="col-xs-2">
    <input name="memberId" type="text" class="form-control">
  </div>
  <div class="col-xs-2">
    <button class="btn btn-default" id="add">Добавить участника</button>
  </div>
</div>
<br>

<div class="row">
  <div class="col-xs-4">
    <center id="membersList">
    </center>
  </div>
</div>


<!-- city chosen -->
<input id="countryInp" type="text" name="country" style="visibility: hidden;">
<input id="areaInp" type="text" name="area" style="visibility: hidden">
<input id="cityInp" type="text" name="city" style="visibility: hidden">

<div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
  <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
    <div class="input-group">

      <div class="col-xs-6" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a id="chosenRegion" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите область<b class="caret"></b></a>
            <ul class="dropdown-menu multi-column columns-2">
              <div id="regions" class="row">
                <div class="col-sm-6">
                  <ul class="multi-column-dropdown">
                    <li><a role="menuitem" tabindex="-1" href="#"><b>Вся Украина</b></a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Винницкая область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Волынская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Донецкая область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Житомирская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Закарпатская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#" >Ивано‑Франковская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Киевская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Кировоградская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Крым</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Луганская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Львовская область</a></li>
                  </ul>
                </div>
                <div class="col-sm-6">
                  <ul class="multi-column-dropdown">
                    <li><a role="menuitem" tabindex="-1" href="#">Николаевская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Одесская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Полтавская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Ровенская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Сумская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Тернопольская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Харьковская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Херсонская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Хмельницкая область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Черкасская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Черниговская область</a></li>
                    <li><a role="menuitem" tabindex="-1" href="#">Черновицкая область</a>
                    </li>
                  </ul>
                </div>
              </div>
            </ul>
          </li>
        </ul>
      </div>

      <div class="col-xs-6" id="bs-example-navbar-collapse-2" style="visibility: hidden">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a id="chosenCity" href="#" class="dropdown-toggle" data-toggle="dropdown">Выберите город<b class="caret"></b></a>
            <ul class="dropdown-menu multi-column columns-2">
              <div id="cities" class="row">

                <div class="col-sm-6">
                  <ul id="cities1" class="multi-column-dropdown">
                  </ul>
                </div>

                <div class="col-sm-6">
                  <ul id="cities2" class="multi-column-dropdown">
                  </ul>
                </div>
              </div>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<!-- city chosen -->


<div class="row">
  <div class="col-xs-4">
    <center>Выберите дату окончания тендера</center>
    <input type='text' class="form-control" id='datetimepicker4'/>
  </div>
</div>

<br>
<br>

<button id="addImg">Загрузить фото</button>
<form id="uploadProfilePhotoForm" enctype="multipart/form-data"
      method="post" style="display:none">
  <p><input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg" multiple></p>
</form>

<div class="imgBlock">
  <!--uploaded images-->
</div>
<div class="docBlock">
  <!--uploaded images-->
</div>


<div class="row">
  <div class="col-xs-4">
    <div class="panel panel-info">
      <div class="panel-body" id="drop_zone"> Перетяните файлы сюда
      </div>
    </div>
  </div>
</div>

<div class="row">

  <div class="col-xs-4">
    <center>
      <button class="btn btn-success" id="save">Сохранить</button>
    </center>
  </div>
</div>

<br>

<div class="row">
  <div class="col-xs-12">
    <textarea id="textarea"></textarea>
  </div>
</div>


<div id="floating-panel">
  <input id="address" type="textbox" value="">
  <input id="submit" type="button" value="Сохранить">
</div>
<div id="map" style="height: 50%"></div>

<!-- script references -->
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/bootstrap-datepicker.js"></script>

<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>--%>

<script>
  var members = [];
  var placeKey = 'ChIJBUVa4U7P1EAR_kYBF9IxSXY';
  var picMapObj = {};
  var imgsArrResult = {};
  var picArrDel = [];
  var picArrNew = [];
  var imgsArr = {};


  if ('${tender.uploadFilesIds}'.length > 5 ){
    picMapObj = JSON.parse('${tender.uploadFilesIds}'.replace('{', '{"').replace(/=/g, '":"').replace(/,/g, '","').replace('}', '"}').replace(/ /g, ''));
  }

  $.when(loadNace).done(function(response){
    var select = $('#selectKved');
    for(var i = 0; i < response.length; i++) {
      var option = $('<option id="'+ response[i].id +'" value="'+ response[i].id +'">'+ response[i].id + ": " +response[i].name +'</option>');
      select.append(option);
    }
    $(".chosen").chosen();
  });

  $(document).ready(function () {

    if('${tender.hidePropose}') $('#HideBidders').prop( "checked", true );
    if(('${tender.type}') === "CLOSE") {
      $('.input-tenderRadio[data-type="CLOSE"]').prop("checked", true);
    } else {
      $('.input-tenderRadio[data-type="OPEN"]').prop("checked", true);
      $('#InviteBidders').attr("style", "display: none");
      $('label[for="InviteBidders"]').attr("style", "display: none");
    }

    // place photo from received model on the page
    for (var id in picMapObj) {
      imgsArr[id] = picMapObj[id];
      if (picMapObj[id] === "image" || picMapObj[id] === "pic1") {
        appendImg(id);
      } else {
        appendDoc(id, "");
      }
    }

    // Setup the dnd listeners.
    var dropZone = document.getElementById('drop_zone');
    dropZone.addEventListener('dragover', handleDragOver, false);
    dropZone.addEventListener('drop', handleFileSelect, false);

    function handleFileSelect(evt) {
      evt.stopPropagation();
      evt.preventDefault();

      var files = evt.dataTransfer.files; // FileList object.

      // files is a FileList of File objects. List some properties.
      for (var i = 0, f; f = files[i]; i++) {
        var fd = new FormData();
        fd.append('file', f);
        $.ajax({
          type: "POST",
          url: "/api/rest/fileStorage/TENDER/file/upload/",
          data: fd,
          async: false,
          cache: false,
          contentType: false,
          processData: false,

          success: function (data, textStatus, request) {
            var id = data.id;
            if (f.type.substring(0, 5) === 'image') {
              imgsArr[id] = "image";
              appendImg(id);
            } else {
              imgsArr[id] = "doc";
              appendDoc(id, f.name);
            }
          }
        });
      }
    }

    function handleDragOver(evt) {
      evt.stopPropagation();
      evt.preventDefault();
      evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }
  });


  $('.blogCreationSubmit').click(function(){
    $('#photoInput').trigger('click');
  });
  $('#tender-btn-addDoc').click(function(){
    $('#photoInput').trigger('click');
  });

  function appendImg(id) {
    $("#tender-img-block > .li-defaultIMG").css("display", "none");
    var cloneImg = $("#tender-img-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
    cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/TENDER/file/read/id/' + id)
            .attr("id", id)
            .click(onClickSetMainImg);
    cloneImg.find('span')
            .click(deleteImg);

    if(imgsArr[id] === 'pic1') cloneImg.find('img').addClass("mainImg");

    cloneImg.appendTo('#tender-img-block');
  }

  function appendDoc(id, name) {
    $("#tender-doc-block > .li-defaultIMG").css("display", "none");
    var cloneDoc = $("#tender-doc-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
    cloneDoc.find('img')
            .attr("id", id);
    cloneDoc.find('div')
            .text(name);
    cloneDoc.find('span')
            .click(deleteImg);
    cloneDoc.appendTo('#tender-doc-block');
  }

  //--------------------   DATEPICKER ---------------------------------//

  //--------------------   END  DATEPICKER ---------------------------------//


  //----------------------  HTML EDITOR-------------------------------------//
  tinymce.init({
    selector: 'textarea',
    height: 500,
    theme: 'modern',
    plugins: [
      'advlist autolink lists link image charmap print preview hr anchor pagebreak',
      'searchreplace wordcount visualblocks visualchars code fullscreen',
      'insertdatetime media nonbreaking save table contextmenu directionality',
      'emoticons template paste textcolor colorpicker textpattern imagetools'
    ],
    toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    toolbar2: 'print preview media | forecolor backcolor emoticons',
    image_advtab: true,
    templates: [
      {title: 'Test template 1', content: 'Test 1'},
      {title: 'Test template 2', content: 'Test 2'}
    ],
    content_css: [
      '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
      '//www.tinymce.com/css/codepen.min.css'
    ],
    init_instance_callback : function(editor) {
      editor.setContent('${tender.body}');
    }
  });

  //---------------------- END  HTML EDITOR-------------------------------------//


  //--------------------  RADIO CHECK ------------------------------------//

  $('.input-tenderRadio').change(function () {
    var invite = $('#InviteBidders');
    if ($('.input-tenderRadio[data-type="CLOSE"]').prop('checked')) {
      invite.attr("style", "display: ");
      $('label[for="InviteBidders"]').attr("style", "display: ");
    } else {
      invite.attr("style", "display: none");
      $('label[for="InviteBidders"]').attr("style", "display: none");
    }
  });

  //--------------------   END RADIO CHECK ------------------------------------//


  //-------------------- ADD MEMBER ------------------------------------------//

  $('#add').click(function () {
    var email = $('input[name="memberId"]').val();
    $.ajax({
      type: "POST",
      url: "/api/rest/profilesService/profile/email-check",
      data: {"email": email},
      success: function (data) {
        if (data === 'NOT FOUND') {
          alert("Нет пользователя с таким e-mail");
        } else {
          var member = {};
          member.id = data;
          member.name = email;
          var flag = true;
          for (var i = 0; i < members.length; i++) {
            if (members[i].id === data) {
              flag = false;
              break;
            }
          }
          if (flag) {
            members.push(member);
            $('#membersList').append('<p name="' + email + '">' + email + '    <i name="' + email + '"class="icon-remove-sign"></i></p>');
            $('i[name="' + email + '"]').click(function () {
              $('p[name="' + email + '"]').detach();
              for (var i in members) {
                if (members[i].name === email) {
                  members.splice(i);
                }
              }
            });
          } else {
            alert("Собеседник уже добавлен!");
          }
        }
      }
    });
  });

  //-------------------- END ADD MEMBER ------------------------------------------//


  /*//--------------------------- GOOGLE MAP API ---------------------------------------//

  function initMap() {

    var input = document.getElementById('address');

    var options = {
      types: []
    };

    var autocomplete = new google.maps.places.Autocomplete(input, options);

    google.maps.event.addListener(autocomplete, 'place_changed', function () {
      var place = autocomplete.getPlace(); //получаем место
      console.log(place);
      console.log(place.name);  //название места
      console.log(place.id);  //уникальный идентификатор места
    });

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 17,
      center: {lat: 50.4501, lng: 30.523400000000038}
    });

    var geocoder = new google.maps.Geocoder();

    document.getElementById('submit').addEventListener('click', function () {
      geocodeAddress(geocoder, map);
    });
  }

  function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function (results, status) {
      placeKey = results[0].place_id;
      if (status === google.maps.GeocoderStatus.OK) {
        resultsMap.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
          map: resultsMap,
          position: results[0].geometry.location
        });
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
  }

  //--------------------------- END GOOGLE MAP API ---------------------------------------//*/


  // -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//

  $('#photoInput').change(function (event) {
    event.preventDefault();

    var files = event.currentTarget.files;
    for (var i = 0, f; f = files[i]; i++) {
      var fd = new FormData();
      fd.append('file', f);
      $.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/TENDER/file/upload/",
        data: fd,
        async: false,
        cache: false,
        contentType: false,
        processData: false,

        success: function (data, textStatus, request) {
          var id = data.id;
          if (f.type.substring(0, 5) === 'image') {
            imgsArr[id] = "image";
            appendImg(id);
          } else {
            imgsArr[id] = "doc";
            appendDoc(id, f.name);
          }
        }
      });
    }
  });

  function deleteImgFromDB(idImg) {
    $.ajax({
      type: "POST",
      url: "/api/rest/fileStorage/TENDER/file/delete/id/" + idImg,
      success: function (data, textStatus, request) {
      }
    });
  }

  function deleteImg() {
    var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
    var block = $(event.currentTarget).parent().parent();
    $('#' + idImg).parent().remove();

    var numberImg = block.find('img').length;
    if(numberImg < 2) {
      block.find(".li-defaultIMG").css("display", "inline-block");
    }
    picArrDel.push(idImg);
  }

  function onClickSetMainImg(event) {

    var img = $(event.currentTarget);
    var id = img.attr("id");
    var isMain = img.hasClass("mainImg");
    var allImgs = $("#tender-img-block").find("img");
    for (var i =0; i < allImgs.length; i++) {
      var curImg = $(allImgs[i]);
      if (curImg.hasClass("mainImg")) {
        curImg.removeClass("mainImg");
      }
    }
    if(!isMain) img.addClass("mainImg");

    for(var key in imgsArr) {
      if(imgsArr[key] === "pic1") {
        imgsArr[key] = "image";
      }
    }

    if(img.hasClass("mainImg")) {
      imgsArr[id] = "pic1";
    }
  }

  function checkMainImg() {
    var hasMainImg = false;

    for(var key in imgsArrResult) {
      if(imgsArrResult[key] === 'pic1') {
        hasMainImg = true;
        break;
      }
    }

    if(!hasMainImg) {
      for(var key in imgsArrResult) {
        imgsArrResult[key] = 'pic1';
        break;
      }
    }
  }

  // -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//

  //---------------------------- SUBMIT -----------------------------------------------------//
  function checkDateInDatepicker() {
    var dateFrom = $('#tender-datepicker1').datepicker('getDate');
    var dateTo = $('#tender-datepicker2').datepicker('getDate');
    if (dateFrom && dateTo) {
      dateFrom = new Date(dateFrom).getTime() / 1000;
      dateTo = new Date(dateTo).getTime() / 1000;
      if (dateFrom > dateTo) {
        $('#tender-date span').addClass('active-tooltip');
        $(window).scrollTop($('span.active-tooltip').offset().top);
        setTimeout(function () {
          $('#tender-date span').removeClass('active-tooltip');
        }, 6000);
        return false;
      }
    }
    return true;
  }

  $('#tender-make-form').submit(function (event) {
    if(!checkDateInDatepicker()) return false;

    var body = tinymce.activeEditor.getContent();
    if(!body) {
      return false;
    }

    for(var key in imgsArr) {
      if(picArrDel.indexOf(key) === -1) picArrNew.push(key);
    }

    for (var i = 0; i < picArrNew.length; i++) {
      imgsArrResult[picArrNew[i]] = imgsArr[picArrNew[i]];
    }

    for(var i = 0; i < picArrDel.length; i++) {
      deleteImgFromDB(picArrDel[i]);
    }

    checkMainImg();

    var tender = {};
    tender.uploadFilesIds = imgsArrResult;
    tender.title = $('#EnterTheTitle').val();
    tender.body = body;
    tender.tenderNumber = $('#TenderNumber').val();
    tender.begin = $('#tender-datepicker1').datepicker( 'getDate' ).getTime() / 1000;
    tender.end = $('#tender-datepicker2').datepicker( 'getDate' ).getTime() / 1000;
    tender.type = $('.input-tenderRadio:checked').attr("data-type");
    tender.expectedPrice = $('#ExpectedValue').val();
    tender.hidePropose =  $('#HideBidders').prop('checked');
    var members = [];
    if (tender.type === 'CLOSE') {
      tender.members = members;
    }
    /*var naceIds = [];
     var naceOptions = $('#select-type option:selected');
     for(var i = 0; i < naceOptions.length; i++) {
     naceIds.push(naceOptions[i]);
     }
     tender.naceIds = naceIds;*/

    tender.address = {};
    //tender.address.googleMapKey = placeKey;
    tender.address.area = $('#SelectArea').val();
    tender.address.city = $('#SelectCity').val();

    $.ajax({
      type: "POST",
      url: "/api/rest/tenderService/tender/id/${tender.id}/update/",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(tender),
      dataType: "json",
      success: function (response) {
        window.location.href = '/tender/' + response.id;
      }
    });

    event.preventDefault();
    
  });
  //---------------------------- END SUBMIT -------------------------------------------------//
  //------------------ BEGIN DELETE TENDER ------------------------------------//
  $('#tender-btn-delete').on('click', function () {
    $("#confirmTenderDelete").show();
  });

  $('#cancelTenderDelBtn').on('click', function () {
    $("#confirmTenderDelete").hide();
  });

  $('#confirmTenderDelBtn').on('click', function (event) {
    event.preventDefault();

    $.ajax({
      type: "POST",
      url: "/api/rest/tenderService/tender/" + "${tender.id}" + "/delete",
      statusCode: {
        200: function () {
          window.location.href = '/tenders';
        }
      }
    });
  });
  //------------------ END DELETE TENDER ------------------------------------//

</script>
<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww&libraries=places&signed_in=true&callback=initMap"--%>
        <%--async defer></script>--%>
</body>
</html>
