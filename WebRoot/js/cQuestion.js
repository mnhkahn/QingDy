(function ($) {
    var TheSplit1 = "*"        //一级选项与一级选项之间的分隔符
    var TheSplit2 = "|"        //一级选项与二级选项之间的分隔符
    var TheSplit3 = "^"        //二级选项与二级选项之间的分隔符
    var TheSplit4 = "@"        //选项文本与选项值的分隔符，可以省略[值为选项文本值]
    /*
     如:北京@010|东城@0101^西城@0102
     */
    var hwallselecttext = "个人贷款|创业贷款^消费贷款^助学贷款^其他信用贷款*房屋贷款|房屋按揭^房产抵押^二手房贷款^公积金贷款*汽车贷款|汽车消费贷款^汽车抵押/质押^二手车贷款*企业贷款|企业抵押贷款^企业信用贷款^个体户贷款*其他|贷款通用知识^民间借贷^小额贷款公司^贷款担保*青帝网帮助|贷款申请^青帝旺铺^其它问题";

    var boss = "questionClasses";
    var underling = "questionSubClasses";

    var hwallselecttextarr
    hwallselecttextarr = hwallselecttext.split(TheSplit1)
    hwArraylength = hwallselecttextarr.length;
    var hwwhere = new Array(hwArraylength);

    $.cQuestion = function (hwdefault_value) {
        //默认值，当选项值有相同时有bug。

        hwwhere[0] = new hw_comefrom("请选择@", "请选择@");

        for (var hwl = 0; hwl < hwArraylength; hwl++) {
            eval(hwwhere[hwl + 1] = new hw_comefrom(hwallselecttextarr[hwl].split(TheSplit2)[0], hwallselecttextarr[hwl].split(TheSplit2)[1]))
        }
        init(hwdefault_value);

        $("#" + boss).on("change", function() {
            hw_select(hwwhere);
            $("#" + underling).trigger("liszt:updated");
        });
    };

    var init = function (hwdefault_value) {
        with (document.getElementsByName(boss)[0]) {
            length = hwwhere.length;
            var hwm = 0
            for (hwk = 0; hwk < hwwhere.length; hwk++) {
                if (hwwhere[hwk].hwSelect_s1.indexOf(TheSplit4) != -1) {
                    options[hwk].text = hwwhere[hwk].hwSelect_s1.split(TheSplit4)[0];
                    options[hwk].value = hwwhere[hwk].hwSelect_s1.split(TheSplit4)[1];
                    if (hwdefault_value.indexOf(hwwhere[hwk].hwSelect_s1.split(TheSplit4)[1]) != -1) {
                        hwm = hwk
                    }
                }
                else {
                    options[hwk].text = hwwhere[hwk].hwSelect_s1;
                    options[hwk].value = hwwhere[hwk].hwSelect_s1;
                    if (hwdefault_value.indexOf(hwwhere[hwk].hwSelect_s1) != -1) {
                        hwm = hwk
                    }
                }
            }
            selectedIndex = hwm
        }
        with (document.getElementsByName(underling)[0]) {
            var hwn = 0
            hwSelect_s13 = (hwwhere[hwm].hwSelect_s2).split(TheSplit3);
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

    var hw_select = function()
    {
        with(document.getElementsByName(boss)[0])
        {
            var hwSelect_s12 = options[selectedIndex].value;
        }
        for(hwi = 0;hwi < hwwhere.length;hwi ++)
        {
            if (hwwhere[hwi].hwSelect_s1.indexOf(TheSplit4)!=-1)
            {
                var hwThisV = hwwhere[hwi].hwSelect_s1.split(TheSplit4)[1]
            }
            else
            {
                var hwThisV = hwwhere[hwi].hwSelect_s1
            }
            if (hwThisV == hwSelect_s12)
            {
                hwSelect_s13 = (hwwhere[hwi].hwSelect_s2).split(TheSplit3);
                for(hwj = 0;hwj < hwSelect_s13.length;hwj++)
                {
                    with(document.getElementsByName(underling)[0])
                    {
                        length = hwSelect_s13.length;
                        if (hwSelect_s13[hwj].indexOf(TheSplit4)!=-1)
                        {
                            options[hwj].text = hwSelect_s13[hwj].split(TheSplit4)[0]
                            options[hwj].value = hwSelect_s13[hwj].split(TheSplit4)[1]
                        }
                        else
                        {
                            options[hwj].text = hwSelect_s13[hwj];
                            options[hwj].value = hwSelect_s13[hwj];
                        }
                        var hwSelect_s14=options[selectedIndex].value;
                    }
                }
                break;
            }
        }
    }

    var hw_comefrom = function(hwSelect_s1,hwSelect_s2)
    {
        this.hwSelect_s1 = hwSelect_s1;
        this.hwSelect_s2 = hwSelect_s2;
    }

})(jQuery);