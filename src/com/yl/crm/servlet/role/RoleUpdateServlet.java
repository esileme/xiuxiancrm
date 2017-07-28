package com.yl.crm.servlet.role;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.model.Role;
import com.yl.crm.service.RoleService;
import com.yl.crm.service.Impl.RoleServiceImp;

@WebServlet("/role/update")
public class RoleUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RoleService roleService = new RoleServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mId = req.getParameter("id");
		System.out.println(mId + "------------");
		Integer id = Integer.valueOf(mId);

		Role role = roleService.getRoleById(id);
		System.out.println(role.toString());

		req.setAttribute("role", role);
		req.getRequestDispatcher("/WEB-INF/view/role/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roleName = req.getParameter("roleName");
		String roleDesc = req.getParameter("roleDesc");
		String roleId = req.getParameter("roleId");
		Integer id = Integer.valueOf(roleId);

		Role role = new Role();
		role.setRoleId(id);
		role.setRoleName(roleName);
		role.setRoleDesc(roleDesc);
		role.setStatus(2);
		role.setUpdater(2);
		role.setUpdateTime(new Timestamp(System.currentTimeMillis()));

		boolean isUpdate = roleService.updateRole(role);

		if (isUpdate) {
			req.setAttribute("role", role);
			resp.sendRedirect(req.getContextPath() + "/role/list");
		} else {

			doGet(req, resp);
		}

	}

}
