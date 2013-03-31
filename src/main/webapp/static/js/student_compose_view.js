function StudentComposeView(isCreate) {
	this.isCreate = isCreate;
	this.$tag = $('<div>');
	var $form = $('<form class="form-horizontal">');
	var $content = $('<div class="edit_course_content_div">');
	$content.append($form);
	this.$tag.append($content);
	
	//first name
	var $controlGroup = $('<div class="control-group">');
	var $controls = $('<div class="controls">');
	var $label = $('<label class="control-label" for="courseEditTitle">First Name</label>');
	this.$firstName =  $('<input>').attr({"type":"text", "placeholder":"First Name"});
	$controls.append(this.$firstName);
	$controlGroup.append($label);
	$controlGroup.append($controls);
	$form.append($controlGroup);
	
	//last name
	$controls = $('<div class="controls">');
	$controlGroup = $('<div class="control-group">');
	$label = $('<label class="control-label" for="courseEditDescription">Last Name</label>');
	this.$lastName =  $('<input>').attr({"type":"text", "placeholder":"Last Name"});
	$controls.append(this.$lastName);
	$controlGroup.append($label);
	$controlGroup.append($controls);
	$form.append($controlGroup);
	
	//roles
	$controls = $('<div class="controls">');
	$controlGroup = $('<div class="control-group">');
	$label = $('<label class="control-label" for="courseEditDescription">Roles</label>');
	this.$roles =  $('<select multiple="multiple">');
	$controls.append(this.$roles);
	$controlGroup.append($label);
	$controlGroup.append($controls);
	$form.append($controlGroup);
	
	//manager
	$controls = $('<div class="controls">');
	$controlGroup = $('<div class="control-group">');
	$label = $('<label class="control-label" for="courseEditDescription">Manager</label>');
	this.$manager =  $('<select>');
	$controls.append(this.$manager);
	$controlGroup.append($label);
	$controlGroup.append($controls);
	$form.append($controlGroup);
	
	this.initializeRoles();
	this.initializeManagers();
}

StudentComposeView.prototype = new BaseView();

StudentComposeView.prototype.initializeRoles = function() {
	var self = this;
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + "roles", "GET", "json",
	function(data) {
		if(data != undefined) {
			for(var i in data) {
				self.$roles.append($('<option>').attr({'value' : data[i].id}).text(data[i].title));
			}
		}
	});
};
StudentComposeView.prototype.initializeManagers = function() {
	var self = this;
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + "students?filterRole=2", "GET", "json",
	function(data) {
		if(data != undefined) {
			for(var i in data) {
				self.$manager.append($('<option>').attr({'value' : data[i].id}).text(data[i].firstName + " " + data[i].lastName));
			}
		}
	});
};

StudentComposeView.prototype.repaint = function() {
	this.$firstName.val(this.model.firstName);
	this.$lastName.val(this.model.lastName);
};

StudentComposeView.prototype.saveChanges = function() {
	var title = this.$firstName.val();
	var description = this.$lastName.val();
	this.model.firstName = title;
	this.model.lastName = description;
	this.model.roles = [];
	var selectedRoles = this.$roles.val();
	if(selectedRoles != undefined) {
		for(var i in selectedRoles) {
			this.model.roles.push({id : selectedRoles[i]});
		}
	}
	
	//manager
	if(this.$manager.val() != undefined && this.$manager.val() != '') {
		this.model.manager = {id : this.$manager.val()};
	}
	var url = JSConfig.getInstance().getRESTUrl() + 'students/update';
	if(this.isCreate) {
		url = JSConfig.getInstance().getRESTUrl() + 'student';
	}
	makeAjaxRequest(url, "POST", "json",
	function (){
		EventManager.getInstance().notifyEvent(EventManager.STUDENT_CREATED);
	}, 
	undefined, JSON.stringify(this.model));
};