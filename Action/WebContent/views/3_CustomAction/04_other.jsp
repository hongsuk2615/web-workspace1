<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>c:import</h3>
	<p>
		페이지에서 다른 페이지를 불러오는 기능<br>
		var : 페이지를 저장하는 변수명<br>
		url : 불러올 페이지의 위치<br>
	</p>
	
	<c:import  url="http://localhost:8081/action/views/3_CustomAction/jstl.jsp" var="header"/>
	${header} 
	<%-- <c:out value="${header }" escapeXml="false"/> --%>
	<h3> c:redirect태그 이용하기</h3>
<%-- 	<c:redirect url="https://search.naver.com/search.naver">
		<c:param name="query">코로나</c:param>
	</c:redirect> --%>
	
	<h4>c:catch태그 이용해보기</h4>
	<%
		String test = null;
		request.setAttribute("test",test);
	
	%>
	<c:catch var="e">
		<%= test.charAt(0) %>
	</c:catch>
	${e }
	
	<jsp:useBean id="p" class="com.kh.model.vo.Person" scope="request"/>
	
	<jsp:setProperty name="p" property="name" value="김홍석"/><br>
	<jsp:setProperty name="p" property="gender" value="남"/><br>
	<jsp:setProperty name="p" property="job" value="요리사"/><br>
	
	이름 : <jsp:getProperty name="p" property="name"/> <br>
	성별 : <jsp:getProperty name="p" property="gender"/><br>
	직업 : ${p.job } 
</body>
</html>