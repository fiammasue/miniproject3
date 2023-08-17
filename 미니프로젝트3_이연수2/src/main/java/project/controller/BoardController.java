package project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import project.dto.Board;
import project.dto.Member;
import project.service.BoardService;

public class BoardController {
	private BoardService boardService;
	
	public String AllBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		session.setAttribute("boardtype", "AllBoard.do");
		
		List<Board> boardList = boardService.getBoardList("게시판");
		request.setAttribute("boardList", boardList);
		
		boolean result = boardService.isAdmin(loginMember);
		request.setAttribute("result", result);
		
		return "board/allBoard.jsp";
	}
	public String AllNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		session.setAttribute("boardtype", "AllNotice.do");
		
		List<Board> noticeList = boardService.getBoardList("공지사항");
		request.setAttribute("noticeList", noticeList);
		
		boolean result = boardService.isAdmin(loginMember);
		request.setAttribute("result", result);
		
		return "board/allNotice.jsp";
	}
	public String DeleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	public String DeleteSelectPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	public String InsertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	public String reviseBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	public String ViewOneBoard(Board board,HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("board", boardService.getBoard(board.getBoardid()));
		return "board/viewOneBoard.jsp";
	}
	
}
