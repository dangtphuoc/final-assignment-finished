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
    <script type="text/javascript" src="<c:url value="/static/js/courses.js" />" ></script>
    <title></title>
</head>
<body>
<div class="bs-docs-example">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Courses</a></li>
			<li class=""><a href="#location" data-toggle="tab">Location</a></li>
			<li class=""><a href="#student" data-toggle="tab">Student</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Dropdown <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li class=""><a href="#dropdown1" data-toggle="tab">@fat</a></li>
					<li class=""><a href="#dropdown2" data-toggle="tab">@mdo</a></li>
				</ul></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active in" id="home">
				<div class="control-group">
					<div class="controls">
						<button type="button" class="btn btn-primary" id="btnAddCourse">Add</button>
						<button type="button" class="btn btn-primary" id="btnDeleteCourse">Delete</button>
					</div>
				</div>
				<div id="course_content">
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
			
			<div class="tab-pane fade" id="dropdown1">
				<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they
					sold out mcsweeney's organic lomo retro fanny pack lo-fi
					farm-to-table readymade. Messenger bag gentrify pitchfork tattooed
					craft beer, iphone skateboard locavore carles etsy salvia banksy
					hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify
					squid 8-bit cred pitchfork. Williamsburg banh mi whatever
					gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk
					vice blog. Scenester cred you probably haven't heard of them, vinyl
					craft beer blog stumptown. Pitchfork sustainable tofu synth
					chambray yr.</p>
			</div>
			<div class="tab-pane fade" id="dropdown2">
				<p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy
					art party before they sold out master cleanse gluten-free squid
					scenester freegan cosby sweater. Fanny pack portland seitan DIY,
					art party locavore wolf cliche high life echo park Austin. Cred
					vinyl keffiyeh DIY salvia PBR, banh mi before they sold out
					farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral,
					mustache readymade thundercats keffiyeh craft beer marfa ethical.
					Wolf salvia freegan, sartorial keffiyeh echo park vegan.</p>
			</div>
		</div>
	</div>
</body>
</html>