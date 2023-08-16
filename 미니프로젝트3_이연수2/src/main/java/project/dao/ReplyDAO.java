package project.dao;

import java.util.List;

import project.dto.Reply;

public interface ReplyDAO {
	void insertReply(Reply reply);
	List<Reply> getReplyList(String boardid);
	void updateReply(Reply reply);
	void deleteReply(Reply reply);
}
