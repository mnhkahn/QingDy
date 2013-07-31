function showSkin(id) {
}

function showAvatar(id) {

}

function showMallNav(id) {
	var arrNav = new Array("index", "supply", "mall", "specialist");
	var arrNavLabel = new Array("我的主页", "贷款产品", "成功案例", "企业资讯");
	var ulNav = document.createElement("ul");
	ulNav.id = "nav-ul-mall";
	ulNav.className = "nav-ul-mall";

	var i = 0;
	for (; i < arrNav.length; i++) 
	{
		var li = document.createElement("li");
		li.className = "page-nav-mall";
		if (i == id)
			li.className = "page-nav-mall selectedNav";
		if (i == arrNav.length - 1)
			li.className = "page-nav-mall lastNav";
		li.innerHTML = "<a class='page-anchor-link-mall' href='" + arrNav[i] + ".html'>" + arrNavLabel[i] +"</a>";    	
		ulNav.appendChild(li);
	}
	
	$('#mallNav').append(ulNav);
}
