/**
 * Created with JetBrains WebStorm.
 * User: Bryce
 * Date: 13-9-11
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */

(function($) {
    $.fn.cTemplate = function(url, templateURL, adapter) {
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
                if (arguments.length > 2 && adapter)
                    adapter(data);

                _show(container, data, templateURL);
            }
        })
    };

    var _show = function(container, json, templateURL) {
        $.get(templateURL, function(template) {
            $.template("Tmpl", template);
            $.tmpl("Tmpl", json).appendTo(container);
        });
        console.debug(json);
    };
})(jQuery);