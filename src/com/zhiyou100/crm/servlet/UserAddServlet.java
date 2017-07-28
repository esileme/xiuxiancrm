package com.zhiyou100.crm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.DepartmentService;
import com.zhiyou100.crm.service.RoleService;
import com.zhiyou100.crm.service.UserService;
import com.zhiyou100.crm.service.Impl.DepartmentServiceImp;
import com.zhiyou100.crm.service.Impl.RoleServiceImp;
import com.zhiyou100.crm.service.Impl.UserServiceImpl;
import com.zhiyou100.crm.util.BeanUtil;

@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {
	private DepartmentService departmentService = new DepartmentServiceImp();
	private RoleService roleService = new RoleServiceImp();
	private UserService userDao = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Department> departments = departmentService.getAllDepartment();
		List<Role> roles = roleService.getAllRoles();

		req.setAttribute("roles", roles);
		req.setAttribute("departments", departments);
		req.getRequestDispatcher("/WEB-INF/view/user/add.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) BeanUtil.parseRequestToBean(req, User.class);
		user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
		user.setIsSystem(Boolean.valueOf("0"));

		Boolean isAdd = userDao.addUser(user);
		if (!isAdd) {
			req.setAttribute("user", user);
			doGet(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/user/list");
		}

	}

}
