function createSidebarTable(domName, args) {
	var table = document.createElement("table");
	table.className = "infoTable";
	
	for(var i = 0; i < 3; i++) {
		row = document.createElement('tr');
		
		for ( var j = 0; j < 4; j++) {
			if (i == 1 && j == 0) {
				cell = document.createElement('td');
				cell.className = "sidebar" + i + j;
				cell.id = domName + "_sidebar" + i + j;
				cell.appendChild(document.createTextNode(i + '_' + j));
				cell.rowSpan = "2";
				row.appendChild(cell);
			}
			else if (i != 0 && j == 0) {
				continue;
			}
			else {
				cell = document.createElement('td');
				cell.className = "sidebar" + i + j;
				cell.id = domName + "_sidebar" + i + j;
/*				if (i == 0 && j == 0) {
					cell.innerHTML = "<h2>贷款产品</h2>";
				}
				else {*/
				cell.appendChild(document.createTextNode(i + '_' + j));
			}
			row.appendChild(cell);
//			}
		}
		table.appendChild(row);
	}
	
	$('#' + domName).append(table);
}

function createVerticallistTable(domName, args) {
	var table = document.createElement("table");
	table.className = "verticalTable";
	
	row = document.createElement('tr');
	
	for(var i = 0; i < 10; i++) {
		cell = document.createElement('td');
		cell.className = "verticallist" + i;
		cell.id = domName + "_verticallist" + i;
		cell.appendChild(document.createTextNode(i));
		row.appendChild(cell);
	}
	
	table.appendChild(row);
	$('#' + domName).append(table);
}