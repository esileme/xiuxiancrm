package com.yl.crm.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.model.User;
import com.yl.crm.service.UserService;
import com.yl.crm.service.Impl.UserServiceImpl;

@WebServlet("/user/detail")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mId = request.getParameter("id");
		Integer id = Integer.valueOf(mId);

		UserService userService = new UserServiceImpl();
		User user = userService.getUserById(id);

		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/view/user/detail.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
