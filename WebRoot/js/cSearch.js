(function($) {
    $.fn.cSearch = function() {
        var searchboxContent = $("<div></div>");
        searchboxContent.addClass("searchboxContent");

        var divSearchTypeSelect = $("<div></div>");
        divSearchTypeSelect.addClass("divSearchTypeSelect");
        searchboxContent.append(divSearchTypeSelect);

        var selectType = $("<select><select>");
        selectType.addClass("selectType");
        selectType.append(new Option("贷款产品","product"));
        selectType.append(new Option("贷款需求","demand"));
        selectType.append(new Option("专家顾问","specialist"));
        selectType.append(new Option("贷款机构","mall"));
        selectType.append(new Option("贷款银行","bank"));

        divSearchTypeSelect.append(selectType);

        var divSearchbox = $("<div></div>");
        divSearchbox.addClass("searchbox");
        searchboxContent.append(divSearchbox);

        var divTextSearch = $("<div></div>");
        divTextSearch.addClass("divTextSearch");
        divSearchbox.append(divTextSearch);

        var textSearch = $("<input></input>");
        textSearch.addClass("textSearch");
//        textSearch.type = "text";
        divTextSearch.append(textSearch);

        var btnSearch = $("<button></button>");
        btnSearch.addClass("largeBtn");
        btnSearch.click(function() {
            var type = $('.selectType').val();
            var key = $('.textSearch').val();
            window.open("s.html?type=" + type + "&keyword=" + key ,'_newtab');
        });

        var spanIco = $("<span></span>");
        spanIco.addClass("smallcio");
        spanIco.addClass("searchico");
        btnSearch.append(spanIco);

        divSearchbox.append(btnSearch);

        this.append(searchboxContent);

        selectType.chosen();
    };
})(jQuery);
