var cMenu= {
	create: function(container, url, selected, reset) {
		var THIS = this;
		this.arguments = arguments;
        this.selected = selected ? selected : 0;
        this.reset = reset;
		$.ajax({
			type: "GET",
			dataType: "json",
			url: url,
			success: function(arr) {
				THIS.arr = arr;
				THIS.container = container;
				THIS._showMenu();
			},
			error: function(response) {
				console.error(response);
			}
		});
	},

	_showMenu: function() {

		var menuWrapper = $('#' + this.container);

		var navWrapper = $('<div></div>');
		navWrapper.attr("id", "navWrapper");
		navWrapper.addClass("navWrapper");

		var n = 0;
		for (var i in this.arr.menus) {
			var navGroup = $('<div></div>');
			navGroup.attr("id", "navGroup");
			navGroup.addClass("navGroup");

			var navItemHeader = $('<div></div>');
			navItemHeader.attr("id", "navItemHeader");
			navItemHeader.addClass("navItemHeader");
			navItemHeader.html(this.arr.menus[i].header);
			navGroup.append(navItemHeader);

			for (var j in this.arr.menus[i].name) {
				var navItem = $('<div></div>');
				navItem.attr("id", "navItem" + n++);
				navItem.addClass("navItem");
				navItem.html(this.arr.menus[i].name[j]);

				var THIS = this;
				navItem.click(function(event) {
					var m = new Number(event.currentTarget.id.substring(7));
					console.debug(THIS.arguments[m + 4]);

					for (var k = 0; k < n; k++) {
						var item = $('#navItem' + k);
						if (item.hasClass("navItemClick")) {
							item.removeClass("navItemClick");
						}
					}
					$('#navItem' + m).addClass("navItemClick");

                    THIS.reset.apply();
					if (THIS.arguments[m + 4])
						THIS.arguments[m + 4].apply();
				});

				navGroup.append(navItem);
			}

			navWrapper.append(navGroup);

			if (i != this.arr.menus.length - 1) {
				var navBorder = $('<div></div>');
				navBorder.addClass("navBorder");
				navWrapper.append(navBorder);
			}
		}

		menuWrapper.append(navWrapper);

		// Initialize with selected id
		if (this.selected >= 0) {
			var selectedItem = $('#navItem' + this.selected);
			console.debug(selectedItem);
			selectedItem.click();
		}
	}
}
