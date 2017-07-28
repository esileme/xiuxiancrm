package com.yl.crm.service.Impl;

import java.util.List;

import com.yl.crm.dao.DepartmentDao;
import com.yl.crm.dao.Impl.DepartmentDaoImp;
import com.yl.crm.model.Department;
import com.yl.crm.service.DepartmentService;

public class DepartmentServiceImp implements DepartmentService {
	private DepartmentDao departmentDao = new DepartmentDaoImp();

	@Override
	public List<Department> getAllDepartment() {

		return departmentDao.getAllDepartment();
	}

}
