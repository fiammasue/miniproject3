package project.service;

import java.util.List;

import org.json.JSONObject;

import project.dao.BoardDAO;
import project.dto.Board;
import project.dto.Member;

public class BoardService {
	private BoardDAO boardDAO ;
	
	public List<Board> getTop5BoardList(String boardtype){
		return boardDAO.getTop5BoardList(boardtype);
	}
	public List<Board> getBoardList(String boardtype){
		return boardDAO.getBoardList(boardtype);
	}
	public void insertBoard(Board board) {
		boardDAO.insertBoard(board);
	}
	public Board getBoard(int boardid) {
		return boardDAO.getBoard(boardid);
	}
	public void hitUpdate(int boardid) {
		boardDAO.hitUpdate(boardid);
	}
	public void updateBoard(int boardid,Board board) {
		boardDAO.updateBoard(boardid, board);
	}
	public void deleteBoard(int boardid) {
		boardDAO.deleteBoard(boardid);
	}

	public void deleteBoards(String[] boardidList) {
		boardDAO.deleteBoards(boardidList);
	}
	public boolean isAdmin(Member member) {
		boolean result = false;
		if(member!= null) {
			result = member.isAdmin();
		}
		
		return result;
	}
	
}
