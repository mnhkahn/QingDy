(function($) {
    $.fn.cVisitors = function(url) {
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
                _showVisitors(container, data);
            }
        })
    };

    var _showVisitors = function(container, json) {
        // create header
        var headerContainer = $('<div></div>');
        headerContainer.addClass('headerContainer');

        var title = $('<div></div>');
        title.addClass('titleContainer30');
        title.html("最近访客");
        headerContainer.append(title);

        container.append(headerContainer);

        var visitorContainer = $('<div></div>');
        visitorContainer.addClass('visitorContainer');
        visitorContainer.css("height", container.height() - headerContainer.height());
        visitorContainer.css("width", container.width());

        var visitorWrapper = $('<div></div>');
        visitorWrapper.addClass('visitorWrapper');
        visitorWrapper.css("width", visitorContainer.width() - 20);
        visitorWrapper.css("height", visitorContainer.height() - 20);

        for (var i = 0; i < 23; i++) {
            var visitorItem = $('<div></div>');
            visitorItem.addClass('visitorItem');
            var visitorItemIcon = $('<div><div>');
            visitorItemIcon.addClass('visitorItemIcon');
            visitorItemIcon.html('<img style="width:50px; height: 50px" src="avatar/avatar.png" />');
            var visitorItemName = $('<div></div>');
            visitorItemName.addClass('visitorItemName');
            visitorItemName.html("dzkkk");

            visitorItem.append(visitorItemIcon);
            visitorItem.append(visitorItemName);
            visitorWrapper.append(visitorItem);
        }

        visitorContainer.append(visitorWrapper);

        container.append(visitorContainer);
    };
})(jQuery);
           /*
var cVisitors = {
    create: function(containerId, url) {
        this.container = $('#' + containerId);
        this.json = json;
        this._showVisitors();
    },
    _showVisitors: function() {
        // create header
        var headerContainer = $('<div></div>');
        headerContainer.addClass('headerContainer');

        var title = $('<div></div>');
        title.addClass('titleContainer30');
        title.html("最近访客");
        headerContainer.append(title);

        this.container.append(headerContainer);

        var visitorContainer = $('<div></div>');
        visitorContainer.addClass('visitorContainer');
        visitorContainer.css("height", this.container.height() - headerContainer.height());
        visitorContainer.css("width", this.container.width());

        var visitorWrapper = $('<div></div>');
        visitorWrapper.addClass('visitorWrapper');
        visitorWrapper.css("width", visitorContainer.width() - 20);
        visitorWrapper.css("height", visitorContainer.height() - 20);

        for (var i = 0; i < 23; i++) {
            var visitorItem = $('<div></div>');
            visitorItem.addClass('visitorItem');
            var visitorItemIcon = $('<div><div>');
            visitorItemIcon.addClass('visitorItemIcon');
            visitorItemIcon.html('<img style="width:50px, height: 50px" src="http://tp1.sinaimg.cn/1377661900/50/1290073128/1" />');
            var visitorItemName = $('<div></div>');
            visitorItemName.addClass('visitorItemName');
            visitorItemName.html("dzkkk");

            visitorItem.append(visitorItemIcon);
            visitorItem.append(visitorItemName);
            visitorWrapper.append(visitorItem);
        }

        visitorContainer.append(visitorWrapper);

        this.container.append(visitorContainer);
    }
};                  */