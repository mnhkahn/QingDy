

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
	
	$("#site-header-container").append(divHeaderWrapper);
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
	
	$("#searchboxContainer").append(searchboxContent);
}

function showFoot() {
	var arr = new Array('青帝首页', '网站地图', '关于青帝', '服务条款', '法律声明', '诚聘英才', '联系我们', '广告服务', '资讯中心', '新手入门');
	var links = new Array('.', '#', '#', '#', '#', '#', '#', '#', '#', '#');
	var footTable = document.createElement("table");
	footTable.className = "footTable";

	row = document.createElement('tr');
	for ( var j = 0; j < 10; j++) {
		cell = document.createElement('td');
		cell.className = "foot" + 1;
		cell.id = "foot" + 1;
		cell.innerHTML = "<a href='" + links[j] + "'>" + arr[j] + "</a>";
//		cell.appendChild(document.createTextNode());
		row.appendChild(cell);
	}
//	row.appendChild(cell);
	footTable.appendChild(row);

	row = document.createElement('tr');
	cell = document.createElement('td');
	cell.className = "foot" + 2;
	cell.id = "foot" + 2;
	cell.colSpan = "10";
	cell.appendChild(document.createTextNode('Copyright ©1997-2009 Edai Corporation.All Rights Reserved.  成都易贷网络科技有限公司'));
	row.appendChild(cell);
	footTable.appendChild(row);

	row = document.createElement('tr');
	cell = document.createElement('td');
	cell.className = "foot" + 3;
	cell.id = "foot" + 3;
	cell.colSpan = "10";
	cell.appendChild(document.createTextNode('蜀ICP备09001352号 客服：028-62560576'));
	row.appendChild(cell);
	footTable.appendChild(row);

	row = document.createElement('tr');
	cell = document.createElement('td');
	cell.className = "foot" + 4;
	cell.id = "foot" + 4;
	cell.colSpan = "10";
	cell.appendChild(document.createTextNode('青帝网 版权所有'));
	row.appendChild(cell);
	footTable.appendChild(row);

	$("#footContainer").append(footTable);
}