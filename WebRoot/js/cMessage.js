(function($) {
    $.fn.cMessage = function(url) {
        var container = this;

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
                _showMessage(container, data);
            }
        })
    };

    var _showMessage = function(container, json) {
        console.debug(container);
        var messageContainer = $('<div></div>');
        container.append(messageContainer);
        messageContainer.addClass("messageContainer");
        messageContainer.attr("id", "messageContainer");

        $.get("/js/templates/message.html", function(template) {
            $.template("messageTmpl", template);
            $.tmpl("messageTmpl", json).appendTo("#messageContainer");
        });
         console.debug(json);
    };
})(jQuery);