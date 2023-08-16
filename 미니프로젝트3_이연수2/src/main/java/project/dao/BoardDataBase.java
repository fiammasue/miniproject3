package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import project.dto.Board;
import project.service.BoardService;
import project.utils.ConnectionHelper;

public class BoardDataBase implements BoardDAO{
	static {
		BoardService.setBoardDAO(new BoardDataBase());
	}
	
	
	//게시판 글 목록 전체보기 // 선택된 게시판에 따른 목록불러오기
		//select boardid, title, memberid from board;
		//select boardid, title, contents, hit, good, memberid from board;
		@Override
		public List<Board> getTop5BoardList(String boardtype){
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql = "select "
					+ " boardid, title, contents, hit, memberid, fixed_yn"
					+ " from ("
					+ " select /*+ index_desc(board PK_BOARD) */"
					+ " boardid, title, contents, hit, memberid, fixed_yn"
					+ " from board"
					+ " where boardtype=? and fixed_yn='Y' "
					+ " union all"
					+ " select /*+ index_desc(board PK_BOARD) */"
					+ " boardid, title, contents, hit, memberid, fixed_yn"
					+ " from board "
					+ " where boardtype=? and fixed_yn='N' "
					+ " )"
					+ "where  rownum <= 5";
			
			List<Board> boardList = new ArrayList<>();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardtype);
				pstmt.setString(2, boardtype);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					boardList.add(Board.builder()
							.boardid(rs.getInt("boardid"))
							.title(rs.getString("title"))
							.contents(rs.getString("contents"))
							.hit(rs.getInt("hit"))
							.memberid(rs.getString("memberid"))
							.build());;
				}
//				boardList.stream().forEach(b -> System.out.println(b));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
			
			
			return boardList;
		}
		@Override
		public List<Board> getBoardList(String boardtype) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql = "select /*+INDEX_desc(board PK_BOARD) */ boardid, title, contents, hit, memberid,fixed_yn from board where boardtype=? and fixed_yn='Y' "
					+ " union all "
					+ " select /*+ index_desc(board PK_BOARD) */ "
					+ " boardid, title, contents, hit, memberid,fixed_yn "
					+ " from board "
					+ " where boardtype=? and fixed_yn <> 'Y' or fixed_yn is null";
			
			List<Board> boardList = new ArrayList<>();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardtype);
				pstmt.setString(2, boardtype);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					boardList.add(Board.builder()
							.boardid(rs.getInt("boardid"))
							.title(rs.getString("title"))
							.contents(rs.getString("contents"))
							.hit(rs.getInt("hit"))
							.memberid(rs.getString("memberid"))
							.fixed_yn(rs.getString("fixed_yn"))
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
			
			
			return boardList;
		}
		//게시판 글 삽입
//		insert into board(boardid, title, contents,regdate, hit, good, memberid) values(?,?,?,?,?,?)
		//insert into board(boardid, title, contents, hit, good, memberid) values(?,?,?,?,?,?)
//		insert into board(boardid, title, contents,regdate, hit, good, memberid,boardtype) values(board_num.nextval,'제목','내용',sysdate,0,0,'yeonsul','공지사항');
		@Override
		public void insertBoard(Board board) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt = null;
	
			String sql ="insert into board( " 
		             + "  boardid " 
		             + " ,title " 
		             + " ,contents "  
		             + " ,regdate "  
		             + " ,hit "  
		             + " ,boardtype "  
		             + " ,fixed_yn "  
		             + " ,memberid "  
		             + " ) values ( "  
		             + "  board_num.nextval "
		             + " ,? "
		             + " ,? "
		             + " ,sysdate "
		             + " ,0 "
		             + " ,? "
		             + " ,? "
		             + " ,?) ";
//			Date now = Calendar.getInstance().getTime();
//			java.sql.Date regdate = new java.sql.Date(now.getTime());
			
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContents());
				pstmt.setString(3, board.getBoardtype());
				pstmt.setString(4, board.getFixed_yn());
				pstmt.setString(5, board.getMemberid());
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
			
			
		}
		//게시판 글 상세보기
		//select boardid, title, contents, regdate, hit, good, memberid from board;
		//select boardid, title, contents, regdate, hit, good, memberid from board;
		@Override
		public Board getBoard(int boardid) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			Board board =null;
			
			String sql = "select boardid, title, contents, regdate, hit, memberid,boardtype from board where boardid=?";
			
			try {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board = Board.builder()
										.boardid(rs.getInt(1))
										.title(rs.getString(2))
										.contents(rs.getString(3))
										.regdate(rs.getDate(4))
										.hit(rs.getInt(5))
										.memberid(rs.getString(6))
										.boardtype(rs.getString(7))
										.build();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
			return board;
		}
		@Override
		public void hitUpdate(int boardid) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt= null;
			String sql = "update board set hit = hit + 1 where boardid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardid);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
			
			
		}
		//글 업데이트
		//update board set title=?,contents=?,regdate=? from boardid=?
		@Override
		public void updateBoard(int boardid,Board board) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt= null;
			String sql = "update board "
					+ " set "
					+ " title=?"
					+ " ,contents=?"
					+ " ,regdate=sysdate"
					+ " ,fixed_yn=?"
					+ " , boardtype=? "
					+ " where "
					+ " boardid=?";
//			Date now = Calendar.getInstance().getTime();
//			java.sql.Date regdate = new java.sql.Date(now.getTime());
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContents());
				pstmt.setString(3, board.getFixed_yn());
				pstmt.setString(4, board.getBoardtype());
				pstmt.setInt(5, boardid);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
		}
		//글삭제 //관리자모드까지 하려면 이거 배열로 받아와야할듯;;
		//delete from board where boardid=?
		@Override
		public void deleteBoard(int boardid) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt= null;
			String sql = "delete from board where boardid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, boardid);
				pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}
		}
		
		public static void main(String[] args) {
	    	
//	    	Board board = Board.builder()
//					.memberid("yeonsul")
//					.title("d놉")
//					.contents("d")
//					.boardtype("게시판")
//					.fixed_yn("Y")
//					.build();
//	    	insertBoard(board);
//			Board board = getBoard(1);
//			System.out.println(board);
//			List<Board> boardList = getBoardList("공지사항");
//			System.out.println(boardList);
//	    	Board board = Board.builder()
//					.title("에에에에")
//					.contents("0801에 만나")
//					.build();
//	    	updateBoard(1,board);
			
//			deleteBoard(1);
//			boolean result = isMemberid("yeonsul");
//			System.out.println(result);

		}
		@Override
		public void deleteBoards(String[] boardIds) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			Statement stmt= null;
			String params = Stream.of(boardIds).collect(Collectors.joining("','", "('", "')"));
//			StringBuilder params = new StringBuilder();
//			
//			if(boardidList.length >= 1) {
//				params.append("('");
//				params.append(boardidList[0]);
//				for(int i=1;i<boardidList.length;i++) {
//					params.append("','").append(boardidList[i]);
//				}
//				params.append("')");
//			}
//			
			String sql = "delete from board where boardid in "+params;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(stmt);
				ConnectionHelper.close(conn);
			}
			
		}
}
