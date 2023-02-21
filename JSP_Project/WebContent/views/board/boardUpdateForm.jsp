<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.*" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#update-form>table{ border : 1px solid white;}
	#update-form input, #update-form textarea {
		width: 100%;
		box-sizing : border-box;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	
	<div class="outer">
		<br>
		<h2 align = "center">일반게시판 수정하기</h2>
		<br>
		
		<form action="<%= contextPath%>/update.bo" id="update-form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bno" value="<%= b.getBoardNo() %>">
			<table align="center">
				<tr>
					<th width="100">카테고리</th>
					<td width="500">
						<select name="category">
							<% for(Category c:list) { %>
								<option value="<%= c.getCategoryNo()%>"><%=c.getCategoryName() %></option>
							<% } %>
							<!-- 내가 선택한 카테고리가 자동으로 선택되어있도록 작업해주기(js) -->
						</select>
						<script>
							$(function(){
								$('select[name="category"]>option').each(function(){
									if(this.innerText == '<%= b.getCategory()%>'){
										this.selected = true;
									}
								})
							})
								
												
						</script>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" required value="<%= b.getBoardTitle() %>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="10" name="content" required><%= b.getBoardContent() %></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<% if(at != null) {%>
							<%= at.getOriginName() %>
							<!-- 원본파일의 파일번호, 수정명을  hidden과 함께 전송할 예정 -->
							<input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
							<input type="hidden" name="changeFileName" value="<%= at.getChangeName()%>">
					    <% } %>
					
					
					
					<input type="file" name="upfile"></td>
				</tr>
			</table>
			<br>
			
			<div align="center">
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>
</body>
</html>