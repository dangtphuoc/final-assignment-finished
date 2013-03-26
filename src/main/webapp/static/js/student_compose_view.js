function StudentComposeView(isCreate) {
	this.isCreate = isCreate;
	this.$tag = $('<div>');
	this.$firstName =  $('<input>').attr({"type":"text", "placeholder":"First Name"});
	this.$lastName =  $('<input>').attr({"type":"text", "placeholder":"Last Name"});
	var $form = $('<form class="form-horizontal">');
	var $controlGroup1 = $('<div class="control-group">');
	var $label1 = $('<label class="control-label" for="courseEditTitle">First Name</label>');
	var $controls1 = $('<div class="controls">');
	var $controlGroup2 = $('<div class="control-group">');
	var $label2 = $('<label class="control-label" for="courseEditDescription">Last Name</label>');
	var $controls2 = $('<div class="controls">');
	var $content = $('<div class="edit_course_content_div">');
	$content.append($form);
	$form.append($controlGroup1);
	$form.append($controlGroup2);
	$controlGroup1.append($label1);
	$controlGroup1.append($controls1);
	$controlGroup2.append($label2);
	$controlGroup2.append($controls2);
	$controls1.append(this.$firstName);
	$controls2.append(this.$lastName);
	this.$tag.append($content);
}

StudentComposeView.prototype = new BaseView();

StudentComposeView.prototype.repaint = function() {
	this.$firstName.val(this.model.firstName);
	this.$lastName.val(this.model.lastName);
};

StudentComposeView.prototype.saveChanges = function() {
	var title = this.$firstName.val();
	var description = this.$lastName.val();
	this.model.firstName = title;
	this.model.lastName = description;
	var url = '/students/update';
	if(this.isCreate) {
		url = '/student';
	}
	makeAjaxRequest(url, "POST", "json",
	function (){
		EventManager.getInstance().notifyEvent(EventManager.STUDENT_CREATED);
	}, 
	undefined, JSON.stringify(this.model));
};