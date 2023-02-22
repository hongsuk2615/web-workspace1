<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.board.model.vo.Board" %>
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
		height: 800px;
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
			
			<div class="thumbnail" align="center">
				<input type="hidden" value="1">
				<img src="<%=contextPath %>/resources/thumb_upfiles/animal2.gif" width="200px" height="150px">
				<p>
					No.1 첫번째 글 제목 <br>
					조회수 : 1
				</p>
			</div>
			<div class="thumbnail" align="center">
				<input type="hidden" value="1">
				<img src="<%=contextPath %>/resources/thumb_upfiles/animal1.gif" width="200px" height="150px">
				<p>
					No.2 두번째 글 제목 <br>
					조회수 : 1
				</p>
			</div>
			<div class="thumbnail" align="center">
				<input type="hidden" value="1">
				<img src="<%=contextPath %>/resources/thumb_upfiles/animal3.gif" width="200px" height="150px">
				<p>
					No.3 세번째 글 제목 <br>
					조회수 : 3
				</p>
			</div>
		
		
		</div>
	
	
	</div>
	<script>
		$(function(){
			$(".thumbnail").click(function(){
				location.href="<%=contextPath%>/detail.th?bno"+$(this).children().eq(0).val();	
			})			
		})
		
	
	</script>
</body>
</html>