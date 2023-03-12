<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1> 1. formatNumber</h1>
	<p>
		 숫자데이터 포맷(형식) 지정
		 - 표현하고자 하는 숫자데이터의 형식을 통화기호, %등 원하는 쓰임에 맞게 지정하는 태그<br><br>
		 
		 &lt;fmt:formatNumber value="출력할값" groupingUsed="true/false" type="percent/currency" currencySymbol="$" /gt;<br>
		 
	</p>
	
	<c:set var="num1" value="123456789"/>
	<c:set var="num2" value="0.75"/>
	<c:set var="num3" value="50000"/>
	일반 출력시 : ${num1 } <br>
	세자리마다 구분해서 출력하고 싶으면 : <fmt:formatNumber value="${num1}"/><br>
	숫자를 그대로 출력하고 싶으면 <fmt:formatNumber value="${num1 }" groupingUsed="false"/><br>
	<!-- 
		3자리수마다, 가 찍히게하고 싶으면 groupingUsed 속성을  true로 부여하면됨(기본값 true, 생략가능)
	 -->
	 percent(%) : <fmt:formatNumber value="${num2}" type="percent"/> <br>
	 
	 currency : <fmt:formatNumber value="${num3}" type="currency"/> <br>
	 <!-- 
	 	currency : 통화(돈) 단위로 보여짐. 현재 내 컴퓨터의 로컬정보에 따라 통화기호가 결정됨
	 	(groupingUsed가 true 설정되어있기 때문에 3자리마다 ,도 찍혀있음)
	  -->
	  currency $ : <fmt:formatNumber value="${num3 }" type="currency" currencySymbol="$" /><br>
	  
	  <h3>2. formatDate</h3>
	  <p>
	  	날짜 및 시간 데이터의 포맷 형식을 지정<br>
	  	단, java.util.Date객체를 사용해야함.
	  </p>
	  
	  <c:set var="current" value="<%= new java.util.Date() %>"/>
	  
	  단순 출력시 : ${current}
	  
	  <ul>
	  	<li>
	  		현재 날짜 : <fmt:formatDate value="${current}" type="date"/>
	  	</li>
	  	<li>
	  		현재 시간 : <fmt:formatDate value="${current }" type="time"/>
	  	</li>
	  	
	  	<li>
	  		현재날짜 및 시간 : <fmt:formatDate value="${current }" type="both"/>
	  	</li>
	  	<li>
	  		medium : <fmt:formatDate value="${current}" type="both" dataStyle="medium" timeStyle="medium"/>	  		
	  	</li>
	  	<li>
	  		long : <fmt:formatDate value="${current}" type="both" dataStyle="long" timeStyle="long"/>	  		
	  	</li>
	  	<li>
	  		short : <fmt:formatDate value="${current}" type="both" dataStyle="short" timeStyle="short"/>	  		
	  	</li>
	  	<li>
	  		full : <fmt:formatDate value="${current}" type="both" dataStyle="full" timeStyle="full"/>	  		
	  	</li>
	  	<li>
	  		customizing : <fmt:formatDate value="${current}" type="both" pattern="yyyy-MM-dd(E) a HH:mm:ss"/>	  		
	  	</li>
	  	<!-- 
	  		yyyy: 년도
	  		MM : 월
	  		dd : 일
	  		E  : 요일
	  		a  : 오전/오후
	  		HH : 시
	  		mm : 분
	  		ss : 초
	  	
	  	
	  	 -->
	  </ul>
</body>
</html>