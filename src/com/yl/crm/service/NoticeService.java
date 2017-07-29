package com.yl.crm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yl.crm.model.Notice;
import com.yl.crm.util.Pager;

public interface NoticeService {

	int getTotalCount(String keyword, String searchField, HttpServletRequest request);

	List<Notice> getAllNoticeByKeywordSearchFieldPageno(String keyword, String searchField, Pager pager,
			HttpServletRequest request);

}
