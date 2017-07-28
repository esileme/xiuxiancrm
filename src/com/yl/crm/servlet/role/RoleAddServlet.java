package com.yl.crm.servlet.role;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.model.Role;
import com.yl.crm.service.RoleService;
import com.yl.crm.service.Impl.RoleServiceImp;
import com.yl.crm.util.BeanUtil;

@WebServlet("/role/add")
public class RoleAddServlet extends HttpServlet {
	private RoleService roleService = new RoleServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Role role = (Role) req.getAttribute("role");

		req.setAttribute("role", role);
		req.getRequestDispatcher("/WEB-INF/view/role/add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Role role = new Role();
		role.setRoleName(req.getParameter("roleName"));
		role.setRoleDesc(req.getParameter("roleDesc"));
		role.setStatus(2);
		role.setUpdater(2);
		role.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		role.setCreateTime(new Timestamp(System.currentTimeMillis()));

		boolean isAdd = roleService.addRole(role);
		req.setAttribute("role", role);

		if (!isAdd) {
			doGet(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/role/list");
		}

	}

}
