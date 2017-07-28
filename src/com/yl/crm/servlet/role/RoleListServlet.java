package com.yl.crm.servlet.role;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.model.Role;
import com.yl.crm.service.RoleService;
import com.yl.crm.service.Impl.RoleServiceImp;

@WebServlet("/role/list")
public class RoleListServlet extends HttpServlet {
	private RoleService roleService = new RoleServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Role> roles = roleService.getAllRoles();

		req.setAttribute("list", roles);
		req.getRequestDispatcher("/WEB-INF/view/role/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
