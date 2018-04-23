<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8"> 
	<title>Course Chain</title>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="page-header">
	<h1>
	   Course Chain
	</h1>
</div>

<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>
							Course
						</th>
						<th>
							Course name
						</th>
						<th>
							Description
						</th>
						<th>
							Edit
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courseList}" var="course">
						<tr>
							<td class="id">
								<c:out value="${course.courseId}" />
							</td>
							<td class="name">
								<c:out value="${course.courseName}" />
							</td>
							<td class="description">
								<c:out value="${course.description}" />
							</td>
							<td class="operate">
								<span class="btn btn-default">Edit</span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table> 
</div>

</body>
</html>
