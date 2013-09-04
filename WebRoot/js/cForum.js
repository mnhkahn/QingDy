(function($) {
    $.fn.cForum = function(countContainer, url) {
        var container = this;
        _getdata(url, container, countContainer);
    };

    var _showForum = function(container, json) {
        setInterval(
            _getdata(),
            3000);
    };
    var _show_num = function(container, n){
        var it = $(container + " i");
        var len = String(n).length;
        for(var i=0;i<len;i++){
            if(it.length<=i){
                $("#" + container).append("<i></i>");
            }
            var num=String(n).charAt(i);
            var y = -parseInt(num)*30; //y轴位置
            var obj = $("#" + container + " i").eq(i);
            obj.animate(
                {
                    'background-position-x': 0,
                    'background-position-y': String(y) + 'px'
                },
                'slow',
                'swing',
                function () {
                }
            );
        }
    }

    var _getdata = function(url, container, countContainer) {
        $.ajax({
            url: url,
            type: 'GET',
            dataType: "json",
            cache: false,
            timeout: 10000,
            error: function(response){
                console.debug(response);
            },
            success: function(data){
                _show_num(countContainer[0], data.mallCount);
                _show_num(countContainer[1], data.specialistCount);
                _show_num(countContainer[2], data.transactionCount);

                if (data.timelines) {
                    _showSlide(container, data.timelines);
                }
                startMove(container);
            }
        })
    }

    var _showSlide = function(container,json) {
        var liHeight = container.height() / 2;

        // draw container
        container.css("overflow", "hidden");
        container.css("position", "relative");

        var VSlideUl = $("<ul></ul>");
        VSlideUl.addClass("VSlideUl");
        VSlideUl.attr("id", "VSlideUl");
        VSlideUl.css("width", container.width()-22);
        VSlideUl.css("height", container.height()-2);

        for (var i = 0; i < json.length; i++) {
            var VSlideLi = $("<li></li>");
            VSlideLi.addClass("VSlideLi");
            VSlideLi.attr("id", "VSlideLi" + i);
            VSlideLi.css("top", i * liHeight);
            VSlideLi.css("height", liHeight);

            var time = $("<div></div>");
            time.addClass("VSlideTime");
            time.html(json[i].time);

            var title = $("<div></div>");
            title.html('<a href="' + getLink(json[i].type, json[i].id) + '" target="_blank" title="' + json[i].name + '" class="">' + "[" + json[i].name + "]   " + getTitle(json[i].type) + '</a>');
            title.addClass("VSlideTitle");
            VSlideLi.append(time);
            VSlideLi.append(title);
            VSlideUl.append(VSlideLi);
        }

        container.append(VSlideUl);
    }
    var move = function(isTop, container) {
        var liHeight = container.height() / 2;

        var lis = $('.VSlideLi')
        for (var i = 0; i < $('.VSlideLi').length; i++) {
            //console.debug(lis.eq(i).position().top);
            //console.debug(-70 * (lis.length - 2));
            if (lis.eq(i).position().top <= -liHeight * (lis.length - 2)) {
                lis.eq(i).css("top", liHeight * 2);
            }
//            console.debug(lis.eq(i).css("top"));
        }

        lis.animate({top: "-=" + liHeight + "px"}, "slow");
    }

    var startMove = function(container) {
        var changeTimer = window.setInterval(
            function(){move(false, container)}, 5000);
    }
})(jQuery);