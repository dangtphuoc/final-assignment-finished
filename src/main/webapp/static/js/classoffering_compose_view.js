function ClassOfferingComposeView() {
	this.model = new Array();
	var self = this;
	this.$tag = $('<div>').addClass('classoffering_compose_view_div');
	
	this.$contentTable = $('<div>');
	this.$tag.append(this.$contentTable);
	this.startDate = new DateInput();
	this.endDate = new DateInput();
	this.$location = $('<select>');
	this.$instructor = $('<select>');
	this.$addButton = $('<button class="btn" type="button">Add</button>');
	
	this.$addButton.click(function(){
		self.addOffering();
	});
	
	this.initializeLocations();
	this.initializeInstructors();
	return this;
}

ClassOfferingComposeView.prototype = new BaseView();

ClassOfferingComposeView.prototype.initializeLocations = function() {
	var self = this;
	makeAjaxRequest("/locations", "GET", "json",
	function(data) {
		if(data != undefined) {
			for(var i in data) {
				self.$location.append($('<option>').attr({'value' : data[i].id}).text(data[i].title));
			}
		}
	});
};

ClassOfferingComposeView.prototype.initializeInstructors = function() {
	var self = this;
	makeAjaxRequest("/students?filterRole=3", "GET", "json",
	function(data) {
		if(data != undefined) {
			for(var i in data) {
				self.$instructor.append($('<option>').attr({'value' : data[i].id}).text(data[i].firstName + " " + data[i].lastName));
			}
		}
	});
};

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
	if(this.$location.val() != undefined && this.$location.val() != "") {
		offering.location = {id: this.$location.val()};
	}
	if(this.$instructor.val() != undefined && this.$instructor.val() != "") {
		offering.instructor = {id: this.$instructor.val()};
	}
	this.model.push(offering);
	
	this.repaint();
};
