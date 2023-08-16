package project.service;

import java.util.List;

import project.dao.ReplyDAO;
import project.dto.Reply;

public class ReplyService {
	public static ReplyDAO replyDAO;
	
	public static void setReplyDAO(ReplyDAO replyDAO) {
		ReplyService.replyDAO =replyDAO;
	}
	public static void insertReply(Reply reply) {
		replyDAO.insertReply(reply);
	}
	public static List<Reply> getReplyList(String boardid) {
		return replyDAO.getReplyList(boardid);
	}
	public static void updateReply(Reply reply) {
		replyDAO.updateReply(reply);
	}
	public static void deleteReply(Reply reply) {
		replyDAO.deleteReply(reply);
	}
}
