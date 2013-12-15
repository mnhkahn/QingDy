function GetUrlParms()
{
    var args=new Object();
    var query=location.search.substring(1);//获取查询串
    var pairs=query.split("&");//在逗号处断开
    for(var i = 0;i < pairs.length;i++)
    {
        var pos=pairs[i].indexOf('=');//查找name=value
        if(pos==-1)   continue;//如果没有找到就跳过
        var argname=pairs[i].substring(0,pos);//提取name
        var value=pairs[i].substring(pos+1);//提取value
        args[argname] = decodeURI(value);//存为属性
    }
    return args;
}

function timeFormat(time, type) {
    var date = new Date(Date.parse(time.replace(/-/g, "/")));
    switch (type) {
        case 1: // *分钟前
            var now = new Date();
            var diff = now - date;
            var hour = (date.getHours() < 10) ? "0" + date.getHours() : date.getHours();
            var min = (date.getMinutes() < 10) ? "0" + date.getMinutes() : date.getMinutes();
            if (diff > 172800000) {
                return (date.getMonth() + 1) + "月" + date.getDate() + "日";
            }
            else if (diff > 86400000 && diff <= 172800000) {
                return "前天" + hour + ":" + min;
            }
            else if (diff <= 86400000 && diff > 3600000) {
                return "今天" + hour + ":" + min;
            }
            else if (diff > 60000 && diff <= 3600000) {
                return Math.round(diff / 60000) + "分钟前";
            }
            else if (diff > 0 && diff <= 60000) {
                return Math.round(diff / 1000) + "秒前";
            }
            break;
    }
}

function refresh() {
    window.location.reload();
}

function load(url) {
    window.location.href = url;
}

var client;
function getClientInfo(o) {
    client = o;
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function () {
        client.country = remote_ip_info.country;
        client.city = remote_ip_info.city;
        client.isp = remote_ip_info.isp;
    });
    client.startDate = toJavaDate(new Date());
    window.onbeforeunload = function () {
        client.endDate = toJavaDate(new Date());
        var args = GetUrlParms();
        var id = args['id'];
        visit(getTyte(), Number(id));
    }
    client.brower = platform.name + "_" + platform.version;
    client.resolution = screen.width + "*" + screen.height;

    if(platform.os.family.indexOf("Win") > -1) {
        client.os = "Win" + platform.os.version + "_" + platform.os.architecture + "bit";
    }
    client.fromSource = document.referrer;
}

function toJavaDate(d) {
    // "2011-04-08T09:00:00"
    var localTime = d.getTime();
    var localOffset = d.getTimezoneOffset() * 60000; //获得当地时间偏移的毫秒数
    var utc = localTime + localOffset; //取GMT时间
    var offset = 8; //北京为东8区
    var beijing = utc + (3600000*offset);
    var date = new Date(beijing);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + "T" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}

function toJavaDay(d) {
    // "2011-04-08"
    var localTime = d.getTime();
    var localOffset = d.getTimezoneOffset() * 60000; //获得当地时间偏移的毫秒数
    var utc = localTime + localOffset; //取GMT时间
    var offset = 8; //北京为东8区
    var beijing = utc + (3600000*offset);
    var date = new Date(beijing);
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
}

function toJavaMD(d) {
    // "2011-04-08"
    var localTime = d.getTime();
    var localOffset = d.getTimezoneOffset() * 60000; //获得当地时间偏移的毫秒数
    var utc = localTime + localOffset; //取GMT时间
    var offset = 8; //北京为东8区
    var beijing = utc + (3600000*offset);
    var date = new Date(beijing);
    return (date.getMonth() + 1) + "-" + date.getDate();
}

//判断照片大小
function checkFormat(s) {
    var i = s.lastIndexOf(".");
    if(i < 0)
    {
		return false;
    }
    
    var var1 = s.substring(i+1);
    if(var1 == "jpg" || var1 == "png" || var1 == "gif")
	{
	   return true;
	}
    return false ;
}