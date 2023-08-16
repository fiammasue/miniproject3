package project.service;

import java.util.List;

import project.dao.BoardDAO;
import project.dto.Board;

public class BoardService {
	private static BoardDAO boardDAO ;
	
	public static void setBoardDAO(BoardDAO boardDAO) {
		BoardService.boardDAO = boardDAO;
	}
	public static List<Board> getTop5BoardList(String boardtype){
		return boardDAO.getTop5BoardList(boardtype);
	}
	public static List<Board> getBoardList(String boardtype){
		return boardDAO.getBoardList(boardtype);
	}
	public static void insertBoard(Board board) {
		boardDAO.insertBoard(board);
	}
	public static Board getBoard(int boardid) {
		return boardDAO.getBoard(boardid);
	}
	public static void hitUpdate(int boardid) {
		boardDAO.hitUpdate(boardid);
	}
	public static void updateBoard(int boardid,Board board) {
		boardDAO.updateBoard(boardid, board);
	}
	public static void deleteBoard(int boardid) {
		boardDAO.deleteBoard(boardid);
	}

	public static void deleteBoards(String[] boardidList) {
		boardDAO.deleteBoards(boardidList);
	}
	
}
