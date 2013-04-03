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
		makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'curricula/search?key=' + $('#inSearchKey').val(), 'GET', 'JSON', "jsonCBCurricula");
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
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'curricula', "GET", "json",
				"jsonCBCurricula", undefined, undefined);
}

function editCurriculum(event) {
	var id = event.data.id;
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'curricula/' + id, "GET", "json",
			"jsobCBEditCurriculum");
}
function jsonCBCurricula(data) {
	var simpleTable = new SimpleTableView();
	var header = ['Id', 'Title', 'Description', ''];
	simpleTable.setHeader(header);
	var model = [];
	for(var i in data) {
		var $link = $('<a>').text(data[i].id);
		$link.click(data[i], editCurriculum);
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
	makeAjaxRequest(JSConfig.getInstance().getRESTUrl() + 'curricula/' + curriculum.id, 'DELETE', 'json', function() {
		loadCurriculumData();
	});
}

$(function() {
    $("#navi-curricula-link").addClass("active");
});
