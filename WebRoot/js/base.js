function commonInit(type, keyword) {
	var ctop;
	var username = "lender1"
	$.ajax({
		type: "GET",
		dataType: "json",
		url: '/data/top.json',
		success: function(arr) {
			if (username) {
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
			}
			else {
				arr.info = new Object;
				arr.info.name = "";
				arr.info.link = "login.html";
				ctop = cTop.create(arr);
			}

		},
		error: function(response) {
			console.error(response);
		}
	});

    $("#searchboxContainer").cSearch(type, keyword);

    $("#footContainer").load("/js/templates/foot.html", function() {

    })
//	showFoot();
}

function autoScroll() {
    var url = window.location.toString();
	if (url.indexOf("#") > 0 ) {
		var anchor = url.substring(url.indexOf("#") + 1);
		$(document).scrollTop($("#" + anchor).offset().top);
	}
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

//    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
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

var DEFAULT_AREA = "广东广州";
var DEFAULT_SEL = "不限";
var DEFAULT_INTRO = "这个人很懒，什么都没有留下";
var ONE_WEEK_IN_SECOND = 60 * 60 * 24 * 7

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

function getProperty(object, type) {
    var property = new Object;
    switch (type) {
        case SPECIALIST:
            property.title = object.user.lastname + object.user.firstname + "(积分：" + object.scores + ")";
            property.link = getLink(SPECIALIST, object.user.id);
            property.content = "擅长领域：" + object.specialty;
            break;
        case BLOG:
            return "贷款资讯";
            break;
        case EVALUATE:
            return "";
            break;
        case LOAN:
            property.title = object.title;
            property.link = getLink(LOAN, object.id);
            property.content = object.content;
            break;
        case MALL:
            property.title = object.cName;
            property.link = getLink(MALL, object.id);
            property.content = object.content;
            break;
        case NEWS:
            return "重要新闻";
            break;
        case PRODUCT:
            property.title = object.pName;
            property.link = getLink(PRODUCT, object.id);
            property.content = object.content;
            break;
        case QUESTION:
            property.title = object.title;
            property.link = getLink(QUESTION, object.id);
            property.content = object.content;
            break;
        case TRANSACTION:
            return "m.html?tab=2&id=" + id;
            break;
        default:
            property.title = object.name;
            break;
    }
    return property;
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

function getTyte() {
    var url = window.location.href;
    if (url.indexOf("m.html?tab=1") > -1) {
        return PRODUCT;
    }
    else if (url.indexOf("m.html") > -1) {
        return MALL;
    }
    else if (url.indexOf("d.html") > -1) {
        return LOAN;
    }
    else if (url.indexOf("p.html") > -1) {
        return BLOG
    }
    else if (url.indexOf("p1.html") > -1) {
        return NEWS;
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

function addFavourite(type, oid, username) {
    if ($("#isFav_" + oid).html() == "收藏") {
        $.ajax({
            url: "/rest/metadata/favourite",
            type: 'POST',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: '{"poster": {"username": "' + username + '"}, "title": "' + document.title + '", "link": "' + window.location.href + '", "oid": ' + oid + ', "type": ' + type + '}',
            success: function (data, textStatus, jqXHR) {
                $.jBox.confirm("收藏成功", "消息", refresh);
            },
            error: function(response, textStatus, jqXHR) {
                if (jqXHR == "Conflict") {
                    $.jBox.confirm("请勿重复收藏", "消息", refresh);
                    //jBox.info("请勿重复收藏");
                    //refresh();
                }
                else {
                    $.jBox.confirm("收藏失败", "消息", refresh);
                   //jBox.info("收藏失败");
                    //refresh();
                }
                console.warn(response);
            }
        });
    }
    // unfavourite
    else {
        $.ajax({
            url: "/rest/metadata/favourite/id/" + oid + "/type/" + type + "/username/" + username,
            type: 'DELETE',
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function (data, textStatus, jqXHR) {
                if (jqXHR == "Moved Permanently") {
                    $.jBox.confirm("取消收藏成功", "消息", refresh);
                    // jBox.info("取消收藏成功");
                    //  refresh();
                }
                else
                    $.jBox.confirm("取消收藏失败", "消息", refresh);
            },
            error: function(response, textStatus, jqXHR) {
                if (jqXHR == "Moved Permanently") {
                    $.jBox.confirm("取消收藏成功", "消息", refresh);
                   // jBox.info("取消收藏成功");
                 //  refresh();
                }
                else
                    $.jBox.confirm("取消收藏失败", "消息", refresh);
              //      jBox.info("取消收藏失败");
                console.warn(response);
            }
        });
    }
}

function isFavourite(type, oid, username) {
    $.ajax({
        url: "http://localhost:8080/rest/metadata/favourite/type/" + type + "/id/" + oid + "/username/" + username,
        type: "HEAD",
        success: function(response, textStatus, jqXHR) {
            if (jqXHR.getResponseHeader("is") == "true") {
                $("#isFav_" + oid).html("取消收藏");
            }
            else {
                $("#isFav_" + oid).html("收藏");
            }
        },
        error: function(response, textStatus, jqXHR) {
            console.warn(response);
        }
    });
}

function visit(type, id) {
    var url = "/rest/metadata/visit/";
    var json = '{"oid":' + id + ',"type":' + type + ', "user":{"username":"' + username + '"},"ip":"' + client.host +'","startDate":"' + client.startDate + '", "endDate": "' + client.endDate + '","city":"' + client.city + '","browser":"' + client.brower + '","resolution":"' + client.resolution + '","os":"' + client.os + '","fromSource":"' + client.fromSource + '","isp":"' + client.isp + '"}';
    $.ajax({
        url: url,
        type: 'POST',
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (data, textStatus, jqXHR) {
            console.debug(data);
        },
        error: function(response) {
            console.debug(response);
        }
    });
}

function contact(container, username) {
    $.get("js/templates/contact.html", function(template) {
        $.template("contactTmpl", template);
        $.ajax({
            url: "/rest/metadata/userdetail/" + username,//"data/tests/contact.json",
            type: "GET",
            dataType: "json",
            success: function(response) {
                $.tmpl("contactTmpl", response).appendTo(container);
            },
            error: function(response) {
                console.warn(response);
            }
        });
    });
}

var reply = function (receiver) {
    if ($("#Dialog")) {
        $("#Dialog").remove();
    }
    var dialog = $('<div id="Dialog"></div>');
    dialog.load('/js/templates/messageStation1.html', function () {
        dialog.dialog({
            width:'auto',
            modal: true,
            title: "站内消息",
            show: 'fade',
            hide: 'fade'
        });
        var receiverInput = $("#tipInput");
        receiverInput.val(receiver);
        $("#submit").on("click", function () {

            var title = $("#title");
            var content = $("#content");
            sendMessage(username, receiver, title.val(), content.val(),
                function() {
                    dialog.dialog( "destroy" );
                }
            );
        });
    });

}

function getFilter(key, opts) {
    var i = 0;
    if (key) {
        for (; i < opts.length; i++) {
            if (opts[i].value == key) {

                break;
            }
        }
    }
    return i;
}

function getFilterCount(field, value, type) {
    var url = "/rest/metadata/";
    switch (type) {
        case MALL:
            url += "mall";
            break;
    }
    url += "/count";
    if (value != DEFAULT_SEL) {
        url += "?searchField=" + field + "&searchString=" + value;
    }
    console.debug(url);

    var count = 0;
    $.ajax({
        url: url,
        type: 'HEAD',
        dataType: "json",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus, jqXHR) {
            count = parseInt(jqXHR.getResponseHeader("count"));
        },
        error: function(response) {
            console.warn(response);
        }
    })
    return count;
}

function setURLFilter(oldValue, value) {
    var url = window.location.href;
    if (oldValue && oldValue != "" && oldValue != value) {
        url = url.replace(encodeURI(oldValue), encodeURI(value));
        window.location.href = url;
    }
}

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Year', 'Sales', 'Expenses'],
        ['2004', 1000, 400],
        ['2005', 1170, 460],
        ['2006', 660, 1120],
        ['2007', 1030, 540]
    ]);

    var options = {
        title: 'Company Performance',
        hAxis: {title: 'Year', titleTextStyle: {color: 'red'}}
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function getTypeStr(type) {
    switch (type) {
        case ANSWER:
            return "";
            break;
        case BLOG:
            return "blog";
            break;
        case EVALUATE:
            return "";
            break;
        case LOAN:
            return "loan";
            break;
        case MALL:
            return "mall";
            break;
        case NEWS:
            return "news";
            break;
        case PRODUCT:
            return "product";
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

function getVisits(type, id) {
    var url = "/rest/metadata/visit/" + id + "/" + type + "/count";
    $.ajax({
        url: url,
        type: "HEAD",
        success: function(response, status, xhr) {
            var count = xhr.getResponseHeader("count");
            console.debug(count);
            $("#" + getTypeStr(type) + "_" + id).html(count);
        },
        error: function(response) {
            console.warn(response);
        }
    });
}

function getVisitsByDate(type, id, start, end) {
    // http://localhost:8080/rest/metadata/visit/2/2/count/date?start=2010-10-01&end=2014-12-31
    var url = "/rest/metadata/visit/" + id + "/" + type + "/count/date?start=" + start + "&end=" + end;
    var value;
    $.ajax({
        url: url,
        type: "GET",
        async: false,
        success: function(response) {
            value = response;
        },
        error: function(response) {
            console.warn(response);
        }
    });
    return value;
}

function getCount(type) {
    var url = "/rest/metadata/" + getTypeStr(type) + "/nkeys/count";
    console.debug(url);
    var count = 0;
    $.ajax({
        url: url,
        type: "HEAD",
        async: false,
        success: function(response, status, xhr) {
            count = xhr.getResponseHeader("count");
        },
        error: function(response) {
            count = 0
            console.warn(response);
        }
    });
    console.debug(count);
    return count;
}

function getProduct(id) {
	var product = new Object;
	$.ajax({
		url: "/rest/metadata/product/" + id,
		type: "GET",
		async: false,
		dataType: "json",
		success: function(response) {
			product = response
		},
		error: function(response) {
			console.debug(response);
		}
	});
	return product;
}

function deleteProduct(id) {
	$.jBox.confirm("确认要删除？", "警告", function() {
		$.ajax({
		url: "/rest/metadata/product/" + id,
		type: "DELETE",
		success: function(response) {
			$.jBox.confirm("删除成功", "提示", function() {
				refresh();
			});
		},
		error: function(response) {
			console.debug(response);
		}
	});
	});
}

function editProduct(id) {
	showCreateProduct(getProduct(id));
}

function trsnactionProduct(id) {
	$.jBox.confirm("确认要删除？", "警告", function() {
		$.ajax({
		url: "/rest/metadata/transaction/" + id,
		type: "DELETE",
		success: function(response) {
			$.jBox.confirm("删除成功", "提示", function() {
				refresh();
			});
		},
		error: function(response) {
			console.debug(response);
		}
	});
	});
}

function isUserExists(username) {
	console.debug(username);
	$.ajax({
		url: "/rest/metadata/user/" + username + "/exists",
		type: "HEAD",
		success: function(response, textStatus, jqXHR) {
			if ("true" == jqXHR.getResponseHeader("exist"))
				return true;
			else
				return false;
		},
		error: function(response, textStatus, jqXHR) {
			console.debug(response, textStatus, jqXHR);
		}
	});
}

function deleteBlog(id) {
	$.jBox.confirm("确认要删除？", "警告", function() {
		$.ajax({
			url: "/rest/metadata/blog/" + id,
			type: "DELETE",
			success: function(response) {
				$.jBox.confirm("删除成功", "提示", function() {
					refresh();
				});
			},
			error: function(response) {
				console.debug(response);
			}
		});
	});
}

function getBlog(id) {
	var blog = new Object;
	$.ajax({
		url: "/rest/metadata/blog/" + id,
		type: "GET",
		async: false,
		dataType: "json",
		success: function(response) {
			blog = response
		},
		error: function(response) {
			console.debug(response);
		}
	});
	return blog;
}

function deleteLoan(id) {
	$.jBox.confirm("确认要删除？", "警告", function() {
		$.ajax({
			url: "/rest/metadata/loan/" + id,
			type: "DELETE",
			success: function(response) {
				$.jBox.confirm("删除成功", "提示", function() {
					refresh();
				});
			},
			error: function(response) {
				console.debug(response);
			}
		});
	});
}

function getLoan(id) {
	var loan = new Object;
	$.ajax({
		url: "/rest/metadata/loan/" + id,
		type: "GET",
		async: false,
		dataType: "json",
		success: function(response) {
			loan = response
		},
		error: function(response) {
			console.debug(response);
		}
	});
	return loan;
}

function editLoan(id) {
	showCreateLoan(getLoan(id));
}

function logout() {
alert("logout")
	$.ajax({
		url: "/rest/metadata/user/logout",
		type: "POST",
		success: function(response) {
			window.location.href = "/"
		},
		error: function(response) {
			console.debug(response);
		}

	});
}