<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 21.10.2015
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Панель управления</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">

    <!-- DataTables Select CSS -->
    <link href="https://cdn.datatables.net/select/1.0.1/css/select.dataTables.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/buttons/1.0.3/css/buttons.dataTables.min.css" rel="stylesheet">


    <!-- Timeline CSS -->
    <link href="/resources/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <link href="/resources/css/typeaheadjs.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <!-- Header-bar -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/admin" />">Финансовая панель управления</a>
        </div>
        <!-- Header-bar -->

        <!-- Header dropdown menu -->
        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="<c:url value="/profileEditor" />"><i class="fa fa-user fa-fw"></i> Профиль</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="<c:url value="/logout" />"><i class="fa fa-sign-out fa-fw"></i> Выход</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Header dropdown menu -->

        <!-- Left sidebar menu -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Поиск...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </li>

                    <li>
                        <a href="<c:url value="/admin-user" />"><i class="fa fa-dashboard fa-fw"></i>Пользователи</a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin-tenders" />"><i class="fa fa-share fa-fw"></i>Тендеры</a>
                    </li>

                    <li>
                        <a href="<c:url value="/admin-projects" />"><i class="fa fa-edit fa-fw"></i>Проекты</a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin-admins" />"><i class="fa fa-table fa-fw"></i>Админы</a>
                    </li>
                    <li>
                        <a href="<c:url value="/admin-offers" />"><i class="fa fa-credit-card fa-fw"></i>Объявления</a>
                    </li>
                    <%--<li>--%>
                    <%--<a href="<c:url value="/accountant/internal" />"><i class="fa fa-share fa-fw"></i> Внутренние транзакции</a>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
        <!-- Left sidebar menu -->
    </nav>
    <!-- Navigation -->

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Информация</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>


        <div class="row">
            <div class="col-lg-12">

                <div class="col-lg-8">
                    <div class="span9">

                        <div id="content">
                            <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                <li><a href="#adminsTable" data-toggle="tab">Админы</a></li>
                                <li><a href="#moderatorsTable" data-toggle="tab">Модераторы</a></li>
                                <li>
                                    <button type="button" class="btn btn-default btn-md" data-toggle="modal"
                                            data-target="#charge" style="margin-left: 25px">
                                        Создать админа
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="btn btn-default btn-md" data-toggle="modal"
                                            data-target="#invest" style="margin-left: 25px">
                                        Выбрать из пользователей портала
                                    </button>
                                </li>
                            </ul>

                            <!-- Modal -->
                            <div class="modal fade" id="charge" tabindex="-1"
                                 role="dialog" aria-labelledby="myModalLabel"
                                 style="z-index: 1051">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close"
                                                    data-dismiss="modal"
                                                    aria-label="Close"><span
                                                    aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="mModalLabel">
                                                Создать</h4>
                                        </div>

                                            <div class="col-xs-4">
                                                <br>
                                                <input id="newLogin" type="text"
                                                       class="form-control"
                                                       placeholder="Логин">
                                                <br>
                                                <input id="newPassword" type="text"
                                                       class="form-control"
                                                       placeholder="Пароль">
                                            </div>

                                            <div class="col-xs-4">
                                                <br>
                                                <div class="checkbox">
                                                    <label><input id="adminCheck" type="checkbox"
                                                                  value="ROLE_ADMIN">ADMIN</label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input id="supportCheck" type="checkbox"
                                                                  value="ROLE_SUPPORT">SUPPORT</label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input id="moderatorCheck" type="checkbox"
                                                                  value="ROLE_MODERATOR">MODERATOR</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <br>
                                                <div class="checkbox">
                                                    <label><input id="anonymousCheck" type="checkbox"
                                                                  value="ROLE_ANONYMOUS">ANONYMOUS</label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input id="userCheck" type="checkbox"
                                                                  value="ROLE_USER">USER</label>
                                                </div>
                                            </div>

                                        <div class="modal-footer">

                                            <button id="create" type="submit"
                                                    class="btn btn-primary">Создать
                                            </button>
                                            <button type="button"
                                                    class="btn btn-default"
                                                    data-dismiss="modal">Отмена
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="invest" tabindex="-1"
                                 role="dialog" aria-labelledby="myModalLabel"
                                 style="z-index: 1051">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close"
                                                    data-dismiss="modal"
                                                    aria-label="Close"><span
                                                    aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title">Редактировать</h4>
                                        </div>

                                            <div class="col-xs-4">
                                                <br>
                                                <div id="scrollable-dropdown-menu">
                                                    <input id="typeahead" class="typeahead tt-input form-control" type="text" placeholder="users" autocomplete="off" spellcheck="false" dir="auto" style="position: relative; vertical-align: top; background-color: transparent;">
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <br>
                                                <div class="checkbox">
                                                    <label><input id="adminCheck2" type="checkbox"
                                                                  value="ROLE_ADMIN">ADMIN</label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input id="supportCheck2" type="checkbox"
                                                                  value="ROLE_SUPPORT">SUPPORT</label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input id="moderatorCheck2" type="checkbox"
                                                                  value="ROLE_MODERATOR">MODERATOR</label>
                                                </div>
                                            </div>
                                            <div class="col-xs-4">
                                                <br>
                                                <div class="checkbox">
                                                    <label><input id="anonymousCheck2" type="checkbox"
                                                                  value="ROLE_ANONYMOUS">ANONYMOUS</label>
                                                </div>
                                                <div class="checkbox">
                                                    <label><input id="userCheck2" type="checkbox"
                                                                  value="ROLE_USER">USER</label>
                                                </div>
                                            </div>

                                        <div class="modal-footer">
                                            <button type="submit"
                                                    class="btn btn-primary">Создать
                                            </button>
                                            <button type="button"
                                                    class="btn btn-default"
                                                    data-dismiss="modal">Отмена
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>



                            <div class="tab-content">
                                <div class="tab-pane active" id="adminsTable">

                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="admins"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Id</th>
                                                                <th>Фото</th>
                                                                <th>Логин</th>
                                                                <th>Дата регистрации</th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="tab-pane" id="moderatorsTable">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="panel panel-default">
                                                <!-- /.panel-heading -->
                                                <div class="panel-body">
                                                    <div class="dataTable_wrapper">

                                                        <table id="moderators"
                                                               class="table table-striped table-bordered table-hover"
                                                               cellspacing="0" width="100%">
                                                            <thead>
                                                            <tr>
                                                                <th>Id</th>
                                                                <th>Фото</th>
                                                                <th>Логин</th>
                                                                <th>Дата регистрации</th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<!-- /.panel -->

<!-- jQuery -->
<script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- script references -->
<script src="/resources/js/bootstrap-modalmanager.js"></script>
<script src="/resources/js/bootstrap-modal.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
<script src="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<script src="https://cdn.datatables.net/select/1.0.1/js/dataTables.select.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.0.3/js/dataTables.buttons.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/resources/dist/js/sb-admin-2.js"></script>

<!-- Moment library for humanlike date format -->
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/typeahead.js"></script>

<script>
    var idCorrect = [];
    var usernames = [];

    $(document).ready(function () {
        var data;
        var filterOptions = new Object();
        filterOptions.skip = 0;
        filterOptions.limit = 1000000;
//        filterOptions.userRoles = ['ROLE_ADMIN', 'ROLE_MODERATOR'];

        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').focus()
        });

        $('#deleteModal').on('shown.bs.modal', function () {
            $('#myInput').focus()
        });

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/profilesService/profile/read/all",
            data: JSON.stringify(filterOptions),
            success: function (response) {
                data = response.entities;
                for (var k = 0; k < data.length; k++){
                    usernames.push(data[k].email);
                }
                for (var i = 0; i < data.length; i++) {
                    if (data[i].contact !== null) {
                        if (data[i].contact.pic.length > 2) {
                            data[i].contact.pic = '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + data[i].contact.pic + '" width="100" height="100">';
                        }
                        else {
                            data[i].contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                        }
                    }
                    else {
                        data[i].contact = new Object();
                        data[i].contact.pic = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';
                    }
                }

                var admins = [];
                var moderators = [];
                for (var m = 0; m < data.length; m++) {
                    for (var n = 0; n < data[m].userRoles.length; n++) {
                        if (data[m].userRoles[n] === 'ROLE_ADMIN') {
                            admins.push(data[m]);
                        }
                        if (data[m].userRoles[n] === 'ROLE_MODERATOR') {
                            moderators.push(data[m]);
                        }
                    }
                }

                var tableAdmins = $('#admins').DataTable({
                    select: {
                        style: 'single'
                    },
                    data: admins,
                    "columns": [
                        {"data": "contact.pic"},
                        {"data": "username"},
                        {"data": "email"},
                        {"data": "createdDate"}
                    ],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
                    }
                });

                tableAdmins
                        .on('select', function (e, dt, type, indexes) {
                            var rowData = table.rows(indexes).data().toArray();
                            $("input[name='transactionId']").attr("value", rowData[0].id);
                            $('#inp').removeAttr("readonly");
                            $('#cancelBtn').attr("class", "btn btn-danger");
                        })
                        .on('deselect', function (e, dt, type, indexes) {
                            $("input[name='transactionId']").attr("value", "");
                            $('#inp').attr("readonly", "readonly");
                            $('#cancelBtn').attr("class", "btn btn-danger disabled");
                        });

                var tableModerators = $('#moderators').DataTable({
                    select: {
                        style: 'single'
                    },
                    data: moderators,
                    "columns": [
                        {"data": "contact.pic"},
                        {"data": "username"},
                        {"data": "email"},
                        {"data": "createdDate"}
                    ],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
                    }
                });

                tableModerators
                        .on('select', function (e, dt, type, indexes) {
                            var rowData = table.rows(indexes).data().toArray();
                            $("input[name='transactionId']").attr("value", rowData[0].id);
                            $('#inp').removeAttr("readonly");
                            $('#cancelBtn').attr("class", "btn btn-danger");
                        })
                        .on('deselect', function (e, dt, type, indexes) {
                            $("input[name='transactionId']").attr("value", "");
                            $('#inp').attr("readonly", "readonly");
                            $('#cancelBtn').attr("class", "btn btn-danger disabled");
                        });

                var logins = new Bloodhound({
                    datumTokenizer: Bloodhound.tokenizers.whitespace,
                    queryTokenizer: Bloodhound.tokenizers.whitespace,
                    // `states` is an array of state names defined in "The Basics"
                    local: usernames
                });

                $('#scrollable-dropdown-menu .typeahead').typeahead(null, {
                    name: 'logins',
                    limit: 10,
                    source: logins
                });

                $('#typeahead').change(function(){
                    alert("chenge");
                })

            }

        });


    });

    $('#create').click(function(){
        var user = {};
        var login;
        var password;
        var roles = [];
        login =$('#newLogin').val();
        password =$('#newPassword').val();
        if($('#adminCheck').prop("checked")){
            roles.push('ROLE_ADMIN');
        }
        if($('#supportCheck').prop("checked")){
            roles.push('ROLE_SUPPORT');
        }
        if($('#moderatorCheck').prop("checked")){
            roles.push('ROLE_MODERATOR');
        }
        if($('#userCheck').prop("checked")){
            roles.push('ROLE_USER');
        }
        if($('#anonymousCheck').prop("checked")){
            roles.push('ROLE_ANONYMOUS');
        }

        user.username = login;
        user.email = login;
        user.password = password;
        user.userRoles = roles;

        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(user),
            success: function (response) {
                window.location.href = '/admin-admins';
            }
        });
    });



</script>

</body>

</html>

