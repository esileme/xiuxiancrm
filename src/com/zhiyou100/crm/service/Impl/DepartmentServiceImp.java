package com.zhiyou100.crm.service.Impl;

import java.util.List;

import com.zhiyou100.crm.dao.DepartmentDao;
import com.zhiyou100.crm.dao.Impl.DepartmentDaoImp;
import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.service.DepartmentService;

public class DepartmentServiceImp implements DepartmentService {
	private DepartmentDao departmentDao = new DepartmentDaoImp();

	@Override
	public List<Department> getAllDepartment() {

		return departmentDao.getAllDepartment();
	}

}
