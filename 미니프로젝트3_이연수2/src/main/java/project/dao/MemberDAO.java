package project.dao;

import java.util.List;
import java.util.Optional;

import project.dto.Member;

public interface MemberDAO {
	String FILE_NAME = "c:\\temp\\member.db";
	boolean isExistUid(String uid);
	int addMember(Member newMember);
	Member getMember(String uid);
	void removeMember(String uid);
	Optional<Member> findIDMember(String name,String phone);
	List<Member> getMemberList();
	//void loadFileMemberMap();
	//void saveFileMemberMap();
	void updateMember(Member member);
}
