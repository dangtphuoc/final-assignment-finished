<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/static/js/course_edit_view.js" />" ></script>
    <script type="text/javascript" src="<c:url value="/static/js/classoffering_compose_view.js" />" ></script>
    <script type="text/javascript" src="<c:url value="/static/js/date_input.js" />" ></script>
    <script type="text/javascript" src="<c:url value="/static/js/location_add_view.js" />" ></script>
    <script type="text/javascript" src="<c:url value="/static/js/student_compose_view.js"/>" ></script>
    <script type="text/javascript" src="<c:url value="/static/js/students.js" />" ></script>
    <title></title>
</head>
<body>
<div class="bs-docs-example">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#students" data-toggle="tab">Students</a></li>
			<li class=""><a href="#searchnenroll" data-toggle="tab">Search & Enroll</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active in" id="students">
				<div class="pull-right">
					<button type="button" class="btn" id="btnAddStudent"><i class="icon-plus"></i> Add</button>
				</div>
				<div id='student_content' class='content_table_div'></div>
			</div>
			
			<div class="tab-pane fade" id="searchnenroll">
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="inKeySearch">Find: </label>
						<div class="controls">
							<input type="text" id="inSearchKey" placeholder="Enter key to search" />
							<button type="button" class="btn" id="btnSearch">Search</button>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inKeySearch">Start Date: </label>
						<div class="controls" id="startDateDiv">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inKeySearch">From Date: </label>
						<div class="controls" id="endDateDiv">
						</div>
					</div>
				</form>
				<div class="pull-right">
					<button type="button" class="btn" id="btnEnroll"><i class="icon-pencil"></i> Enroll</button>
				</div>
				<div id='searchnenroll_content' class='content_table_div'></div>
			</div>
			
		</div>
	</div>
</body>
</html>