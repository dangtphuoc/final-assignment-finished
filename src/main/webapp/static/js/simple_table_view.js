function SimpleTableView() {
	this.$tag = $('<table>').addClass('table').addClass('table-hover');
	this.header = null;
	this.model = null;
}

SimpleTableView.prototype = new BaseView();

SimpleTableView.prototype.setHeader = function(header) {
	this.header = header;
	var $row = $('<tr>');
	var $thead = $('<thead>');
	$thead.append($row);
	for(var index in this.header) {
		var item = this.header[index];
		$row.append($('<th>').append(item));
	}
	this.$tag.append($thead);
};

SimpleTableView.prototype.setModel = function(model) {
	this.model = model;
	this.repaint();
};

SimpleTableView.prototype.repaint = function()  {
	var $tbody = $('<tbody>');
	this.$tag.append($tbody);
	for(var i in this.model) {
		var $row = $('<tr>');
		var r = this.model[i];
		for(var j in r) {
			var c = r[j];
			$row.append($('<td>').append(c));
		}
		$tbody.append($row);
	}
};