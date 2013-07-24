var cVSlide = {
    create: function(containerID, url) {
        this.container = $('#' + containerID);
        this.liHeight = this.container.height() / 2;

        var THIS = this;
        $.ajax({
            type: "GET",
            dataType: "json",
            url: url,
            success: function(response) {
                THIS.json = eval(response);
                THIS._showSlide();
                THIS.startMove();
            },
            error: function(response) {
                console.error(response);
            }
        });
    },
    _showSlide: function() {
        // draw container
        this.container.css("overflow", "hidden");
        this.container.css("position", "relative");

        var VSlideUl = $("<ul></ul>");
        VSlideUl.addClass("VSlideUl");
        VSlideUl.attr("id", "VSlideUl");
        VSlideUl.css("width", this.container.width()-22);
        VSlideUl.css("height", this.container.height()-2);

        for (var i = 0; i < this.json.length; i++) {
            var VSlideLi = $("<li></li>");
            VSlideLi.addClass("VSlideLi");
            VSlideLi.attr("id", "VSlideLi" + i);
            VSlideLi.css("top", i * this.liHeight);
            VSlideLi.css("height", this.liHeight);

            var time = $("<div></div>");
            time.addClass("VSlideTime");
            time.html(this.json[i].time);

            var title = $("<div></div>");
            title.html('<a href="' + this.json[i].link + '" target="_blank" title="' + this.json[i].title + '" class="">' + this.json[i].title + '</a>');
            title.addClass("VSlideTitle");
            VSlideLi.append(time);
            VSlideLi.append(title);
            VSlideUl.append(VSlideLi);
        }

        this.container.append(VSlideUl);
    },
    move: function(isTop) {
        var lis = $('.VSlideLi')
        for (var i = 0; i < $('.VSlideLi').length; i++) {
            //console.debug(lis.eq(i).position().top);
            //console.debug(-70 * (lis.length - 2));
            if (lis.eq(i).position().top <= -this.liHeight * (lis.length - 2)) {
                lis.eq(i).css("top", this.liHeight * 2);
            }
//            console.debug(lis.eq(i).css("top"));
        }

        lis.animate({top: "-=" + this.liHeight + "px"}, "slow");
    },

    startMove: function() {
        var THIS = this;
        this.changeTimer = window.setInterval(
            function(){THIS.move(false)}, 5000);
    }
};