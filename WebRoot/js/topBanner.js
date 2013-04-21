

function showTopBanner() {
	// Query the id from url
	var id = 1;
	
	var divHeaderWrapper = document.createElement("DIV");
	divHeaderWrapper.className = "header-wrapper";
	
	var divSiteHeader = document.createElement("DIV");
	divSiteHeader.className = "site-header";
	divHeaderWrapper.appendChild(divSiteHeader);
	
	// Logo
	var divLogoWrapper = document.createElement("DIV");
	divLogoWrapper.className = "site-header-logo-container";
	divLogoWrapper.innerHTML = "<a href='#'><img src='images/logo.png' /></a>";	
	divSiteHeader.appendChild(divLogoWrapper);
	
	// Nav
	var divNav = document.createElement("DIV");
	divNav.className = "nav";
	
	var arrNav = new Array("home", "supply", "mall", "specialist", "demand", "advisory", "news");
	var arrNavLabel = new Array("首页", "贷款供应", "贷款机构", "贷款顾问", "贷款需求", "贷款咨询", "贷款资讯");
	var ulNav = document.createElement("ul");
	ulNav.id = "page-nav";
	ulNav.className = "horizontal-list";
	for (var i = 0; i < arrNav.length; i++) {
		var li = document.createElement("li");
		li.className = "page-nav-top";
		li.innerHTML = "<a class='page-anchor-link' href='" + arrNav[i] + ".html'>" + arrNavLabel[i] +"</a>";    	
		ulNav.appendChild(li);
	}
	
	divNav.appendChild(ulNav);	
	divSiteHeader.appendChild(divNav);
	
	// Info
	var divInfo = document.createElement("DIV");
	divInfo.className = "site-header-info-container";
	divSiteHeader.appendChild(divInfo);
	
	// Get user info from cookie
	

	var login = true;
	var userrealname = "李超";
	
	// Show login and register if no cookie
	if (login == true) {
		var divName = document.createElement("DIV");
		divName.className = "namecontainer";
		divName.innerHTML = "<div class='ico'><div class='ui_element button_user'></div></div><div class='username'>" + userrealname + "</div>";
		divInfo.appendChild(divName);
		
		var divMessage = document.createElement("DVI");
		divMessage.className = "messagecontainer";
		divMessage.innerHTML = "<div class='ico'><div class='ui_element button_message'></div></div>";
		divInfo.appendChild(divMessage);
	}
	// Show user info if has cookie
	else {
		var divLogin = document.createElement("DIV");
		divLogin.innerHTML = "<div onClick='alert(1)' class='blackbutton'>登录</div>";
		divInfo.appendChild(divLogin);
		
		var divRegister = document.createElement("DIV");
		divRegister.innerHTML = "<div class='blackbutton'>注册</div>"
		divInfo.appendChild(divRegister);
	}
	
	dojo.byId("site-header-container").appendChild(divHeaderWrapper);
}

function showSearchBox() {
	var searchboxContent = document.createElement("DIV");
	searchboxContent.className = "searchboxContent";
	
	var divSearchTypeSelect = document.createElement("DIV");
	divSearchTypeSelect.className = "divSearchTypeSelect";
	searchboxContent.appendChild(divSearchTypeSelect);
	
	var selectType = document.createElement("select");
	selectType.className = "selectType";
	selectType.appendChild(new Option("贷款银行","product"));
	selectType.appendChild(new Option("贷款需求","demand"));
	selectType.appendChild(new Option("专家顾问","specialist"));
	selectType.appendChild(new Option("贷款机构","mall"));
	selectType.appendChild(new Option("贷款银行","bank"));
	
	divSearchTypeSelect.appendChild(selectType);
	
	var divSearchbox = document.createElement("DIV");
	divSearchbox.className = "searchbox";
	searchboxContent.appendChild(divSearchbox);
	
	var divTextSearch = document.createElement("DIV");
	divTextSearch.className = "divTextSearch";
//	divTextSearch.onfocus = function(){ border:1px solid #FFD42C; };
	divSearchbox.appendChild(divTextSearch);
	
	var textSearch = document.createElement("input");
	textSearch.className = "textSearch";
	textSearch.type = "text";
	divTextSearch.appendChild(textSearch);
	
	var btnSearch = document.createElement("button");
	btnSearch.className = "largeBtn";
	
	var spanIco = document.createElement("span");
	spanIco.className = "smallcio searchico";
	btnSearch.appendChild(spanIco);
	
	divSearchbox.appendChild(btnSearch);
	
	dojo.byId("searchboxContainer").appendChild(searchboxContent);
}