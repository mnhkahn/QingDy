function commonInit() {
	var username = 'bryce';
	var password = 'bryce';
	var ctop;
	
	$.ajax({
		type: "GET",
		dataType: "json",
		url: 'data/top.json',
		success: function(arr) {
			$.ajax({
				type: "POST",
				dataType: "json",
				url: '/rest/metadata/members/login',
				contentType: "application/json; charset=utf-8",
				data: '{"username": "' + username + '", "password":"' + password + '" }',//{'Username' : username, 'Password' : password},
				success: function(info) {
					$.ajax({
						type: "GET",
						dataType: "json",
						url: '/rest/metadata/messages/' + info.uid + '/unread',
						success: function(message) {
							arr.info = new Object;
							arr.info.icon = info.icon;
							arr.info.name = info.lastname + info.firstname;
							arr.info.message = message;
							var link;
							if (info.groupid == 2)
								link = 'lender.html';
							else if (info.groupid == 1)
								link = 'loaner.html';
							else if (info.groupid == 0)
								link = 'admin.html';
							arr.info.link = link;
							ctop = cTop.create(arr);
						}
					});			
				},
				error: function(response) {
					console.error(response);
				}
			});
			
		},
		error: function(response) {
			console.error(response);
		}
	});
	
	showSearchBox();
	
	showFoot();
	
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
