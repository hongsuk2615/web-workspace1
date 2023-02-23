<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.board.model.vo.*" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
	 min-height: 800px; 
	}
	.list-area{
		width: 760px;
		margin : auto;
	}
</style>
</head>
<body>
	<%@include file="../common/menubar.jsp" %>
	<div class="outer">
		<br>
		<h2 style="text-align:center;">사진게시판</h2>
		<br>
		
		<% if(loginUser != null){ %>
			<div align="right" style="width:860px;">
				<a href="<%= contextPath%>/insert.th" class="btn btn-secondary">글작성</a>
				<br><br>
			</div>
		<% } %>
		<div class="list-area">
			<% if(list.isEmpty()) {%>
				작성사진이 없습니다.
			<% } %>
			<% for(Board b : list) { %>
			<div class="thumbnail" align="center">
				<input type="hidden" value="<%= b.getBoardNo()%>">
				<img src="<%=contextPath %><%= b.getTitleImg()%>" width="200px" height="150px">
				<p>
					<%= b.getBoardTitle() %> <br>
					조회수 : <%= b.getCount() %>
				</p>
			</div>
			<% } %>		
		
		</div>
	
	
	</div>
	<script>
		$(function(){
			$(".thumbnail").click(function(){
				location.href="<%=contextPath%>/detail.th?bno="+$(this).children().eq(0).val();	
			})			
		})
		
	
	</script>
</body>
</html>