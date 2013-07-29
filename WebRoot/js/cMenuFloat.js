(function($) {
    $.fn.cMenuFloat = function(url) {
        var THIS = this;
        $.ajax({
            url: url,
            type: "GET",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(response) {
                console.debug(response);
                _showMenuFloat(THIS, response);
            },
            error: function(response) {

            }
        });
    };
    _showMenuFloat = function(container, data) {
        container.css("overflow", "hidden");

        var top = $("<div></div>");
        top.addClass("newsTitle");
        top.html(data.title);
        container.append(top);

        // create classes
        for (var i = 0; i < data.items.length; i++) {
            var questionClassesContainer = $("<div></div>");
            questionClassesContainer.css("width", "100%");
            questionClassesContainer.css("overflow", "auto");
            questionClassesContainer.css("zoom", 1); // 4 ie6
            container.append(questionClassesContainer);
            var questionClasses = $("<div></div>");
            questionClasses.addClass("questionClasses");
            questionClasses.html("&nbsp;&nbsp;&gt;&nbsp;" + data.items[i]);
            questionClassesContainer.append(questionClasses);

            for (var j = 0; j < data.subitems[i].length; j++) {
                var questionSubClasses = $("<div></div>");
                questionSubClasses.addClass("questionSubClasses");
                questionSubClasses.html("<a href='#' >" + data.subitems[i][j] + "</a>");
                questionSubClasses.css("font-size", "15px");
                questionSubClasses.css("padding", "4px 6px");
                questionSubClasses.css("float", "left");
                questionClassesContainer.append(questionSubClasses);
            }
        }

        // create subclasses


    }
})(jQuery);
