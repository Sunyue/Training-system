<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

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
	</tr>

</body>
</html>
