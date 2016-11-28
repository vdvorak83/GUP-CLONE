let urlRussianLanguageForTables = '//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json';
let urlReadAllOffer = 'http://localhost:8184/api/rest/offersService/offer/read/admin/all';
let ulrImg = 'http://localhost:8184/api/rest/fileStorage/offers/photo/read/id/';
let urlNoPhotoImg = 'http://localhost:8185/resources/images/no_photo.jpg';
let urlEditOffer = 'http://gup.com.ua:55555/editBulletin/';

/**
 * Find first image from the whole arraye of images of the offer
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


$(document).ready(function () {
    var data;
    var offerFilterOptions = {
        skip: 0,
        limit: 50,
        offerModerationReports: {
            moderationStatus: 'NO'
        },
        active: true,
        deleted: false
    };


    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: urlReadAllOffer,
        data: JSON.stringify(offerFilterOptions),

        success: function (response) {

            data = response;

            for (var i = 0; i < data.length; i++) {

                data[i].title = '<a href="' + urlEditOffer + data[i].seoUrl + '">' + data[i].title + '</a>';

                // ToDo нужо оставить только те, у которых нет даты последней модерации (т.е. которые только что созданные)
                data[i].createdDate = new Date(parseInt(data[i].createdDate));
                data[i].createdDate = moment(data[i].createdDate).locale("ru").format('LLL');

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
                    {"data": "createdDate"}
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