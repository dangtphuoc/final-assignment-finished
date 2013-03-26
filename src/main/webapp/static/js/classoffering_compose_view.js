function ClassOfferingComposeView() {
	this.model = new Array();
	var self = this;
	this.$tag = $('<div>').addClass('classoffering_compose_view_div');
	
	this.$contentTable = $('<div>');
	this.$tag.append(this.$contentTable);
	var $composePanel = $('<div>').addClass('view-holder');
	this.startDate = new DateInput();
	this.endDate = new DateInput();
	this.$location = $('<select>').append($('<option value="1">').text('1'));
	this.$instructor = $('<select>').append($('<option value="1">').text('1'));
	this.$addButton = $('<button class="btn" type="button">Add</button>');
	
	this.$addButton.click(function(){
		self.addOffering();
	});
	return this;
}

ClassOfferingComposeView.prototype = new BaseView();

ClassOfferingComposeView.prototype.repaint = function() {
	var simpleTable = new SimpleTableView();
	var header = ['Start Date', 'End Date', 'Location', 'Instructor', '', ];
	simpleTable.setHeader(header);
	var data = this.model;
	var model = [];
	for(var i in data) {
		var item = [data[i].startTime.toString(), data[i].endTime.toString(), data[i].location.id, '', ''];
		model.push(item);
	}
	var item = [this.startDate.getTag(), this.endDate.getTag(), this.$location, this.$instructor, this.$addButton];
	model.push(item);
	simpleTable.setModel(model);
	this.$contentTable.empty();
	this.$contentTable.append(simpleTable.getTag());
};

ClassOfferingComposeView.prototype.addOffering = function() {
	var offering = {};
	offering.startTime = this.startDate.getSelectedDate();
	offering.endTime = this.endDate.getSelectedDate();
	offering.location = {id: this.$location.val()};
	//offering.instructor = {id: this.$instructor.val()};
	this.model.push(offering);
	
	this.repaint();
};
