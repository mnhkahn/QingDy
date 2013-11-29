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

var client;
function getClientInfo(o) {
    client = o;
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function () {
        client.country = remote_ip_info.country;
        client.city = remote_ip_info.city;
        client.isp = remote_ip_info.isp;
    });
    client.startDate = new Date();
    window.onbeforeunload = function () {
        client.endDate = new Date();
    }
    client.brower = platform.name + "_" + platform.version;
    client.resolution = screen.width + "*" + screen.height;

    if(platform.os.family.indexOf("Win") > -1) {
        client.os = "Win" + platform.os.version + "_" + platform.os.architecture + "bit";
    }
    client.fromSource = document.referrer;
}