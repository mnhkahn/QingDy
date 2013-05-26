// store Windows positon
var position = new Array();
var windowWidth = 0;

function showSlide(container, arr) {
	var margin_left = 10;
	var margin_top = 10;

	var container = $('#' + container);
	container.css("position", "relative");
	container.css("overflow", "hidden");

	windowWidth = container.width();

	var leftArrow = $('<div></div>');
	leftArrow.addClass("leftArrow");
	leftArrow.attr("id", "leftArrow");
	leftArrow.css("position", "absolute");
	leftArrow.css("width", "23px")
	leftArrow.css("height", "46px");
	leftArrow.css("left", "0");
	leftArrow.css("top", (container.height() - leftArrow.height()) / 2);
	leftArrow.css("z-index", "5");
	leftArrow.css("background-image", "url(/images/arrowLeft.png)");
	leftArrow.click(function(){move(true, 10, 0)});

	container.append(leftArrow);

	var slideWrapper = $('<div></div>');
	slideWrapper.addClass("slideWrapper");
	var slideWrapper = $('<div></div>');
	slideWrapper.addClass("slideWrapper");
	slideWrapper.attr('id', "slideWrapper" + container);
	slideWrapper.css("position", "relative");
	slideWrapper.css("width", windowWidth - margin_left * 2);
	slideWrapper.css("height", container.height() - margin_top * 2);
	slideWrapper.css("margin", "0 auto");
	slideWrapper.css("overflow", "hidden");
	slideWrapper.css("margin-left", margin_left);
	slideWrapper.css("margin-top", margin_top);
	slideWrapper.css("-webkit-box-shadow", "0 2px 4px rgba(0,0,0,0.2)");
	
	var lastPos = 0 - windowWidth;
	for(i in arr) {
		position[i] = lastPos + windowWidth;
		lastPos = position[i];
	}
	for(i in arr) {
		var Windows = $("<div></div>");
		Windows.addClass("Windows");
		Windows.css("overflow", "hidden");
		Windows.css("position", "absolute");
		Windows.css("width", slideWrapper.width());
		Windows.css("height", slideWrapper.height());
		Windows.css("left", position[i]);
		Windows.attr("id", "Windows" + i);
		Windows.html("<img class='slideImg' style='overflow:hidden;' src='" + arr[i].link + "'></img>");

		slideWrapper.append(Windows);
	}
	
	container.append(slideWrapper);
	
	var rightArrow = $('<div></div>');
	rightArrow.addClass("rightArrow");
	rightArrow.attr("id", "rightArrow");
	rightArrow.css("position", "absolute");
	rightArrow.css("width", "23px")
	rightArrow.css("height", "46px");
	rightArrow.css("right", 0);
	rightArrow.css("top", (container.height() - rightArrow.height()) / 2);
	rightArrow.css("z-index", "5");
	rightArrow.css("background-image", "url(/images/arrowRight.png)");
	rightArrow.click(function(){move(false, 10, 0)});

	container.append(rightArrow);
	
	// slide action by timer
	var changeTimer = window.setInterval(function(){move(false, 10, 0)}, 5000);
}

// isPrevious: true move previously, false move next
function move(isPrevious, steps, times) {
	window.setTimeout(
		function() {
			times++;
			for (i in position) {
				var flagPrevious = isPrevious ? 1 : -1;
				position[i] += flagPrevious * windowWidth / steps;
				$('#Windows' + i).css("left", position[i]);
			}

			// reset position
			for (i in position) {
				if (position[i] <= -(position.length - 1) * windowWidth) {
					position[i] += position.length * windowWidth;
				}
				if (position[i] >= (position.length - 1) * windowWidth) {
					position[i] -= position.length * windowWidth;
				}
			}
			console.debug(position);

			if (times < steps) {
				move(isPrevious, steps, times);
			}
		},
		50
	);
}

function slide() {
//	window.setInterval(
//		function() {
			for (i in position) {
				position[i] -= windowWidth;
				$('#Windows' + i).css("left", position[i]);
			}
				console.debug(position);
//		},
//		100
//	);
}
