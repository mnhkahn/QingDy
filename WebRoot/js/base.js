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

var ANSWER = 1;
var BLOG = 2;
var EVALUATE = 3;
var LOAN = 4;
var MALL = 5;
var PRODUCT = 6;
var QUESTION = 7;
var TRANSACTION = 8;
var NEWS = 9;
var USER = 10;
var SPECIALIST = 11;

var NEWS1 = 101;
var NEWS2 = 102;
var NEWS3 = 103;
var NEWS4 = 104;
var NEWS5 = 105;
var NEWS6 = 106;
var NEWS7 = 107;

var DEFAULT_SIZE = 20;
var DEFAULT_PAGE = 1;

function getLink(type, id) {
    switch (type) {
        case ANSWER:
            return "";
            break;
        case BLOG:
            return "p.html?id=" + id;
            break;
        case EVALUATE:
            return "";
            break;
        case LOAN:
            return "d.html?id=" + id;
            break;
        case MALL:
            return "m.html?id=" + id;
            break;
        case NEWS:
            return "p1.html?id=" + id;
            break;
        case PRODUCT:
            return "m.html?tab=1&id=" + id;
            break;
        case QUESTION:
            return "q.html?id=" + id;
            break;
        case TRANSACTION:
            return "m.html?tab=2&id=" + id;
            break;
        case SPECIALIST:
            return "m.html?tab=0&id=" + id;
            break;
        case NEWS1:
        case NEWS2:
        case NEWS3:
        case NEWS4:
        case NEWS5:
        case NEWS6:
        case NEWS7:
            return "p1.html?id=" + id;
            break;
    }
}

// 过滤掉img标签
function getBrief(str) {
    return str.replace(/<(script)[\S\s]*?\1>|<\/?(a|img)[^>]*>/gi, "");
}

function getFrontCover(str) {
    var re = "<img.*src=(.*?)[^>]*?>";
    var url = str.match(re);
    if (url == null) {
        return DEFAULT_FRONT_COVER;
    }
    url = url[0].match("((http|ftp|https)://)(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\&%_\./-~-]*)?")
    return url[0];
}

function getName(type) {
    switch (type) {
        case ANSWER:
            return "";
            break;
        case BLOG:
            return "贷款资讯";
            break;
        case EVALUATE:
            return "";
            break;
        case LOAN:
            return "d.html?id=" + id;
            break;
        case MALL:
            return "m.html?id=" + id;
            break;
        case NEWS:
            return "重要新闻";
            break;
        case PRODUCT:
            return "m.html?tab=1&id=" + id;
            break;
        case QUESTION:
            return "易贷问答";
            break;
        case TRANSACTION:
            return "m.html?tab=2&id=" + id;
            break;

        case NEWS1:
            return "小额贷款";
            break;
        case NEWS2:
            return "无抵押贷款";
            break;
        case NEWS3:
            return "消费贷款";
            break;
        case NEWS4:
            return "求学贷款";
            break;
        case NEWS5:
            return "银行贷款";
            break;
        case NEWS6:
            return "房产贷款";
            break;
        case NEWS7:
            return "创业贷款";
            break;
    }
}

function getTitle(type) {
    switch (type) {
        case ANSWER:
            return "回答了问题";
            break;
        case BLOG:
            return "发布了日志";
            break;
        case EVALUATE:
            return "评价了";
            break;
        case LOAN:
            return "发布了贷款需求";
            break;
        case MALL:
            return "创建了店铺";
            break;
        case PRODUCT:
            return "发布了产品";
            break;
        case QUESTION:
            return "提出了问题";
            break;
        case TRANSACTION:
            return "成功贷款";
            break;
    }
}

var DEFAULT_FRONT_COVER = "/avatar/avatar.png";

function getObject(type, id) {
    var baseURL = "/rest/metadata/";
    var obj;
    switch (type) {
        case ANSWER:
            baseURL += "answer/" + id;
            break;
        case BLOG:
            baseURL += "blog/" + id;
            break;
        case LOAN:
            baseURL += "loan/" + id;
            break;
        case MALL:
            baseURL += "mall/" + id;
            break;
        case PRODUCT:
            baseURL += "product/" + id;
            break;
        case QUESTION:
            baseURL += "question/" + id;
            break;
        case NEWS:
            baseURL += "news/" + id;
            break;
    }
    $.ajax({
        url: baseURL,
        type: 'GET',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (response) {
            obj = response;
        },
        error: function(response) {
            console.warn(response);
        }
    });
    return obj;
}

function getFavouriteCount(type, id) {
    var baseURL = "/rest/metadata/favourite/type/" + type + "/id/" + id;
    var count = 0;

    $.ajax({
        url: baseURL,
        type: 'HEAD',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data, textStatus, jqXHR) {
            count = jqXHR.getResponseHeader("count");
        },
        error: function(response) {
            console.warn(response);
        }
    });
    return count;
}