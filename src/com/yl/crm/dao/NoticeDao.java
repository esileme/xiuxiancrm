package com.yl.crm.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yl.crm.model.Notice;
import com.yl.crm.util.Pager;

public interface NoticeDao {

	int getTotalCount(String keyword, String searchField, HttpServletRequest request);

	List<Notice> getAllNoticeByKeywordSearchFieldPageno(String keyword, String searchField, Pager pager,
			HttpServletRequest request);
	
	boolean addNotice(Notice notice);

	Notice findNoticeById(int id);

	boolean updateNotice(Notice notice);

	Boolean deleteUser(Notice notice);

}
