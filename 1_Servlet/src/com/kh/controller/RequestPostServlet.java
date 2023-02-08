package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("잘 실행되나?");
		// 요청시 전달된 값들은 request parameter영역에 담겨있음
		// post방식 요청같은 경우는 값을 뽑기전에 반드시 UTF-8방식으로 인코딩 설정 해야함(POST방식의 기본값 ISO-8859-1)
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name"); // 텍스트박스가 비어있는 경우 빈 문자열("")로 넘어온다.
		String gender = request.getParameter("gender"); // 라디오 버튼의 경우 체크된 값이 없을때 빈 문자열이 아닌 null이 넘어간다.
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		//체크박스처럼 복수개의 정보를 받을때는 배열형태로 받아야함.
		String[] foods = request.getParameterValues("food"); //체크한 값이 없다면 null이 넘어간다.
		 
		// 요청 처리 : Service -> DAO - SQL문
		// 위의 요청처리를 다 했다는 가정하에 사용자가 보게될 응답페이지 출력
		// 순수 Servlet 방식 : Java소스코드내에 HTML을 작성
		// JSP(Java Server Page) : html내에 Java코드를 쓸수 있는 기술
		
		// 응답페이지를 만드는 과정을 jsp에게 위임
		
		// 단, 그 응답화면(jsp)에서 필요로 하는 데이터들을 request객체에 담아서 보내줘야함.
		// request의 attribute영역에 담아서 보내줄 예정(key,value세트로)
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("foods", foods);
		
		// 위임시 필요한 객체 : RequestDispatcher
		// 1) 응답하고자 하는 뷰(jsp)를 선택하면서 생성
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp"); // //자동으로 붙여져있음
		// 2) 포워딩
		view.forward(request, response);
		
		
		
		doGet(request, response);
	}

}
