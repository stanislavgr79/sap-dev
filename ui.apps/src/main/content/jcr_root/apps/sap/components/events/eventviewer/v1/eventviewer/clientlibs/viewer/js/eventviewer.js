
$(document).ready(function () {
    var sort = $('#event_sort').data('sort');
    changeSortEvents(sort);
    $('#event_sort').change();
})

$('#events__view-topic').on("click", function () {
    $(this).addClass('active');
    $('#events__view-type').removeClass('active');
    let sort = $(this).data('sort');
    changeSortEvents(sort);

    $('#event_sort').data('sort', 'topic');
    $('#event_sort').change();

});

$('#events__view-type').on("click", function () {
    $(this).addClass('active');
    $('#events__view-topic').removeClass('active');
    let sort = $(this).data('sort');
    changeSortEvents(sort);

    $('#event_sort').data('sort', 'type');
    $('#event_sort').change();
});



$('#event_sort').change(function () {

    let topics = {
        database: $('.events__Database'),
        cloud: $('.events__Cloud'),
        mobile: $('.events__Mobile'),
        other: $('.events__Other')
    }

    if ($('#event_sort').data('sort') == 'topic') {
        if (topics.database) {
            $(topics.database).addClass('icon-hdd');
        }
        if (topics.cloud) {
            $(topics.cloud).addClass('icon-cloud');
        }
        if (topics.mobile) {
            $(topics.mobile).addClass('icon-mobile-phone');
        }
        if (topics.other) {
            $(topics.other).addClass('icon-calendar');
        }
    }

    if ($('#event_sort').data('sort') == 'type') {

        $('.icon-Database').addClass('icon-hdd');
        $('.icon-Cloud').addClass('icon-cloud');
        $('.icon-Mobile').addClass('icon-mobile-phone');
        $('.icon-Other').addClass('icon-calendar');
    }
})


function changeSortEvents(sort) {
    var path_event = $('#event_sort').data("path");
    var method_event = $('#event_sort').data("method");
    var params = {
        sortEvent: sort
    };

    if (sort == "topic") {
        $.ajax({
            type: method_event,
            url: path_event + '.sort.json',
            data: params,
            contentType: 'application/json',
            success: function (response, status, request) {

                if (status == 'success') {
                    var output = "";

                    $.each(response, function (key, value) {
                        output += "<div class='span3'>";
                        output += "<h3>" + key + "<i class='events__" + key + "'></i></h3>";

                        $(value).each(function (index, el) {
                            output += "<ul class='unstyled'>";
                            output += "<li class='events_topic'>";
                            output += "<span class='date' type='date'>" + formatDate(el.eventStartDate) + "</span>";
                            output += "<h4><a href='#'>" + el.title + "</a></h4>";
                            output += el.description;
                            output += "</li></ul>";
                        });
                        output += "</div>";
                        $('#all-events').html(output);
                        $('#event_sort').change();
                    });
                }
            }
        })
    }

    if (sort == "type") {
        $.ajax({
            type: method_event,
            url: path_event + '.sort.json',
            data: params,
            contentType: 'application/json',
            success: function (response, status, request) {
                if (status == 'success') {
                    var output = "";

                    $.each(response, function (key, value) {
                        output += "<div class='span3'>";
                        output += "<h3>" + key + "<i class='events__" + key + "'></i></h3>";

                        $(value).each(function (index, el) {
                            output += "<ul class='icons icons_type'><i class='icon-" + el.topic + "'></i>";
                            output += "<li class='events_type'>";
                            output += "<span class='date' type='date'>" + formatDate(el.eventStartDate) + "</span>";
                            output += "<h4><a href='"+el.titleLink+""'>" + el.title + "</a></h4>";
                            output += el.description;
                            output += "</li></ul>";
                        });
                        output += "</div>";
                        $('#all-events').html(output);
                        $('#event_sort').change();
                    });
                }
            }
        })
    }
}

function formatDate(date) {
    return new Date(date).toLocaleString('en-US', {
        day: '2-digit',
        month: 'long'
    });
}
