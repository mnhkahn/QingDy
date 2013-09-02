function commonInit() {
	username = 'lender';
	var password = 'bryce';
	var ctop;
	
	$.ajax({
		type: "GET",
		dataType: "json",
		url: 'data/top.json',
		success: function(arr) {
			$.ajax({
				type: "GET",
				dataType: "json",
				url: '/rest/metadata/usertop/' + username,//'data/tests/usertop.json',
				contentType: "application/json; charset=utf-8",
//				data: '{"username": "' + username + '", "password":"' + password + '" }',//{'Username' : username, 'Password' : password},
				success: function(info) {
                    arr.info = new Object;
                    arr.info.uid = username;
                    arr.info.icon = info.avatar;
                    arr.info.name = info.name;
                    arr.info.message = info.message;
                    var link;
                    if (info.groupId == 2)
                        link = 'lender.html';
                    else if (info.groupId == 1)
                        link = 'loaner.html';
                    else if (info.groupId == 0)
                        link = 'admin.html';
                    arr.info.link = link;
                    ctop = cTop.create(arr);
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

    $("#searchboxContainer").cSearch();
	
	showFoot();
	
}

function autoScroll() {
    var url = window.location.toString();
    var anchor = url.substring(url.indexOf("#") + 1);
    $(document).scrollTop($("#" + anchor).offset().top);
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

function commonInitPlugin() {
    $('.textarea1').tah({
        moreSpace: 10,
        maxHeight: 600,
        animateDur: 200
    });
}