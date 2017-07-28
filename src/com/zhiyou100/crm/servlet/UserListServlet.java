package com.zhiyou100.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.UserService;
import com.zhiyou100.crm.service.Impl.UserServiceImpl;
import com.zhiyou100.crm.util.Pager;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("用户管理:doGet");
		// System.out.println(req.getMethod());
		String keyword = "";
		String field = "";
		String pageNo = "";
		int page = 1;

		if ("post".equalsIgnoreCase(req.getMethod())) {
			keyword = (String) req.getAttribute("keyword");
			field = (String) req.getAttribute("field");
			pageNo = (String) req.getAttribute("pageNo");
			if (!pageNo.equals("")) {
				page = Integer.parseInt(pageNo);
			}

		}

		// 给分页设置数据
		int total = userService.getTotalCount(keyword, field);
		Pager pager = new Pager(total, page);
		req.setAttribute("pager", pager);
		System.out.println(pager.toString());

		// 查询出所有的数据
		List<User> users = userService.getUserByKeywordFieldPager(keyword, field, pager);
		req.setAttribute("list", users);

		req.getRequestDispatcher("/WEB-INF/view/user/list.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("用户管理:doPost");
		String keyword = req.getParameter("keyword");
		String searchField = req.getParameter("searchField");
		String pageNo = req.getParameter("pageNo");
		System.out.println("关键字:" + keyword + "搜索区:" + searchField + "页码:" + pageNo);

		req.setAttribute("keyword", keyword);
		req.setAttribute("field", searchField);
		req.setAttribute("pageNo", pageNo);

		doGet(req, resp);

	}

}
