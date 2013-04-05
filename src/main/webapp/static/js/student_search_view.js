function StudentSearchView(parentView) {
	this.parentView = parentView;
	this.selectedStudents = new Object();
	this.$tag = $('<div>');
	var $form = $('<form class="form-horizontal">');
	var $content = $('<div class="edit_student_content_div">');
	$content.append($form);
	this.$tag.append($content);
	
	//student
	var $controlGroup = $('<div class="control-group">');
	var $controls = $('<div class="controls">');
	var $label = $('<label class="control-label" for="studentEditTitle">Find:</label>');
	this.$find =  $('<input>').attr({"type":"text", "placeholder":"Student Title"});
	var $searchButton = $('<button>').addClass("btn").attr({"type" : "button"}).text("Search");
	var self = this;
	$searchButton.click(function() {
		self.searchStudents();
	});
	$controls.append(this.$find);
	$controls.append($searchButton);
	$controlGroup.append($label);
	$controlGroup.append($controls);
	$form.append($controlGroup);
	
	this.$studentTable = $('<div>').addClass('student_table_div');
	this.$tag.append(this.$studentTable);
	
	this.searchStudents();
	
	return this;
}

StudentSearchView.prototype = new BaseView();

StudentSearchView.prototype.searchStudents = function() {
	var self = this;
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'students/search?key=' + this.$find.val(), 'GET', 'json', function(data){
		self.loadStudentTable(data);
	});
};

StudentSearchView.prototype.loadStudentTable = function(data) {
	var simpleTable = new SimpleTableView();
	var header = ['', 'Student Id', 'Firt Name', 'Last Name'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $checkBox = $('<input>').attr({"type" : "checkbox"});
		$checkBox.change({view: this, student: data[i]}, handleCheckboxChanged);
		var item = [$checkBox, data[i].id, data[i].firstName, data[i].lastName];
		model.push(item);
	}
	simpleTable.setModel(model);
	this.$studentTable.empty();
	this.$studentTable.append(simpleTable.getTag());
};

StudentSearchView.prototype.handleEnrollment = function(classOfferings) {
	var enrollData = {};
	var selectedStudent = new Array();
	for(var id in this.selectedStudents) {
		if(this.selectedStudents[id] != undefined) selectedStudent.push(this.selectedStudents[id]);
	}
	
	if(selectedStudent.length == 0) {
		Contact.addErrorMessage("There is no student selected");
	} else {
		
		enrollData.students = selectedStudent;
		enrollData.classOfferings = classOfferings;
		makeAjaxRequest(JSONConfig.getInstance().getRESTUrl() + '/courses/enroll', 'POST', 'json', function(data) {
			if(data.code == 0) {
				Contact.addMessage("Enrolled successfully.");
			} else {
				Contact.addErrorMessage("Enrolled unsuccessfully.");
			}
		}, JSON.stringify(enrollData));
	}
	
};

function handleCheckboxChanged(event) {
	var student = event.data.student;
	var view = event.data.view;
	if($(this).is(':checked')) {
		view.selectedStudents[student.id] = student;
	} else {
		view.selectedStudents[student.id] = undefined;
	}
}