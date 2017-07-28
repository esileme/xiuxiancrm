package com.yl.crm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yl.crm.model.User;
import com.yl.crm.service.UserService;
import com.yl.crm.service.Impl.UserServiceImpl;
import com.yl.crm.util.SessionKey;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserService userService = new UserServiceImpl();

	// 去掉了所有没必要的代码及注释
	// 主要讲页面跳转。
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名:" + username + "密码:" + password);

		User user = userService.getUserByName(username);
		System.out.println(user);

		if (user == null) {
			System.out.println("用户名不存在或不正确");
			request.setAttribute("errorMessage", "用户名不存在或不正确");
			request.setAttribute("username", username);
			request.setAttribute("password", password);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			if (!password.equals(user.getPassword())) {
				System.out.println("密码不正确");
				request.setAttribute("errorMessage", "密码不正确");
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			} else {
				System.out.println("登陆成功");
				HttpSession session = request.getSession();
				session.setAttribute(SessionKey.USERNAME, user.getUsername());
				session.setAttribute(SessionKey.USER, user);

				response.sendRedirect(request.getContextPath() + "/admin.jsp");

			}
		}

	}

}
