package com.yl.crm.servlet.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.model.Notice;
import com.yl.crm.service.NoticeService;
import com.yl.crm.service.Impl.NoticeServiceImp;
import com.yl.crm.util.Pager;

@WebServlet("/notice/list")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNo = 1;
		String keyword = "";
		String searchField = "";

		if ("post".equalsIgnoreCase(request.getMethod())) {
			keyword = request.getParameter("keyword");
			searchField = request.getParameter("searchField");
			String pageNo1 = request.getParameter("pageNo");
			if (pageNo1 != null && !"".equals(pageNo1)) {
				pageNo = Integer.parseInt(pageNo1);
			}
			System.out.println("关键字:" + keyword);
			System.out.println("搜索域:" + searchField);
		}


		// 给分页设置数据
		int total = noticeService.getTotalCount(keyword, searchField, request);
		//int total = notices.size();
		Pager pager = new Pager(total, pageNo);

		System.out.println(pager.toString());

		List<Notice> notices = noticeService.getAllNoticeByKeywordSearchFieldPageno(keyword, searchField, pager,
				request);
		
		

		request.setAttribute("list", notices);
		request.setAttribute("keyword", keyword);
		request.setAttribute("field", searchField);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("公告管理:doPost");
		String keyword = request.getParameter("keyword");
		String searchField = request.getParameter("searchField");
		String pageNo = request.getParameter("pageNo");
		System.out.println("关键字:" + keyword + "搜索区:" + searchField + "页码:" + pageNo);

		request.setAttribute("keyword", keyword);
		request.setAttribute("field", searchField);
		request.setAttribute("pageNo", pageNo);

		doGet(request, response);

	}

}
