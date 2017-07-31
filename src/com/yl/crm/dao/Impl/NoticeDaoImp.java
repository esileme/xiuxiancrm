package com.yl.crm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		StringBuilder builder = new StringBuilder();
		//builder.append("select a.*,user.username as creater_name from user right join ");
		
		builder.append("SELECT * FROM notice WHERE (expire_time > NOW() AND (receive_id =0 OR receive_id=? OR creater= ? ) AND status=2 )OR(expire_time <= NOW() AND creater=? AND status=2 )");

		if (keyword != null && !"".equals(keyword)) {
			String s1 = " and " + searchField + " like '%" + keyword + "%'";
			builder.append(s1);
		}
		String limit = " limit " + (pager.getPageNo() - 1) * pager.getPageSize() + "," + pager.getPageSize();
		builder.append(limit);
		
		//builder.append(" )a on (a.creater = user.user_id)");
		
		
		
		String sql = builder.toString();
		System.err.println(sql);

		Object[] paramters = { departmentId, userId, userId };
		List<Notice> notices = (List<Notice>) DBUtil.queryForList(sql, paramters, Notice.class);
		System.out.println("总共有:" + notices.size() + "条数据");
		return notices;
	}

	@Override
	public boolean addNotice(Notice notice) {

		int i = 0;
		Connection con = DBUtil.getConnection();
		String sql = "insert into notice (receive_id,subject,text,pub_time,expire_time,status,create_time,creater)values( ?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, notice.getReceiveId());
			ps.setObject(2, notice.getSubject());
			ps.setObject(3, notice.getText());
			ps.setObject(4, notice.getPubTime());
			ps.setObject(5, notice.getExpireTime());
			ps.setObject(6, notice.getStatus());
			ps.setObject(7, notice.getCreateTime());
			ps.setObject(8, notice.getCreater());
			
			//BeanUtil.parsePropsToBean(notice, ps);
			System.out.println(ps.toString());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Notice findNoticeById(int id) {

		String sql = " select * from notice where notice_id = ?";
		Object[] parameters = { id };
		List<Notice> lists = (List<Notice>) DBUtil.queryForList(sql, parameters, Notice.class);

		return lists.get(0);
	}

	@Override
	public boolean updateNotice(Notice notice) {

		String sql = "update notice set receive_id=?, subject=?,text=?,pub_time=?,expire_time=?,status=?,update_time=?,updater=? where notice_id = ? ";
		Connection con = DBUtil.getConnection();
		int isUpdate = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, notice.getReceiveId());
			ps.setObject(2, notice.getSubject());
			ps.setObject(3, notice.getText());
			ps.setObject(4, notice.getPubTime());
			ps.setObject(5, notice.getExpireTime());
			ps.setObject(6, notice.getStatus());
			ps.setObject(7, notice.getUpdateTime());
			ps.setObject(8, notice.getUpdater());
			ps.setObject(9, notice.getNoticeId());

			isUpdate = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isUpdate > 0;
	}

	@Override
	public Boolean deleteUser(Notice notice) {
		String sql = "delete from notice where notice_id = ?";
		int noticeId = notice.getNoticeId();
		Connection con = DBUtil.getConnection();
		int update = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, noticeId);

			update = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return update>0;
	}
	
	

}
