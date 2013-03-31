$(function() {
	
	initData();
	
	/**
	 * Binding event handler
	 */
	$('#btnAddCurriculum').click(function(){
		var curriculumView = new CurriculumComposeView(true);
		var curriculumModel = {};
		curriculumModel.courses = new Array();
		curriculumView.setModel(curriculumModel);
		var dialog = new ModalDialog('Curriculum View');
		dialog.setModel(curriculumView.getTag());
		dialog.setCallbackFunction(curriculumView, curriculumView.saveChanges);
		dialog.showDialog();
    });
	
	$('#btnSearch').click(function() {
		makeAjaxRequest('/curricula/search?key=' + $('#inSearchKey').val(), 'GET', 'JSON', "jsonCBCurricula");
	});
	
	/**
	 * Event registration
	 */
	EventManager.getInstance().registerHandler(EventManager.CURRICULUM_CREATED, loadCurriculumData);
});

function initData() {
	loadCurriculumData();
	
}
function loadCurriculumData() {
	makeAjaxRequest('/curricula', "GET", "json",
				"jsonCBCurricula", undefined, undefined);
}
function jsonCBCurricula(data) {
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
	var $curriculaContentDiv = $('#curricula');
	$curriculaContentDiv.empty();
	$curriculaContentDiv.append(simpleTable.getTag());
}

function editCurriculum(id) {
	makeAjaxRequest('/curricula/' + id, "GET", "json",
			"jsobCBEditCurriculum");
}
function jsonCBCurricula(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description', ''];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(function() {
			editCurriculum(data[i].id);
		});
		var $removeIcon = $('<i>').addClass('icon-remove-sign');
		$removeIcon.click(data[i], removeCurriculum);
		var item = [$link, data[i].title, data[i].description, $removeIcon];
		model.push(item);
	}
	simpleTable.setModel(model);
	var $courseDiv = $('#course_content');
	$courseDiv.empty();
	$courseDiv.append(simpleTable.getTag());
}

function jsobCBEditCurriculum(data) {
	var curriculumComposeView = new CurriculumComposeView();
	curriculumComposeView.setModel(data);
	var dialog = new ModalDialog("Curriculum View");
	dialog.setModel(curriculumComposeView.getTag());
	dialog.setCallbackFunction(curriculumComposeView, curriculumComposeView.saveChanges);
	dialog.showDialog();
}

function removeCurriculum(event) {
	var curriculum = event.data;
	makeAjaxRequest('/curricula/' + curriculum.id, 'DELETE', 'json', function() {
		loadCurriculumData();
	});
}