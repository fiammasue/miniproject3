package project.dao;

import java.util.List;

import project.dto.Board;

public interface BoardDAO {
	//게시판 글 목록 전체보기 // 선택된 게시판에 따른 목록불러오기
	//select boardid, title, memberid from board;
	//select boardid, title, contents, hit, good, memberid from board;
	public List<Board> getTop5BoardList(String boardtype);
	public List<Board> getBoardList(String boardtype);
	//게시판 글 삽입
//	insert into board(boardid, title, contents,regdate, hit, good, memberid) values(?,?,?,?,?,?)
	//insert into board(boardid, title, contents, hit, good, memberid) values(?,?,?,?,?,?)
//	insert into board(boardid, title, contents,regdate, hit, good, memberid,boardtype) values(board_num.nextval,'제목','내용',sysdate,0,0,'yeonsul','공지사항');
	public void insertBoard(Board board);
	//게시판 글 상세보기
	//select boardid, title, contents, regdate, hit, good, memberid from board;
	//select boardid, title, contents, regdate, hit, good, memberid from board;
	public Board getBoard(int boardid);
	public void hitUpdate(int boardid);
	//글 업데이트
	//update board set title=?,contents=?,regdate=? from boardid=?
	public void updateBoard(int boardid,Board board);
	//글삭제 //관리자모드까지 하려면 이거 배열로 받아와야할듯;;
	//delete from board where boardid=?
	public void deleteBoard(int boardid);
	//관리자의 글 전체삭제
	public void deleteBoards(String[] boardidList);
	
	
//	public static void main(String[] args) {
//    	
////    	Board board = Board.builder()
////				.memberid("yeonsul")
////				.title("d놉")
////				.contents("d")
////				.boardtype("게시판")
////				.fixed_yn("Y")
////				.build();
////    	insertBoard(board);
////		Board board = getBoard(1);
////		System.out.println(board);
//		List<Board> boardList = getBoardList("공지사항");
//		System.out.println(boardList);
////    	Board board = Board.builder()
////				.title("에에에에")
////				.contents("0801에 만나")
////				.build();
////    	updateBoard(1,board);
//		
////		deleteBoard(1);
////		boolean result = isMemberid("yeonsul");
////		System.out.println(result);
//
//	}
}
