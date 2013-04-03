$(function() {
	initData();
	$('#btnAddCourse').click(function(){
		var courseView = new CourseEditView(true);
		var courseModel = {};
		courseModel.classOfferings = [];
		courseView.setModel(courseModel);
		var dialog = new ModalDialog('Course View');
		dialog.setModel(courseView.getTag());
		dialog.setCallbackFunction(courseView, courseView.saveChanges);
		dialog.showDialog();
    });
	$('#btnAddLocation').click(function(){
		var locationAddView = new LocationAddView();
		locationAddView.setModel({});
		var dialog = new ModalDialog('Location View');
		dialog.setModel(locationAddView.getTag());
		dialog.setCallbackFunction(locationAddView, locationAddView.saveChanges);
		dialog.showDialog();
    });
	$('a[data-toggle="tab"]').on('shown', function (e) {
		  var target  = e.target; // activated tab
		  if(target.hash == "#home") {
			  loadCourseData();
		  } else if(target.hash == '#location') {
			  loadLocationData();
		  }
		});
	EventManager.getInstance().registerHandler(EventManager.COURSE_CREATED, loadCourseData);
	EventManager.getInstance().registerHandler(EventManager.COURSE_UPDATED, loadCourseData);
	EventManager.getInstance().registerHandler(EventManager.LOCATION_CREATED, loadLocationData);
	EventManager.getInstance().registerHandler(EventManager.LOCATION_UPDATED, loadLocationData);
});

function jsonCBLoadCourseData(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(data[i], editCourse);
		var item = [$link, data[i].title, data[i].description];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $courseDiv = $('#course_content');
	$courseDiv.empty();
	$courseDiv.append(simpleTable.getTag());
}
function editCourse(event) {
	var id = event.data.id;
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'courses/' + id, "GET", "json",
			"jsonCBEditCourse");
}
function jsonCBEditCourse(data) {
	var courseEditView = new CourseEditView();
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
				"jsonCBLoadCourseData", undefined, undefined);
}
function loadLocationData() {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'locations', "GET", "json",
			"jsonCBLoadLocationData", undefined, undefined);
}
function jsonCBLoadLocationData(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(data[i], editLocation);
		var item = [$link, data[i].title, data[i].description];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $locationContentDiv = $('#location_content');
	$locationContentDiv.empty();
	$locationContentDiv.append(simpleTable.getTag());
}
function editLocation(event) {
	var id = event.data.id;
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'locations/' + id, 'GET', 'json', 'jsonCBEditLocation');
}
function jsonCBEditLocation(data) {
	var locationAddView = new LocationAddView();
	locationAddView.setModel(data);
	var dialog = new ModalDialog('Location View');
	dialog.setModel(locationAddView.getTag());
	dialog.setCallbackFunction(locationAddView, locationAddView.saveChanges);
	dialog.showDialog();
}
$(function() {
    $("#navi-courses-link").addClass("active");
});
