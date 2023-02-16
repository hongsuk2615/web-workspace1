package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.common.JDBCTemplate.*;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList() {
		Connection conn = getConnection();

		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);

		close(conn);

		return list;

	}

	public int increaseCount(int nno) {
		Connection conn = getConnection();
		int result = new NoticeDao().increaseCount(conn, nno);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Notice selectNotice(int nno) {
		Connection conn = getConnection();

		Notice n = new NoticeDao().selectNotice(conn, nno);

		close(conn);

		return n;
	}

	public int insertNotice(int userNo, String title, String content) {
		Connection conn = getConnection();
		int result = new NoticeDao().insertNotice(conn, userNo, title, content);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int deleteNotice(int nno) {
		Connection conn = getConnection();
		int result = new NoticeDao().deleteNotice(conn, nno);
		if (result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int updateNotice(int nno,String title,String content) {
		Connection conn = getConnection();
		int result = new NoticeDao().updateNotice(conn, nno, title, content);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
}
