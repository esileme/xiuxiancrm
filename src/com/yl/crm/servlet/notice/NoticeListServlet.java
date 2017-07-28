package com.yl.crm.servlet.notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/list")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String searchField = request.getParameter("searchField");
		int pageNo = 1;

		request.setAttribute("list", "");
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchField", searchField);
		request.setAttribute("pageNo", pageNo);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
