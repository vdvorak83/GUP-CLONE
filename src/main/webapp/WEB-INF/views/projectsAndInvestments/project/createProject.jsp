<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Создание Проекта</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/notification.css">
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<jsp:include page="/WEB-INF/templates/authentification.jsp"/>

<div>
    <h2 align="center">Создание Проекта</h2>
</div>
<div>
    <label for="projectType"><b>Тип: </b></label>
    <select name="projectType" id="projectType" required>
        <option value="RENOVATION">Реструктуризация</option>
        <option value="PROTOTYPE">Прототип</option>
        <option value="PROJECT_ON_PAPER">Проект на бумаге</option>
        <option value="KNOW_HOW">Ноу хау</option>
    </select>
</div>

<div>
    <label for="projectName"><b>Название: </b></label>
    <input id="projectName" type="text" name="projectName" min="4" maxlength="140"
           placeholder="Минимум 4 символа" required>
</div>
<!--
<div>
    <label for="projectDescription"><b>Описание: </b></label>
            <textarea id="projectDescription"
                      placeholder="Минимум 50 символов, максимум 5000ю" required></textarea>
</div>
-->
<div class="row">
    <div class="col-xs-12">
        <label for="textarea"><b>Описание: </b></label>
        <textarea id="textarea"></textarea>
    </div>
</div>

<div>
    <label for="amountRequested"><b>Нужная сумма: </b></label>
    <input id="amountRequested" type="number" min="1"
           placeholder="Минимум 1" required>
</div>

<div>
    <label for="categoriesOfIndustry"><b>Категории индустрии: </b></label>
    <select multiple="multiple" size="4" name="categoriesOfIndustry" id="categoriesOfIndustry" required>
        <option value="cat1">Категория 1</option>
        <option value="cat2">Категория 2</option>
        <option value="cat3">Категория 3</option>
        <option value="cat4">Категория 4</option>
    </select>
</div>

<div id="drop_zone">
    <button id="addImg">Загрузить фото</button>
    <form id="uploadProfilePhotoForm" enctype="multipart/form-data"
          method="post" style="display:none">
        <p><input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg" multiple></p>
    </form>

    <div class="imgBlock">
        <!--uploaded images-->
    </div>
    Перетяните файлы сюда
</div>

<button id="createProject">Создать</button>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
<script src="/resources/js/common.js"></script>
<sec:authorize access="isAuthenticated()">
    <script src="/resources/js/autorizedHeader.js"></script>
</sec:authorize>
<script>

    var imagesIds = {};
    var projectType = [];
    var project = {};

    $(document).ready(function () {
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
                    url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
                    data: fd,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,

                    success: function (data, textStatus, request) {
                        var id = data.id;
                        var isImage = f.type.substring(0, 5) === 'image';
                        if (isImage) {
                            imagesIds[id] = "image";
                            $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none"' +
                                    '<li><strong>' + f.name + '</strong></li>' +
                                    ' <li style="background-color: white">' +
                                    '<a rel="example_group"> ' +
                                    '<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                                    '</a> <div onclick=\"deleteImgFromDB(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
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

        $('#addImg').click(function(){
            $('#uploadProfilePhotoInput').trigger('click');
        });

    });

    $('#uploadProfilePhotoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;
        for (var i = 0, f; f = files[i]; i++) {
            var fd = new FormData();
            fd.append('file', f);
            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
                data: fd,
                async: false,
                cache: false,
                contentType: false,
                processData: false,

                success: function (data, textStatus, request) {
                    var id = data.id;
                    var isImage = f.type.substring(0, 5) === 'image';
                    if (isImage) {
                        imagesIds[id] = "image";
                        $('.imgBlock').append('<ul id="' + data.id + '" style="display: inline-table; list-style-type: none">' +
                                '<li><strong>' + f.name + '</strong></li>' +
                                ' <li style="background-color: white">' +
                                '<a rel="example_group"> ' +
                                '<img alt="" src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + data.id + '"' + 'width="150" height="150"> ' +
                                '</a> <div onclick=\"deleteImgFromDB(' + '\'' + id + '\'' + ')">Удалить</div> </li> </ul>');
                    }
                }
            });
        }
        event.currentTarget.form.reset();
    });

    function deleteImgFromDB(picId) {
        delete imagesIds[picId];
        $.ajax({
            url: '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/delete/id/' + picId,
            method: 'POST',
            success: function (response) {
                $('#' + picId).remove();
            },
            error: function (response) {
            }
        });
    }

    $(document).on('click', '#createProject', function (event) {

        project.typeOfProject = $('#projectType').val();
        project.projectName = $('#projectName').val();
        project.projectDescription = tinymce.activeEditor.getContent({format : 'raw'});
        project.amountRequested = $('#amountRequested').val();
        project.categoriesOfIndustry = $('#categoriesOfIndustry').val();
        for(var key in imagesIds) {
            imagesIds[key] = 'pic1';
            break;
        }
        project.imagesIds = imagesIds;

        $.ajax({
            type: "POST",
            url: "/api/rest/projectsAndInvestmentsService/project/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(project),
            success: function (createdProjectId) {
                window.location.href = '/project/id/' + createdProjectId.id;
            },
            error: function (response) {
                alert("Проверьте введенные значения");
            }
        });
    });

    tinymce.init({
        selector: 'textarea',
        height: 300,
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
        ]
    });

</script>
</body>
</html>
