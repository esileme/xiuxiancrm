package com.yl.crm.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yl.crm.model.Department;
import com.yl.crm.model.Role;
import com.yl.crm.model.User;
import com.yl.crm.service.DepartmentService;
import com.yl.crm.service.RoleService;
import com.yl.crm.service.UserService;
import com.yl.crm.service.Impl.DepartmentServiceImp;
import com.yl.crm.service.Impl.RoleServiceImp;
import com.yl.crm.service.Impl.UserServiceImpl;
import com.yl.crm.util.BeanUtil;
import com.yl.crm.util.SessionKey;

@WebServlet("/user/update")
public class UserUpdateServlet extends HttpServlet {
	private UserService userService = new UserServiceImpl();
	private DepartmentService departmentService = new DepartmentServiceImp();
	private RoleService roleService = new RoleServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("name");

		User user = userService.getUserByName(userName);
		List<Department> departments = departmentService.getAllDepartment();
		List<Role> roles = roleService.getAllRoles();

		req.setAttribute("roles", roles);
		req.setAttribute("departments", departments);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/view/user/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) BeanUtil.parseRequestToBean(req, User.class);
		Boolean isUpdate = userService.updateUser(user);

		if (isUpdate) {
			// req.getRequestDispatcher("/user/list").forward(req, resp);
			resp.sendRedirect(req.getContextPath() + "/user/list");
		} else {
			req.setAttribute("user", user);
			req.getRequestDispatcher("/WEB-INF/view/user/update.jsp").forward(req, resp);
		}

	}

}
