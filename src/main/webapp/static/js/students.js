/**
 * Global variables
 */

var startDate;
var endDate;

$(function() {
	
	initData();
	
	$('#btnAddStudent').click(function(){
		var studentView = new StudentComposeView();
		studentView.setModel({});
		var dialog = new ModalDialog('Student Compose View');
		dialog.setModel(studentView.getTag());
		dialog.setCallbackFunction(studentView, studentView.saveChanges);
		dialog.showDialog();
    });
	
	$('#btnSearch').click(function() {
		var searchKey = $('#inSearchKey').val();
		makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'courses/search' +
				'?key=' + searchKey +
				'&startDate=' + moment(startDate.getSelectedDate()).format('MM/DD/YYYY') +
				'&endDate=' + moment(endDate.getSelectedDate()).format('MM/DD/YYYY'), 'GET', 'JSON', "jsonCBCourses");
	});
	
	$('a[data-toggle="tab"]').on('shown', function (e) {
		  var target  = e.target; // activated tab
		  if(target.hash == "#students") {
			  loadStudentData();
		  } else if(target.hash == '#searchnenroll') {
		  }
		});
	EventManager.getInstance().registerHandler(EventManager.STUDENT_CREATED, loadStudentData);
	EventManager.getInstance().registerHandler(EventManager.STUDENT_UPDATED, loadStudentData);
});

function cbEditStudent(data) {
	var courseEditView = new StudentComposeView();
	courseEditView.setModel(data);
	var dialog = new ModalDialog();
	dialog.setModel(courseEditView.getTag());
	dialog.setCallbackFunction(courseEditView, courseEditView.saveChanges);
	dialog.showDialog();
}
function initData() {
	loadStudentData();
	
	//init search tab
	startDate = new DateInput("startDate");
	endDate = new DateInput("endDate");
	$('#startDateDiv').append(startDate.getTag());
	$('#endDateDiv').append(endDate.getTag());
	
	jsonCBCourses([]);
	
}
function loadStudentData() {
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'students', "GET", "json",
			"cbLoadStudentData", undefined, undefined);
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

function jsonCBCourses(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(function() {
			editCurriculum(data[i].id);
		});
		var item = [$link, data[i].title, data[i].description];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $searchNEnroll = $('#searchnenroll_content');
	$searchNEnroll.empty();
	$searchNEnroll.append(simpleTable.getTag());
}
$(function() {
    $("#navi-students-link").addClass("active");
});
