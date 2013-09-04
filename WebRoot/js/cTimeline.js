(function($) {
    $.fn.cTimeline = function(url) {
        var container = this;
        $.ajax({
            url: url,
            type: 'GET',
            dataType: "json",
            cache: false,
            timeout: 10000,
            error: function(response){
                console.warn(response);
            },
            success: function(response){
                var data = [];
                data = _getDate(response);
                _showTimeline(container, data);
                addAction();
            }
        })
    };
    var _getDate = function(response) {
        var date = [];
        for (var i = 0; i < response.length; i++) {
            var tempdate = _getDate1(response[i].time);
            var predate;
            if (date[date.length - 1] && date[date.length - 1].time)
                predate = _getDate1(date[date.length - 1].time);
            if (!(date[date.length - 1] && predate.year === tempdate.year && predate.month === tempdate.month)) {
                date.push(tempdate);
            }
            date.push(response[i]);
        }
        console.debug(date);
        return date;
    };

    var _getDate1 = function(date) {
        var year = date.substring(0, date.indexOf('-'));
        var month = date.substring(date.indexOf('-') + 1, date.lastIndexOf('-'));
        return {"year": year, "month": month};
    };

    var _showTimeline = function(container, data) {
        var sslinks = $("<div></div>");
        sslinks.attr("id", "ss-links");
        sslinks.addClass("ss-links");

        var sscontainer = $("<div></div>");
        sscontainer.attr("id", "ss-container");
        sscontainer.addClass("ss-container");
        for (var i = 0; i < data.length; i++) {
            var ssrow = $("<div></div>");
            ssrow.addClass("ss-row");
            ssrow.addClass("ss-small");

            var ssleft = $("<div></div>");
            ssleft.addClass("ss-left");
            var ssright = $("<div></div>");
            ssright.addClass("ss-right");

            var time, title;
            if (data[i].year) {
                time = $("<h2></h2>");
                time.html(data[i].month + "月");

                title = $("<h2></h2>");
                title.attr("id", data[i].year + data[i].month);
                title.html(data[i].year);

                var a = $("<a></a>");
                a.attr("href", "#" + data[i].year + data[i].month);
                a.html(data[i].year.substring(2) + "/" + data[i].month);
                sslinks.append(a);
            }
            else {
                time = $("<a></a>");
                time.addClass("ss-circle");
                time.html(data[i].time);
                title = $("<h3></h3>");
                var titlea = $("<a></a>");
                titlea.attr("href", getLink(data[i].type, data[i].id));
                titlea.html(getTitle(data[i].type));
                title.append(titlea);
            }


            if (i % 2 == 0) {
                ssleft.append(time);
                ssright.append(title);
            }
            else {
                ssleft.append(title);
                ssright.append(time);
            }

            ssrow.append(ssleft);
            ssrow.append(ssright);
            sscontainer.append(ssrow);
        }

        container.append(sslinks);
        container.append(sscontainer);


    };
    var addAction = function() {
        $(function () {

            var $sidescroll = (function () {

                // the row elements
                var $rows = $('#ss-container > div.ss-row'),
                // we will cache the inviewport rows and the outside viewport rows
                    $rowsViewport, $rowsOutViewport,
                // navigation menu links
                    $links = $('#ss-links > a'),
                // the window element
                    $win = $(window),
                // we will store the window sizes here
                    winSize = {},
                // used in the scroll setTimeout function
                    anim = false,
                // page scroll speed
                    scollPageSpeed = 2000 ,
                // page scroll easing
                    scollPageEasing = 'easeInOutExpo',
                // perspective?
                    hasPerspective = false,

                    perspective = hasPerspective && Modernizr.csstransforms3d,
                // initialize function
                    init = function () {

                        // get window sizes
                        getWinSize();
                        // initialize events
                        initEvents();
                        // define the inviewport selector
                        defineViewport();
                        // gets the elements that match the previous selector
                        setViewportRows();
                        // if perspective add css
                        if (perspective) {
                            $rows.css({
                                '-webkit-perspective': 600,
                                '-webkit-perspective-origin': '50% 0%'
                            });
                        }
                        // show the pointers for the inviewport rows
                        $rowsViewport.find('a.ss-circle').addClass('ss-circle-deco');
                        // set positions for each row
                        placeRows();

                    },
                // defines a selector that gathers the row elems that are initially visible.
                // the element is visible if its top is less than the window's height.
                // these elements will not be affected when scrolling the page.
                    defineViewport = function () {

                        $.extend($.expr[':'], {

                            inviewport: function (el) {
                                if ($(el).offset().top < winSize.height) {
                                    return true;
                                }
                                return false;
                            }

                        });

                    },
                // checks which rows are initially visible
                    setViewportRows = function () {

                        $rowsViewport = $rows.filter(':inviewport');
                        $rowsOutViewport = $rows.not($rowsViewport)

                    },
                // get window sizes
                    getWinSize = function () {

                        winSize.width = $win.width();
                        winSize.height = $win.height();

                    },
                // initialize some events
                    initEvents = function () {

                        // navigation menu links.
                        // scroll to the respective section.
                        $links.on('click.Scrolling', function (event) {

                            // scroll to the element that has id = menu's href
                            $('html, body').stop().animate({
                                scrollTop: $($(this).attr('href')).offset().top
                            }, scollPageSpeed, scollPageEasing);

                            return false;

                        });

                        $(window).on({
                            // on window resize we need to redefine which rows are initially visible (this ones we will not animate).
                            'resize.Scrolling': function (event) {

                                // get the window sizes again
                                getWinSize();
                                // redefine which rows are initially visible (:inviewport)
                                setViewportRows();
                                // remove pointers for every row
                                $rows.find('a.ss-circle').removeClass('ss-circle-deco');
                                // show inviewport rows and respective pointers
                                $rowsViewport.each(function () {

                                    $(this).find('div.ss-left')
                                        .css({ left: '0%' })
                                        .end()
                                        .find('div.ss-right')
                                        .css({ right: '0%' })
                                        .end()
                                        .find('a.ss-circle')
                                        .addClass('ss-circle-deco');

                                });

                            },
                            // when scrolling the page change the position of each row
                            'scroll.Scrolling': function (event) {

                                // set a timeout to avoid that the
                                // placeRows function gets called on every scroll trigger
                                if (anim) return false;
                                anim = true;
                                setTimeout(function () {

                                    placeRows();
                                    anim = false;

                                }, 10);

                            }
                        });

                    },
                // sets the position of the rows (left and right row elements).
                // Both of these elements will start with -50% for the left/right (not visible)
                // and this value should be 0% (final position) when the element is on the
                // center of the window.
                    placeRows = function () {

                        // how much we scrolled so far
                        var winscroll = $win.scrollTop(),
                        // the y value for the center of the screen
                            winCenter = winSize.height / 2 + winscroll;

                        // for every row that is not inviewport
                        $rowsOutViewport.each(function (i) {

                            var $row = $(this),
                            // the left side element
                                $rowL = $row.find('div.ss-left'),
                            // the right side element
                                $rowR = $row.find('div.ss-right'),
                            // top value
                                rowT = $row.offset().top;

                            // hide the row if it is under the viewport
                            if (rowT > winSize.height + winscroll) {

                                if (perspective) {

                                    $rowL.css({
                                        '-webkit-transform': 'translate3d(-75%, 0, 0) rotateY(-90deg) translate3d(-75%, 0, 0)',
                                        'opacity': 0
                                    });
                                    $rowR.css({
                                        '-webkit-transform': 'translate3d(75%, 0, 0) rotateY(90deg) translate3d(75%, 0, 0)',
                                        'opacity': 0
                                    });

                                }
                                else {

                                    $rowL.css({ left: '-50%' });
                                    $rowR.css({ right: '-50%' });

                                }

                            }
                            // if not, the row should become visible (0% of left/right) as it gets closer to the center of the screen.
                            else {

                                // row's height
                                var rowH = $row.height(),
                                // the value on each scrolling step will be proporcional to the distance from the center of the screen to its height
                                    factor = ( ( ( rowT + rowH / 2 ) - winCenter ) / ( winSize.height / 2 + rowH / 2 ) ),
                                // value for the left / right of each side of the row.
                                // 0% is the limit
                                    val = Math.max(factor * 50, 0);

                                if (val <= 0) {

                                    // when 0% is reached show the pointer for that row
                                    if (!$row.data('pointer')) {

                                        $row.data('pointer', true);
                                        $row.find('.ss-circle').addClass('ss-circle-deco');

                                    }

                                }
                                else {

                                    // the pointer should not be shown
                                    if ($row.data('pointer')) {

                                        $row.data('pointer', false);
                                        $row.find('.ss-circle').removeClass('ss-circle-deco');

                                    }

                                }

                                // set calculated values
                                if (perspective) {

                                    var t = Math.max(factor * 75, 0),
                                        r = Math.max(factor * 90, 0),
                                        o = Math.min(Math.abs(factor - 1), 1);

                                    $rowL.css({
                                        '-webkit-transform': 'translate3d(-' + t + '%, 0, 0) rotateY(-' + r + 'deg) translate3d(-' + t + '%, 0, 0)',
                                        'opacity': o
                                    });
                                    $rowR.css({
                                        '-webkit-transform': 'translate3d(' + t + '%, 0, 0) rotateY(' + r + 'deg) translate3d(' + t + '%, 0, 0)',
                                        'opacity': o
                                    });

                                }
                                else {

                                    $rowL.css({ left: -val + '%' });
                                    $rowR.css({ right: -val + '%' });

                                }

                            }

                        });

                    };

                return { init: init };

            })();

            $sidescroll.init();

        });
    }
})(jQuery);