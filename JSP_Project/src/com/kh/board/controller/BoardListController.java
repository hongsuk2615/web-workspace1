package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 페이징 처리
		int listCount; // 현재 게시판의 총 게시글 갯수
		int currentPage; // 현재 페이지(사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수

		int maxPage; // 가장 마지막 페이지가 몇번 페이지인지(총페이지 수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수

		// listCount : 총 게시글의 갯수 --> Board테이블안에 저장되어있는 행의 갯수
		listCount = new BoardService().selectListCount(); // 125

		// currentPage : 현재페이지(사용자가 요청한 페이지)
		currentPage = Integer
				.parseInt(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage"));

		// pageLimit : 페이지 하단에 보여질 페이징 바의 페이지 최대 갯수(페이지 목록을 몇개단위로 출력할건지)
		pageLimit = 10;

		// boardLimit : 한 페이지에 보여질 게시글의 최대 개수
		boardLimit = 10;

		// maxPage : 가장 마지막 페이지가 몇번 페이지인지(총페이지 수)
		maxPage = (int) Math.ceil((double) listCount / boardLimit);

		// startPage : 페이지 하단에 보여질 페이징바의 시작수
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		// endPage : 페이지 하단에 보여질 페이징바의 끝 수
		endPage = (startPage + pageLimit - 1) > maxPage ? maxPage : (startPage + pageLimit - 1);
		// 다양한 게시판에서 공통적으로 사용할 vo객체이기때문에 common패키지에 만듬
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		// 현재 사용자가 요청한 페이지에 보여질 게시글 리스트 요청하기
		ArrayList<Board> list = new BoardService().selectList(pi);
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		System.out.println(pi);
		System.out.println(list);
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
