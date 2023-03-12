<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.ArrayList, com.kh.model.vo.Person"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Functions Library</h1>
	
	<c:set var="str" value="How are you?"/>
	
	str : ${str } <br>
	문자열의 길이 ? : ${fn:length(str)}글자 입니다. <br>
	문자열의 길이 : ${ str.length() }글자 입니다. <br>

	<% 
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("김홍석0","남","학생0"));
		list.add(new Person("김홍석1","남","학생1"));
		list.add(new Person("김홍석2","남","학생2"));
		request.setAttribute("list", list);
	%>
	배열의 길이 : ${list.size() } <br>
	문자열을 대문자로 출력 : ${fn:toUpperCase(str)} <br>
	문자열을 모두 소문자로 출력 : ${fn:toLowerCase(str) }<br>
	
	are의 시작 인덱스 : ${fn:indexOf(str,'are') }<br>
	문자열을 변환 : ${fn:replace(str,"are","were") } <br>
	
	원본 str : ${str} <br>
	
	str에 "are"이라는 문자열이 포함되어있나? : ${fn:contains(str,"are") ? '포함되어있습니다' : '없습니다'} <br>
	
	문자열 앞, 뒤 공백 제거 ${fn:trim(str) }<br>
	문자열 잘라주기 : ${fn :substring(fn:trim(str),0,4) }<br>
	문자열 나누기 : 
	<c:forEach var="s" items="${fn:split(str,' ')}">
		${s} <br>
	</c:forEach>
	배열 합치기 : ${fn:join(fn:split(str,' '), ',') }
	
	<c:set var="htmlStr" value="<p>hi</p>"/>
	태그를 문자열로 바꿔주는 함수 : ${fn:escapeXml(htmlstr)} <br>
	${htmlStr}
	
</body>
</html>