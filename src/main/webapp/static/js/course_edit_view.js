function CourseEditView(isCreate) {
	this.isCreate = isCreate;
	this.$tag = $('<div>');
	this.$title =  $('<input>').attr({"type":"text", "placeholder":"Title"});
	this.$description =  $('<input>').attr({"type":"text", "placeholder":"Description"});
	var $form = $('<form class="form-horizontal">');
	var $controlGroup1 = $('<div class="control-group">');
	var $label1 = $('<label class="control-label" for="courseEditTitle">Course Title</label>');
	var $controls1 = $('<div class="controls">');
	var $controlGroup2 = $('<div class="control-group">');
	var $label2 = $('<label class="control-label" for="courseEditDescription">Course Description</label>');
	var $controls2 = $('<div class="controls">');
	var $content = $('<div class="edit_course_content_div">');
	$content.append($form);
	$form.append($controlGroup1);
	$form.append($controlGroup2);
	$controlGroup1.append($label1);
	$controlGroup1.append($controls1);
	$controlGroup2.append($label2);
	$controlGroup2.append($controls2);
	$controls1.append(this.$title);
	$controls2.append(this.$description);
	this.$tag.append($content);
	this.classOfferingView = new ClassOfferingComposeView();
	this.$tag.append(this.classOfferingView.getTag());
	
	return this;
}

CourseEditView.prototype = new BaseView();

CourseEditView.prototype.repaint = function() {
	this.$title.val(this.model.title);
	this.$description.val(this.model.description);
	this.classOfferingView.setModel(this.model.classOfferings);
};

CourseEditView.prototype.saveChanges = function() {
	var title = this.$title.val();
	var description = this.$description.val();
	this.model.title = title;
	this.model.description = description;
	this.model.classOfferings = this.classOfferingView.model;
	var url = '/courses';
	if(!this.isCreate) {
		url = '/courses/update';
	}
	makeAjaxRequest(url, "POST", "json",
			"cbSaveChanges", undefined, JSON.stringify(this.model));
};

function cbSaveChanges(data) {
	EventManager.getInstance().notifyEvent(EventManager.COURSE_CREATED);
}