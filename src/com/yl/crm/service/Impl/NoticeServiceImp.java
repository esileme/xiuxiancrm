package com.yl.crm.service.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yl.crm.dao.NoticeDao;
import com.yl.crm.dao.Impl.NoticeDaoImp;
import com.yl.crm.model.Notice;
import com.yl.crm.service.NoticeService;
import com.yl.crm.util.Pager;

public class NoticeServiceImp implements NoticeService {
	private NoticeDao noticeDao = new NoticeDaoImp();

	@Override
	public int getTotalCount(String keyword, String searchField, HttpServletRequest request) {
		return noticeDao.getTotalCount(keyword, searchField, request);
	}

	@Override
	public List<Notice> getAllNoticeByKeywordSearchFieldPageno(String keyword, String searchField, Pager pager,
			HttpServletRequest request) {
		return noticeDao.getAllNoticeByKeywordSearchFieldPageno(keyword, searchField, pager, request);
	}

	@Override
	public boolean addNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.addNotice(notice);
	}

	@Override
	public Notice findNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticeDao.findNoticeById(id);
	}

	@Override
	public boolean updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.updateNotice(notice);
	}

	@Override
	public Boolean deleteUser(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.deleteUser(notice);
	}
	

}
