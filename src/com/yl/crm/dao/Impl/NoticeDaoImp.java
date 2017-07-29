package com.yl.crm.dao.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yl.crm.dao.NoticeDao;
import com.yl.crm.model.Notice;
import com.yl.crm.model.User;
import com.yl.crm.util.DBUtil;
import com.yl.crm.util.Pager;
import com.yl.crm.util.SessionKey;

public class NoticeDaoImp implements NoticeDao {

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalCount(String keyword, String searchField, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionKey.USER);
		int userId = user.getUserId();
		int departmentId = user.getDepartmentId();
		StringBuilder builder = new StringBuilder(
				"SELECT * FROM notice WHERE (expire_time > NOW() AND (receive_id =0 OR receive_id=? OR creater= ? ) AND status=2 )OR(expire_time <= NOW() AND creater=? AND status=2 )");

		if (keyword != null && !"".equals(keyword)) {
			String s1 = " and " + searchField + " like '%" + keyword + "%'";
			builder.append(s1);
		}

		String sql = builder.toString();
		Object[] paramters = { departmentId, userId, userId };
		List<Notice> notices = (List<Notice>) DBUtil.queryForList(sql, paramters, Notice.class);

		return notices.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> getAllNoticeByKeywordSearchFieldPageno(String keyword, String searchField, Pager pager,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionKey.USER);
		int userId = user.getUserId();
		int departmentId = user.getDepartmentId();
		StringBuilder builder = new StringBuilder(
				"SELECT * FROM notice WHERE (expire_time > NOW() AND (receive_id =0 OR receive_id=? OR creater= ? ) AND status=2 )OR(expire_time <= NOW() AND creater=? AND status=2 )");

		if (keyword != null && !"".equals(keyword)) {
			String s1 = " and " + searchField + " like '%" + keyword + "%'";
			builder.append(s1);
		}
		String limit = " limit " + (pager.getPageNo() - 1) * pager.getPageSize() + "," + pager.getPageSize();
		builder.append(limit);
		String sql = builder.toString();
		System.err.println(sql);

		Object[] paramters = { departmentId, userId, userId };
		List<Notice> notices = (List<Notice>) DBUtil.queryForList(sql, paramters, Notice.class);
		System.out.println("总共有:" + notices.size() + "条数据");
		return notices;
	}

}
