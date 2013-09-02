(function($) {
    $.fn.cNews = function(name, url) {
        var container = this;
        console.debug(container);

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
                _showNews(name, container, data);
            }
        })
    };

    var _showNews = function(name, container, json) {
        var newsTitle = $('<div></div>');
        newsTitle.addClass("newsTitle");
        newsTitle.html(name);
        container.append(newsTitle);

        var ul = $('<ul></ul>');
        ul.css("list-style", "none");
        for (var i = 0; i < json.length; i++) {
            var li = $('<li></li>');
            li.attr('href', json[i].link);
            li.addClass('hover');

            if (i != json.length)
                li.addClass('dashedBottomBorder');

            var titletime = $('<div></div>');
            titletime.addClass('newsTitleTime');

            var title = $('<div></div>');
            title.addClass('newsName');
            title.addClass("list");
            var time = $('<div></div>');

            time.addClass('newsTime');

            title.html(json[i].title);
            time.html(json[i].postDate);

            if (i === 0) {
                li.addClass('li1');

                var img = $('<div></div>');
                img.addClass('newsImg');
//                img.html('<img style="width: 90px; height: 73px" src="' + json[i].img + '" alt="' + json[i].title + '">');
                li.append(img);

                var content = $('<div></div>');
                content.addClass('newsContent');
                content.html(json[i].content);
                li.append(content);
            }
            else {
                li.addClass('li2');
            }
            titletime.append(title);
            titletime.append(time);

            this.link = ["1.html"];
            var THIS = this;
            li.append(titletime);
            li.click((function() {
                window.open(this.getAttribute('href'));
            }));
            ul.append(li);
        }
        container.append(ul);
    };
})(jQuery);