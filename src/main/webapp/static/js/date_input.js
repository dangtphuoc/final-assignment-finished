function DateInput() {
	this.$tag = $('<input type="text" class="input-small" placeholder="Enter a date...">');
	var self = this;
	this.$tag.datepicker().on('changeDate', function(ev) {
		self.selectedDate = new Date(ev.date);
		self.$tag.datepicker('hide');});
	return this;
}

DateInput.prototype = new BaseView();

DateInput.prototype.getSelectedDate = function() {
	return this.selectedDate;
};
