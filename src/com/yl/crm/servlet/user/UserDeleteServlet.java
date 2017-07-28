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

@WebServlet("/user/remove")
public class UserDeleteServlet extends HttpServlet {
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mId = req.getParameter("id");
		Integer id = Integer.valueOf(mId);
		System.out.println("用户ID:" + mId);

		User user = userService.getUserById(id);

		Boolean isDel = userService.deleteUser(user);
		
		resp.sendRedirect(req.getContextPath() + "/user/list");


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
