package com.yl.crm.servlet.dept;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yl.crm.dao.DepartmentDao;
import com.yl.crm.dao.Impl.DepartmentDaoImp;
import com.yl.crm.model.Department;

@WebServlet("/department/list")
public class DepartmentListServlet extends HttpServlet {
	private DepartmentDao departmentDao = new DepartmentDaoImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Department> departments = departmentDao.getAllDepartment();

		req.setAttribute("list", departments);
		req.getRequestDispatcher("/WEB-INF/view/department/list.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
