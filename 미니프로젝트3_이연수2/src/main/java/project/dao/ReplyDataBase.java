package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import project.dto.Reply;
import project.service.ReplyService;
import project.utils.ConnectionHelper;

public class ReplyDataBase implements ReplyDAO {
	static {
		ReplyService.setReplyDAO(new ReplyDataBase());
	}
	@Override
	public void insertReply(Reply reply) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		String sql = "insert into reply("
				+ " boardid"
				+ " ,replyid"
				+ " ,comments"
				+ " ,regdate"
				+ " ,memberid"
				+ " )"
				+ " values("
				+ "   ?"
				+ " , reply_num.nextval"
				+ " , ?"
				+ " , sysdate"
				+ " , ?"
				+ ")";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getBoardid());
			pstmt.setString(2,reply.getComments());
			pstmt.setString(3, reply.getMemberid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
				
		
	}

	@Override
	public List<Reply> getReplyList(String boardid) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Reply> replyList = new ArrayList<>();
		
		String sql = "select boardid, replyid, comments, regdate, memberid from reply"
				+ " where boardid=?"
				+ " order by replyid desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,boardid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				replyList.add(Reply.builder()
									.boardid(rs.getInt(1))
									.replyid(rs.getInt(2))
									.comments(rs.getString(3))
									.regdate(rs.getDate(4))
									.memberid(rs.getString(5))
									.build());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		

		return replyList;
	}

	@Override
	public void updateReply(Reply reply) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		String sql = "update reply set"
				+ " comments=?"
				+ " ,regdate=sysdate"
				+ " where"
				+ " boardid=?"
				+ " and replyid=?";
		
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getComments());
			pstmt.setInt(2, reply.getBoardid());
			pstmt.setInt(3, reply.getReplyid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
	}

	@Override
	public void deleteReply(Reply reply) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		String sql = "delete from reply where boardid=? and replyid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getBoardid());
			pstmt.setInt(2, reply.getReplyid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
	}
	
}
