<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


    


	<div align="center">
		<h3>
			<i>Login</i>
		</h3>
		<hr>
		<form:form action="process-login" method="post">
		<div style="color: red">
			<c:if test="${param.error !=null}">
			 
			    ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
<%-- 			   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  --%>
			  
	           
			</c:if>
	</div>

			<label for="em">Email :</label>
			<input id="em" type="text" name="customEmail">
			<br>
			<label for="pa">Password :</label>
			<input id="pa" type="password" name="customPassword">
			<br>
			<input type="submit" value="submit">

		</form:form>
	</div>




</body>
</html>