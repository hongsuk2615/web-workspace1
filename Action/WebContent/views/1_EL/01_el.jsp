<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.model.vo.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1. 기존방식대로 스크립틀릿과 표현식을 이요해서 각 영역에 담겨있는 값을 뽑아서 화면에 출력</h3>
	<%
		//requestScope에 담긴 값을 뽑아내기
		String classRoom = (String) request.getAttribute("classRoom");
		Person p = (Person) request.getAttribute("person");
		
		//sessionScope에 담긴 값을 뽑아내기
		String academy = (String) session.getAttribute("academy");
	%>
	
	<p>
		학원명 : <%= academy %> <br>
		강의장 : <%= classRoom %> <br>
		강사정보 : <%= p.getName() %>, <%=p.getGender() %> <br>
	</p>
	
	<h3>2. EL을 이용해서 보다 쉽게 Scope에 저장된 값들 출력하기 </h3>
	<p>
		EL은 getXXX를 통해 값을 빼올 필요없이 키값만 제시하면 바로 접근가능<br>
		내부적으로 해당 scope영역에 해당 키값의 벨류값을 가져올 수 있다.
		기본적으로 EL은 JSP내장객체를 구분하지 않고 자동으로 모든 객체에 키값을 검색해서 존재하는 경우 해당값을 가져옴
	</p>
	
	<p>
		학원명 : ${academy} <br>
		강의장 : ${classRoom}<br>
		강사정보 : ${person.name} , ${person.gender}<br>
		<!-- 
			teacher라는 키값에 접근했을때 벨류값이 Person 객체임.
			해당 Person객체의 각 필드에 담긴 값들을 출력하고자 한다면 키값.필드명으로 접근하면 됨
			내부적으로 해당 필드의 getter메서드를 찾아서 실행하는 구조(getter메서드가 없다면 실행이 안됨)
		 -->
		 <ul>
		 	<li>이름 : ${student.name} </li>
		 	<li>성별 : ${student.gender} </li>
		 	<li>직업 : ${student.job} </li>	 
		 </ul>
	</p>
	
	<hr>
	
	<h3> 3. EL사용시 내장객체들에 저장된 키값이 동일한 CASE</h3>
	scope값 : ${scope} <br>
	<!--
		EL은 공유범위가 제일 작은 Scope에서부터 키값을 검색해 나감.
		Page -> Request -> Session -> Application순
		현재 찾고있는 scope범위 안에서 내가 찾고자 하는 속성이 존재한다면 , 반복을 멈추고 해당속성의 속성값을 출력함	
		만약에 모든 영역에서 못찾을 경우 ? 빈화면이 출력됨
	--> 
	<h3> 4. 직접 Scope영역을 지정해서 접근하기</h3>
	<%
		//pageScope에 값을 담기
		pageContext.setAttribute("scope","page scope");
	%>
	pageScope에 담긴값 : ${scope} 또는 ${pageScope.scope } <br>
	requestScope에 담긴값 : ${requestScope.scope} <br>
	sessionScope에 담긴값 : ${sessionScope.scope} <br>
	applicationScope에 담긴값 : ${applicationScope.scope} <br>
	
	잘못된 접근시 (session의 classRoom) : ${sessionScope.classRoom } <br>
	=> 결과는 에러는 안나고, 아무것도 출력되지 않는다.
	
</body>
</html>