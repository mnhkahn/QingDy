(function ($) {
    var TheSplit1 = "*"        //一级选项与一级选项之间的分隔符
    var TheSplit2 = "|"        //一级选项与二级选项之间的分隔符
    var TheSplit3 = "^"        //二级选项与二级选项之间的分隔符
    var TheSplit4 = "@"        //选项文本与选项值的分隔符，可以省略[值为选项文本值]
    /*
     如:北京@010|东城@0101^西城@0102
     */
    var bankselecttext = "招商银行*中国农业银行";
    var clientselecttext = "不限*公务员/律师/教师*企业法人*个体工商户*公司职员*学生*自由职业者";

    var boss = "provinceSel";
    var underling = "citySel";

    $.cSelect = function (container, type, default_value) {
        //默认值，当选项值有相同时有bug。
        var allselecttextarr;

        switch (type) {
            case 'bank':
                allselecttextarr = bankselecttext.split(TheSplit1);
                Arraylength = allselecttextarr.length;
                break;
            case 'client':
                allselecttextarr = clientselecttext.split(TheSplit1);
                Arraylength = allselecttextarr.length;
                break;
        }

        var where = new Array(Arraylength);

        where[0] = new hw_comefrom("请选择@", "请选择@");

        for (var hwl = 0; hwl < Arraylength; hwl++) {
            eval(where[hwl + 1] = new hw_comefrom(allselecttextarr[hwl].split(TheSplit2)[0], allselecttextarr[hwl].split(TheSplit2)[1]))
        }
        init1(where, default_value);

/*        $("#" + boss).on("change", function() {
            hw_select(where);
            $("#" + underling).trigger("liszt:updated");
        });   */
    };

    var init1 = function (where, hwdefault_value) {
        with (document.getElementsByName(boss)[0]) {
            length = where.length;
            var hwm = 0
            for (hwk = 0; hwk < where.length; hwk++) {
                if (where[hwk].hwSelect_s1.indexOf(TheSplit4) != -1) {
                    options[hwk].text = where[hwk].hwSelect_s1.split(TheSplit4)[0];
                    options[hwk].value = where[hwk].hwSelect_s1.split(TheSplit4)[1];
                    if (hwdefault_value.indexOf(where[hwk].hwSelect_s1.split(TheSplit4)[1]) != -1) {
                        hwm = hwk
                    }
                }
                else {
                    options[hwk].text = where[hwk].hwSelect_s1;
                    options[hwk].value = where[hwk].hwSelect_s1;
                    if (hwdefault_value.indexOf(where[hwk].hwSelect_s1) != -1) {
                        hwm = hwk
                    }
                }
            }
            selectedIndex = hwm
        }
    }

    var init2 = function (hwdefault_value) {
        with (document.getElementsByName(boss)[0]) {
            length = where.length;
            var hwm = 0
            for (hwk = 0; hwk < where.length; hwk++) {
                if (where[hwk].hwSelect_s1.indexOf(TheSplit4) != -1) {
                    options[hwk].text = where[hwk].hwSelect_s1.split(TheSplit4)[0];
                    options[hwk].value = where[hwk].hwSelect_s1.split(TheSplit4)[1];
                    if (hwdefault_value.indexOf(where[hwk].hwSelect_s1.split(TheSplit4)[1]) != -1) {
                        hwm = hwk
                    }
                }
                else {
                    options[hwk].text = where[hwk].hwSelect_s1;
                    options[hwk].value = where[hwk].hwSelect_s1;
                    if (hwdefault_value.indexOf(where[hwk].hwSelect_s1) != -1) {
                        hwm = hwk
                    }
                }
            }
            selectedIndex = hwm
        }
        with (document.getElementsByName(underling)[0]) {
            var hwn = 0
            hwSelect_s13 = (where[hwm].hwSelect_s2).split(TheSplit3);
            length = hwSelect_s13.length;
            for (hwl = 0; hwl < length; hwl++) {
                if (hwSelect_s13[hwl].indexOf(TheSplit4) != -1) {
                    options[hwl].text = hwSelect_s13[hwl].split(TheSplit4)[0];
                    options[hwl].value = hwSelect_s13[hwl].split(TheSplit4)[1];
                    if (hwdefault_value.indexOf(hwSelect_s13[hwl].split(TheSplit4)[1]) != -1) {
                        hwn = hwl
                    }
                }
                else {
                    options[hwl].text = hwSelect_s13[hwl];
                    options[hwl].value = hwSelect_s13[hwl];
                    if (hwdefault_value.indexOf(hwSelect_s13[hwl]) != -1) {
                        hwn = hwl
                    }
                }
            }
            selectedIndex = hwn
        }
    }

    var hw_comefrom = function(hwSelect_s1,hwSelect_s2)
    {
        this.hwSelect_s1 = hwSelect_s1;
        this.hwSelect_s2 = hwSelect_s2;
    }

})(jQuery);