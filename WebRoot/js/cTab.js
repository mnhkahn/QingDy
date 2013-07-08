var cTab = {
    create: function (container, url, selected, reset) {
        this.arguments = arguments;
        this.DEFAULT_UL_WIDTH = 80;
        this.MAX_NAV_WIDTH = 650;
        this.DEFAULT_LOGO_WIDTH = 130;

        this.selected = selected ? selected : 0;
        this.container = $('#' + container);
        this.reset = reset;

        var THIS = this;
        $.ajax({
            type: "GET",
            dataType: "json",
            url: url,
            success: function(arr) {
                THIS.arr = arr;
                THIS._showTab();
            },
            error: function(response) {
                console.error(response);
            }
        });
    },

    _showTab: function () {

        // Navigate
        var nav = $('<div></div>');
        nav.addClass('nav');
        nav.attr('id', 'nav');
        nav.css("float", "left");
        nav.css("width", this.MAX_NAV_WIDTH);
        nav.css("height", "100%");

        // Navigate width
        if (this.MAX_NAV_WIDTH > this.arr.length * this.DEFAULT_UL_WIDTH) {
            this.ul_width = this.DEFAULT_UL_WIDTH;
        } else {
            this.ul_width = this.MAX_NAV_WIDTH / this.arr.length;
        }

        var ulNav = $('<ul></ul>');
        ulNav.addClass('horizontalList');
        ulNav.attr("id", "horizontalLIst");
        ulNav.css("width", "100%");
        ulNav.css("height", "100%");
        ulNav.css("list-style", "none outside none");
        ulNav.css("margin", 0);
        ulNav.css("padding", 0);

        // Items
        for (var i in this.arr) {
            var liItem = $('<li></li>');
            if (i == this.selected) {
                liItem.addClass("pageNavSelected");
            }
            liItem.addClass("page-anchor-link");
            liItem.attr("id", "pageNav" + i);
            liItem.html(this.arr[i]);
            liItem.css("float", "left");
            liItem.css("width", this.ul_width);
            liItem.css("height", "100%");
            liItem.css("overflow", "hidden");
            liItem.css("padding", 0);

            var THIS = this;
            liItem.click(function(event) {
                var m = new Number(event.currentTarget.id.substring(7));
                console.debug(THIS.arguments[m + 4]);

                for (var k = 0; k < THIS.arr.length; k++) {
                    var item = $('#pageNav' + k);
                    if (item.hasClass("pageNavSelected")) {
                        item.removeClass("pageNavSelected");
                    }
                }
                $('#pageNav' + m).addClass("pageNavSelected");

                THIS.reset.apply();
                if (THIS.arguments[m + 4])
                    THIS.arguments[m + 4].apply();
            });

            ulNav.append(liItem);
        }

        nav.append(ulNav);

        this.container.css("border-bottom", "solid rgb(217, 217, 217) 1px");
        this.container.css("height", 45);
        this.container.css("width", "100%");

        this.container.append(nav);

        // Initialize with selected id
        if (this.selected >= 0) {
            var selectedItem = $('#pageNav' + this.selected);
            console.debug(selectedItem);
            selectedItem.click();
        }
    }
};