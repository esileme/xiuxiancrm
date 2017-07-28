package com.yl.crm.servlet.role;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.model.Role;
import com.yl.crm.model.User;
import com.yl.crm.service.RoleService;
import com.yl.crm.service.Impl.RoleServiceImp;

@WebServlet("/role/remove")
public class RoleDeleteServlet extends HttpServlet {
	private RoleService roleService = new RoleServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mId = req.getParameter("id");
		Integer id = Integer.valueOf(mId);
		System.out.println("用户ID:" + mId);

		Role role = roleService.getRoleById(id);
		roleService.deleteRole(role);

		resp.sendRedirect(req.getContextPath() + "/role/list");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
