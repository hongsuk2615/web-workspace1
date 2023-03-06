package com.kh.board.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao() {
		
		try {
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectListCount");
		/*
		 * SELECT COUNT(*) AS COUNT
		 *   FROM BOARD
		 *  WHERE STATUS = 'Y'
		 *    AND BOARD_TYPE = 1 
		 */
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectList(Connection conn, PageInfo pi){
		PreparedStatement pstmt = null;
		ArrayList<Board> list = new ArrayList<Board>();
		String sql = prop.getProperty("selectList");
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board(rset.getInt("BOARD_NO")
						            ,rset.getString("CATEGORY_NAME")
						            ,rset.getString("BOARD_TITLE")
						            ,rset.getString("USER_ID")
						            ,rset.getInt("COUNT")
						            ,rset.getDate("CREATE_DATE"));
				
				list.add(b);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public ArrayList<Category> selectCategoryList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCategoryList");
		ArrayList<Category> list = new ArrayList<Category>();
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();	
			while(rset.next()) {
				list.add(new Category(rset.getInt("CATEGORY_NO"), rset.getString("CATEGORY_NAME")));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);			
		}
		return list;
	}
	
	public int insertBoard(Connection conn,Board b) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2,  b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertAttachment(Connection conn,Attachment at) {
		int result = 0;		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int increaseCount(Connection conn, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}
	public Board selectBoard(Connection conn, int bno) {
		Board b = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoard");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO"), rset.getString("CATEGORY_NAME"), rset.getString("BOARD_TITLE"), rset.getString("BOARD_CONTENT"), rset.getString("USER_ID"), rset.getDate("CREATE_DATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return b;
	}
	
	public Attachment getAttachment(Connection conn, int bno) {
		Attachment at = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getAttachment");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				at = new Attachment(rset.getInt("FILE_NO"), rset.getInt("REF_BNO"), rset.getString("ORIGIN_NAME"), rset.getString("CHANGE_NAME"), rset.getString("FILE_PATH"), rset.getDate("UPLOAD_DATE"), rset.getInt("FILE_LEVEL"), rset.getString("STATUS"));			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return at;
		
		
	}
	public int updateAttachment(Connection conn,Attachment at) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int insertAttachmentModify(Connection conn, Attachment at) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentModify");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoard(Connection conn, Board b) {
int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteBoard(Connection conn,int bno,int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setInt(2, userNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	public int deleteAttachment(Connection conn, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;	
	}
	
	public int insertThumbnailBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertThumbnailBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, Integer.parseInt(b.getBoardWriter()));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentList");
		try {
			pstmt = conn.prepareStatement(sql);
			for(Attachment at : list) {
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileLevel());
			result *= pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Board> selectThumbnailList(Connection conn){
		ArrayList<Board> list = new ArrayList<Board>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectThumbnailList");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setCount(rset.getInt("COUNT"));
				b.setTitleImg(rset.getString("FILE_PATH")+rset.getString("CHANGE_NAME"));
				list.add(b);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public Board selectThumbnailBoard(Connection conn, int bno) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectThumbnailBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board();
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriter(rset.getString("BOARD_WRITER"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}
	
	public ArrayList<Attachment> getThumbnailAttachments(Connection conn, int bno){
		ArrayList<Attachment> list = new ArrayList<Attachment>();
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getThumbnailAttachments");
		ResultSet rset = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileLevel(rset.getInt("FILE_LEVEL"));
				at.setFilePath(rset.getString("FILE_PATH"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				list.add(at);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	public int insertReply(Connection conn, int bno, String content, int replyWriter) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReply");
		int result = 0;
		try {
			System.out.println(content + ", " + replyWriter + ", " + bno);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, bno);
			pstmt.setInt(3, replyWriter);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<Reply> replyList(Connection conn, int bno){
		
		ArrayList<Reply> list = new ArrayList<Reply>();
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("replyList");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Reply r = new Reply(rset.getInt("REPLY_NO"), rset.getString("REPLY_CONTENT"), rset.getString("USER_ID"), rset.getString("CREATE_DATE"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
