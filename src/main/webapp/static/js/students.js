/**
 * Global variables
 */

var startDate = undefined;
var endDate = undefined;

$(function() {
	
	initData();
	
	$('#btnAddStudent').click(function(){
		var studentView = new StudentComposeView();
		studentView.setModel({});
		var dialog = new ModalDialog('Student Compose View');
		dialog.setModel(studentView.getTag());
		dialog.setCallbackFunction(studentView, studentView.handleEnrollment);
		dialog.showDialog();
    });
	
	$('#btnSearch').click(function() {
		var searchKey = $('#inSearchKey').val();
		var url = JSConfig.getInstance().getRESTUrl() + 'classofferings/search' + '?key=' + searchKey;
		if(startDate.getSelectedDate() != "") url += moment(startDate.getSelectedDate()).format(JSConfig.SYSTEM_FORMAT_DATE);
		if(endDate.getSelectedDate() != "") url += moment(endDate.getSelectedDate().format(JSConfig.SYSTEM_FORMAT_DATE));
		makeAjaxRequest(url, 'GET', 'JSON', "jsonCBClassOfferings");
	});
	
	$('#btnEnroll').click(function() {
		var selectedClassOfferings = getSelectedClassOfferings();
		if(selectedClassOffering.length == 0) {
			Contact.addErrorMessage('There is no class offerings selected.');
		} else {
			var studentView = new StudentSearchView();
			studentView.setModel({});
			var dialog = new ModalDialog('Student Search');
			dialog.setModel(studentView.getTag());
			dialog.setCallbackFunction(studentView, studentView.saveChanges, selectedClassOfferings);
			dialog.showDialog();
		}
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
	
	jsonCBClassOfferings([]);
	
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

function jsonCBClassOfferings(data) {
	var simpleTable = new SimpleTableView();
	var header = ['', 'Course Title', 'Class Title', 'Description'];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $checkbox = $('<input>').attr({type: "checkbox"});
		$checkbox.click(data[i], handleClassOfferingCheckbox);
		var item = [$checkbox, data[i].course.title, data[i].title, data[i].description];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $searchNEnroll = $('#searchnenroll_content');
	$searchNEnroll.empty();
	$searchNEnroll.append(simpleTable.getTag());
}
function handleClassOfferingCheckbox(event) {
	var classOffering = event.data;
	classOffering.selected = classOffering.is(":checked");
}
$(function() {
    $("#navi-students-link").addClass("active");
});
