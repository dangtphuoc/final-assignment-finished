<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/static/js/course_edit_view.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/classoffering_compose_view.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/date_input.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/location_add_view.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/student_compose_view.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/course_search_view.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/curriculum_compose_view.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/curricula.js" />"></script>
    <title></title>
</head>
<body>
<div class="bs-docs-example">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#curricula" data-toggle="tab">Curricula</a></li>
			<li class=""><a href="#location" data-toggle="tab">Location</a></li>
			<li class=""><a href="#student" data-toggle="tab">Student</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active in" id="curricula">
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="inKeySearch">Find: </label>
						<div class="controls">
							<input type="text" id="inSearchKey" placeholder="Enter key to search" />
							<button type="button" class="btn" id="btnSearch">Search</button>
						</div>
					</div>
				</form>
				<div class="pull-right">
					<button type="button" class="btn" id="btnAddCurriculum"><i class="icon-plus"></i> Add Curriculum</button>
				</div>
				<div id="course_content" class="content_table_div">
				</div>
			</div>
			
			<div class="tab-pane fade" id="location">
				<div class="control-group">
					<div class="controls">
						<button type="button" class="btn btn-primary" id="btnAddLocation">Add</button>
						<button type="button" class="btn btn-primary" id="btnDeleteLocation">Delete</button>
					</div>
					<div id='location_content'></div>
				</div>
			</div>
			
			<div class="tab-pane fade" id="student">
				<div class="control-group">
					<div class="controls">
						<button type="button" class="btn btn-primary" id="btnAddStudent">Add</button>
						<button type="button" class="btn btn-primary" id="btnDeleteStudent">Delete</button>
					</div>
					<div id='student_content'></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>