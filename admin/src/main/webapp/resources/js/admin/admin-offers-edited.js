let urlRussianLanguageForTables = '//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json';
let urlReadAllOffer = apiUi + '/api/rest/offersService/offer/read/admin/all';
let ulrImg = apiUi + '/api/rest/fileStorage/offers/photo/read/id/';
let urlNoPhotoImg = '/resources/images/no_photo.jpg';
let urlEditOffer = host + '/editBulletin/';

/**
 * Find first image from the whole array of images of the offer
 *
 * @param arr           - the array with images.
 * @returns {string}    - the url of the main photo.
 */
function findFirstImg(arr) {
    var url = urlNoPhotoImg;
    var imgId = '';
    for (var i in arr) {
        if (arr[i] === '1') {
            imgId = i;
            url = ulrImg + imgId + "?cachedSize=small";
            break;
        }
    }
    return url;
}


/**
 * Parse offer changes variants.
 *
 * @param currentString
 * @param change
 * @param position
 * @returns {string}
 */
let changesPreparator = function (currentString, change, position) {
    let result = '';


    switch (change) {
        case 'MODIFIED_TITLE' :
            result = 'заголовок';
            break;
        case 'MODIFIED_DESCRIPTION' :
            result = 'описание';
            break;
        case 'MODIFIED_CATEGORIES' :
            result = 'категория';
            break;
        case 'MODIFIED_PROPERTIES' :
            result = 'хар-ки';
            break;
        case 'MODIFIED_IMAGES' :
            result = 'фото';
            break;
    }

    if (currentString != '') {
        result = ', ' + result;
    }

    return result;
};


$(document).ready(function () {
    var data;
    var offerFilterOptions = {
        skip: 0,
        limit: 50,
        offerModerationReports: {
            moderationStatus: 'NO'
        },
        active: true,
        deleted: false,
        isOfferModifiedAfterModeratorCheck: true
    };


    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: urlReadAllOffer,
        data: JSON.stringify(offerFilterOptions),

        success: function (response) {

            data = response;

            for (var i = 0; i < data.length; i++) {


                // here we put changes
                let offerChanges = data[i].offerModerationReports.offerModifiedFieldLIst;
                data[i].changes = '';
                if (offerChanges) {
                    for (var j = 0; j < offerChanges.length; j++) {
                        data[i].changes = data[i].changes + (changesPreparator(data[i].changes, offerChanges[j], j));
                    }
                }

                data[i].title = '<a href="' + urlEditOffer + data[i].seoUrl + '">' + data[i].title + '</a>';

                // fill with photo
                if (data[i].imagesIds !== null) {
                    data[i].imagesIds = '<img src="' + findFirstImg(data[i].imagesIds) + '" width="100" height="100">';
                }
                else {
                    data[i].imagesIds = `<img src="${urlNoPhotoImg}" width="100" height="100">`;
                }
            }


            var newOffers = $('#offersTable').DataTable({
                select: {
                    style: 'single'
                },
                data: data,
                "columns": [
                    {"data": "imagesIds"},
                    {"data": "title"},
                    {"data": "changes"}
                ],
                "language": {
                    "url": urlRussianLanguageForTables
                }
            });

            newOffers
                .on('select', function (e, dt, type, indexes) {
                    var rowData = newOffers.rows(indexes).data().toArray();
                    $("input[name='offerId']").attr("value", rowData[0].id);
                    $("input[name='offerUrl']").attr("value", rowData[0].seoUrl);
                    $('#offerIdhref').attr("href", urlEditOffer + rowData[0].seoUrl);
                    $('#editOfferButton').attr("class", "btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='offerId']").attr("value", "");
                    $("input[name='offerUrl']").attr("value", "");
                    $('#editOfferButton').attr("class", "btn btn-danger disabled");
                });
        }
    });
});