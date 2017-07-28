package com.zhiyou100.crm.dao.Impl;

import java.util.List;

import com.zhiyou100.crm.dao.DepartmentDao;
import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.util.DBUtil;

public class DepartmentDaoImp implements DepartmentDao {

	@Override
	public List<Department> getAllDepartment() {

		String sql = "select * from department";
		Object[] paramters = {};
		List<Department> departments = (List<Department>) DBUtil.queryForList(sql, paramters, Department.class);
		return departments;
	}

	public static void main(String[] args) {
		DepartmentDao imp = new DepartmentDaoImp();

		List<Department> depts = imp.getAllDepartment();
		// System.out.println(depts.get(1).toString());
		System.out.println(depts.size());

	}

}
