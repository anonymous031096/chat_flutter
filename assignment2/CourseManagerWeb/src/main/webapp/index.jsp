<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" ng-app="app">
<head>
<title>Title</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="app.css" rel="stylesheet" />
<script src="js/lib/angular.min.js"></script>
<script src="js/lib/angular-ui-router.min.js"></script>
<script src="js/lib/localforage.min.js"></script>
<script src="js/lib/ngStorage.min.js"></script>
<script src="app.js"></script>
<script src="student/StudentService.js"></script>
<script src="student/StudentController.js"></script>
<script src="course/CourseService.js"></script>
<script src="course/CourseController.js"></script>
<!-- <script src="registration/RegistrationService.js"></script> -->
<!-- <script src="registration/RegistrationController.js"></script> -->
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">HOME</a></li>
			<li><a ui-sref="student">STUDENT</a></li>
			<li><a ui-sref="course">COURSE</a></li>
		</ul>
	</div>
	</nav>
	<div ui-view></div>
</body>
</html>