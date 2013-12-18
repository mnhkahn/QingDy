var cTop = {
	create: function(arr) {
		this.size = new Object;
		this.size.width = arr.width;
		this.size.height = arr.height;
		
		this.logo = arr.logo;
		this.link = arr.link;
		
		this.items = arr.items;
		this.info = arr.info;
		
		this.DEFAULT_UL_WIDTH = 80;
		this.MAX_NAV_WIDTH = 650;
		this.DEFAULT_LOGO_WIDTH = 130;
		
		this._showTopBanner();
	},
	
	_showTopBanner: function() {
		var containerSiteHeader = $('<div></div>');
		containerSiteHeader.addClass("containerSiteHeader");
		containerSiteHeader.attr("id", "containerSiteHeader");
		containerSiteHeader.css("position", "fixed");
		containerSiteHeader.css("margin", "0px auto");
		containerSiteHeader.css("width", "100%");
		containerSiteHeader.css("height", this.size.height);
		containerSiteHeader.css("z-index", 999);
		containerSiteHeader.css("top", "0px");
		containerSiteHeader.css("left", "0px");
	
		var wrapperHeader = $('<div></div>');
		wrapperHeader.addClass("wrapperHeader");
		wrapperHeader.attr("id", "wrapperHeader");
		wrapperHeader.css("width", "100%");
		wrapperHeader.css("height", "100%");
		wrapperHeader.css("background-color", "#fff");
		wrapperHeader.css("box-shadow", "0px 1px 4px rgba(0,0,0,0.1)");
		wrapperHeader.css("border-top", "3px solid rgb(102,102,102)");
		wrapperHeader.css("border-bottom", "1px solid rgb(221,221,221)");
		
		var siteHeader = $('<div></div>');
		siteHeader.addClass("siteHeader");
		siteHeader.attr("siteHeader");
		siteHeader.css("position", "relative");
		siteHeader.css("-moz-user-select", "none");
		siteHeader.css("background", "none repeat scroll 0% 0% rgb(255,255,255)");
		siteHeader.css("width", this.size.width);
		siteHeader.css("min-width", this.size.width);
		siteHeader.css("margin-right", "auto");
		siteHeader.css("margin-left", "auto");
		siteHeader.css("height", "100%");
		
		// Logo
		var wrapperLogo = $('<div></div>');
		wrapperLogo.addClass('wrapperLogo');
		wrapperLogo.attr("id", "wrapperLogo");
		wrapperLogo.html("<a href='" + this.link + "'><img src='" + this.logo + "' /></a>");
		wrapperLogo.css("float", "left");
		wrapperLogo.css("width", this.DEFAULT_LOGO_WIDTH);
		
		// Navigate
		var nav = $('<div></div>');
		nav.addClass('nav');
		nav.attr('id', 'nav');
		nav.css("float", "left");
		nav.css("width", this.MAX_NAV_WIDTH);
		nav.css("height", "100%");
		
		// Navigate width
		if (this.MAX_NAV_WIDTH > this.items.length * this.DEFAULT_UL_WIDTH) {
			this.ul_width = this.DEFAULT_UL_WIDTH;
		}
		else {
			this.ul_width = this.MAX_NAV_WIDTH / this.items.length;
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
		for (i in this.items) {
			var liItem = $('<li></li>');
			liItem.addClass("pageNav");
			liItem.attr("id", "pageNav" + i);
			liItem.html("<a class='page-anchor-link' href='" + this.items[i].link + "'>" + this.items[i].title +"</a>");
			liItem.css("float", "left");
			liItem.css("width", this.ul_width);
			liItem.css("height", "100%");
			liItem.css("overflow", "hidden");
			liItem.css("padding", 0);
			ulNav.append(liItem);
		}
		
		nav.append(ulNav);
		
		siteHeader.append(wrapperLogo);
		siteHeader.append(nav);
		
		// Info
		var wrapperInfo = $('<div></div>');
		wrapperInfo.addClass("wrapperInfo");
		wrapperInfo.attr("id", "wrapperInfo");
		wrapperInfo.css("width", 214);
		wrapperInfo.css("height", "100%");
		wrapperInfo.css("float", "right");
		
		// Has login
		if (this.info.name != "") {
			var divIcon = $('<div></div>');
			divIcon.addClass("divIcon");
			divIcon.attr("id", "divIcon");
			divIcon.css("width", 44);
			divIcon.css("height", "100%");
			divIcon.css("float", "left");
			divIcon.html("<img src='" + this.info.icon + "' style='width:" + divIcon.width() + "px;height:" + divIcon.width() + "px' />");
			
			var divName = $('<div></div>');
			divName.addClass("divName");
			divName.attr("id", "divName");
			divName.css("width", 76);
			divName.css("height", "100%");
			divName.css("line-height", "45px");
			divName.css("text-align", "center");
			divName.css("float", "left");
			divName.html(this.info.name);
		
			// Name
			var wrapperName = $('<div></div>');
			wrapperName.addClass("wrapperName");
			wrapperName.attr("id", "wrapperName");
			wrapperName.css("width", 124);
			wrapperName.css("height", "100%");
			wrapperName.css("float", "left");
			
			wrapperName.append(divIcon);
			wrapperName.append(divName);

            var that = this;
            wrapperName.click(function() {
                window.open(that.info.link + "?id=" + that.info.uid ,'_newtab');
            });


            var wrapperLogout = $('<div></div>');
            wrapperLogout.addClass("wrapperLogout");
            wrapperLogout.attr("id", "wrapperLogout");
            wrapperLogout.css("width", 44);
            wrapperLogout.css("height", "100%");
            wrapperLogout.css("float", "left");

            // Logout
            var ico1 = $('<div></div>')
            ico1.addClass("ico");
            //		ico.attr("id", "ico");
            wrapperLogout.click(function() {
                logout();
            });


            var element = $('<div></div>');
            element.addClass("button_logout");
            ico1.append(element);
            wrapperLogout.append(ico1);

			var wrapperMessage = $('<div></div>');
			wrapperMessage.addClass("wrapperMessage");
			wrapperMessage.attr("id", "wrapperMessage");
			wrapperMessage.css("width", 44);
			wrapperMessage.css("height", "100%");
			wrapperMessage.css("float", "left");

            // Message
			var ico2 = $('<div></div>')
			ico2.addClass("ico");
	//		ico2.attr("id", "ico");
			
			
			var element = $('<div></div>');
			if (this.info.message > 0) {
				element.addClass("ui_element button_has_message");
			}
			else {
				element.addClass("ui_element button_message");
			}
			ico2.append(element);
			wrapperMessage.append(ico2);
            wrapperMessage.click(function() {
                window.open(that.info.link + "?id=" + that.info.uid + "&tab=2&nav=2" ,'_newtab');
            });

	//		wrapperMessage.html("<div class='ico'><div class='ui_element button_message'></div></div>");
			wrapperInfo.append(wrapperName);
			wrapperInfo.append(wrapperMessage);
            wrapperInfo.append(wrapperLogout);
		}
		else {
			var divLogin = $('<div></div>');
			divLogin.addClass("divLogin");
			divLogin.attr("id", "divLogin");
			divLogin.css("width", "100%");
			divLogin.css("height", "100%");
			divLogin.css("float", "left");
			divLogin.html("<a class='login-anchor-link' href='" + this.info.link + "' width='" + divLogin.width() + "'>" + "Login" + "</a>");
			
			var wrapperLogin = $('<div></div>');
			wrapperLogin.addClass("wrapperLogin");
			wrapperLogin.attr("id", "wrapperLogin");
			wrapperLogin.append(divLogin);
			wrapperLogin.css("widht", "100%");
			wrapperLogin.css("height", "100%");
			
			wrapperInfo.append(wrapperLogin);
		}
		
		siteHeader.append(wrapperInfo);
		
		wrapperHeader.append(siteHeader);
		
		containerSiteHeader.append(wrapperHeader);
		$('body').append(containerSiteHeader);
	}
}
