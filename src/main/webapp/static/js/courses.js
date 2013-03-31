$(function() {
	//init data
	initData();
	$('#btnAddCourse').click(function(){
		var courseView = new CourseEditView(true);
		var courseModel = {};
		courseModel.classOfferings = [];
		courseView.setModel(courseModel);
		var dialog = new ModalDialog();
		dialog.setModel(courseView.getTag());
		dialog.setCallbackFunction(courseView, courseView.saveChanges);
		dialog.showDialog();
    });
	$('#btnAddLocation').click(function(){
		var locationAddView = new LocationAddView();
		locationAddView.setModel({});
		var dialog = new ModalDialog();
		dialog.setModel(locationAddView.getTag());
		dialog.setCallbackFunction(locationAddView, locationAddView.saveChanges);
		dialog.showDialog();
    });
	$('#btnAddStudent').click(function(){
		var studentView = new StudentComposeView();
		studentView.setModel({});
		var dialog = new ModalDialog();
		dialog.setModel(studentView.getTag());
		dialog.setCallbackFunction(studentView, studentView.saveChanges);
		dialog.showDialog();
    });
	$('a[data-toggle="tab"]').on('shown', function (e) {
		  var target  = e.target; // activated tab
		  if(target.hash == "#home") {
			  loadCourseData();
		  } else if(target.hash == '#location') {
			  loadLocationData();
		  } else if(target.hash == '#student') {
			  loadStudentData();
		  }
		});
	EventManager.getInstance().registerHandler(EventManager.COURSE_CREATED, loadCourseData);
	EventManager.getInstance().registerHandler(EventManager.COURSE_UPDATED, loadCourseData);
	EventManager.getInstance().registerHandler(EventManager.STUDENT_CREATED, loadStudentData);
	EventManager.getInstance().registerHandler(EventManager.STUDENT_UPDATED, loadStudentData);
	EventManager.getInstance().registerHandler(EventManager.LOCATION_CREATED, loadLocationData);
	EventManager.getInstance().registerHandler(EventManager.LOCATION_UPDATED, loadLocationData);
});

function cbCourses(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(function() {
			editCourse(data[i].id);
		});
		var item = [$link, data[i].title, data[i].description];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $courseDiv = $('#course_content');
	$courseDiv.empty();
	$courseDiv.append(simpleTable.getTag());
}
function editCourse(id) {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'courses/' + id, "GET", "json",
			"cbEditCourse");
}
function cbEditCourse(data) {
	var courseEditView = new CourseEditView();
	courseEditView.setModel(data);
	var dialog = new ModalDialog();
	dialog.setModel(courseEditView.getTag());
	dialog.setCallbackFunction(courseEditView, courseEditView.saveChanges);
	dialog.showDialog();
}
function cbEditStudent(data) {
	var courseEditView = new StudentComposeView();
	courseEditView.setModel(data);
	var dialog = new ModalDialog();
	dialog.setModel(courseEditView.getTag());
	dialog.setCallbackFunction(courseEditView, courseEditView.saveChanges);
	dialog.showDialog();
}
function initData() {
	loadCourseData();
	loadLocationData();
	
}
function loadCourseData() {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'courses', "GET", "json",
				"cbCourses", undefined, undefined);
}
function loadLocationData() {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'locations', "GET", "json",
			"cbLoadLocationData", undefined, undefined);
}
function loadStudentData() {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'students', "GET", "json",
			"cbLoadStudentData", undefined, undefined);
}
function cbLoadLocationData(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(function() {
			editCourse(data[i].id);
		});
		var item = [$link, data[i].title, data[i].description];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $locationContentDiv = $('#location_content');
	$locationContentDiv.empty();
	$locationContentDiv.append(simpleTable.getTag());
}
function cbLoadStudentData(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'First Name', 'Last Name'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(function() {
			editStudent(data[i].id);
		});
		var item = [$link, data[i].firstName, data[i].lastName];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $studentContentDiv = $('#student_content');
	$studentContentDiv.empty();
	$studentContentDiv.append(simpleTable.getTag());
}
function editStudent(id) {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'students/' + id, "GET", "json",
			"cbEditStudent");
}

$(function() {
    $("#navi-courses-link").addClass("active");
});
