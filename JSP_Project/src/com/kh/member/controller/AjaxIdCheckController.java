package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implement bv    ation class AjaxIdCheckController
 */
@WebServlet("/idCheck.me")
public class AjaxIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxIdCheckController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자가 전달한 데이터 변수화
		String userId = request.getParameter("userId");
		// 2. DB에 현재 전달된 데이터가 존재하는지 확인
		boolean isId = new MemberService().isId(userId); 
		// 3. 중복된 아이디가 존재하는 케이스. 않는 케이스별로 데이터 전달
		JSONObject jobj = new JSONObject();
		jobj.put("isId", isId);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jobj);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
