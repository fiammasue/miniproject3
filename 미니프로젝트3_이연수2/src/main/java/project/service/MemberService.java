package project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import project.dao.MemberDAO;
import project.dto.Member;
import project.dto.ResultMember;

public class MemberService {
	private MemberDAO memberDAO;

	
	public JSONObject insertMember(Member member) {
		JSONObject jsonResult = new JSONObject();
		if(memberDAO.addMember(member)==1) {
			jsonResult.put("status", true);
			jsonResult.put("message", "회원가입이 성공되었습니다.");
		}else {
			jsonResult.put("status", false);
			jsonResult.put("message", "아이디 중복으로 회원가입이 실패했습니다.");
		}
		return jsonResult;
	}
	
	
	
	//login하는 거
	public JSONObject loginMember(Member member) {
		JSONObject jsonResult = new JSONObject();
		
		Member temp = new Member();
		
		if(!isExistUid(member.getUid())) {
			jsonResult.put("status", false);
			jsonResult.put("message", "아이디가 존재하지 않습니다.");		
		}
    	else{
    		//아이디와 일치하는 회원정보를 가져옴
    		temp = getMember(member.getUid());
    		//비밀번호가 일치하는지 확인
    		if(temp.isPwd(member.getPwd())){
    			//로그인한 정보를 세션에 저장해서 로그인을 유지한다.
    			jsonResult.put("message", "로그인 되었습니다.");
    			jsonResult.put("loginMember", temp);
    			jsonResult.put("status", true);
    		}
    		else{
    			jsonResult.put("status", false);
    			jsonResult.put("message", "비밀번호가 일치하지 않습니다.");
    		}
    	}
		
		return jsonResult; 
	}
	//비밀번호 찾기
	public String pwdFind(Member member) {
//		ResultMember result = new ResultMember();
		String message ="";
		//아이디에 해당하는 사람이 존재하면 회원 가져오기
    	if(isExistUid(member.getUid())){
    		Member mem = getMember(member.getUid());
    		System.out.println("member = "+member);
    		//가저온 회원의 이름과 폰번호 정보가 일치하면 비밀번호를 변경한다.
    		if(mem.getName().equals(member.getName())
    				&& mem.getPhone().equals(member.getPhone())){
    			
    			mem.setPwd(member.getPwd());
    			//비밀번호가 바뀐 회원의 정보를 DB에 업데이트한다.
    			updateMember(mem);
    			 System.out.println("member = "+ mem);
    			//이것도 함수 만들어야하나? public static void revisePwd(Member member,String pwd)
    			//MemberService.saveFileMemberMap();
    			 
    			//비밀번호 변경완료 메세지 전달
    			 
    			message="비밀번호가 변경되었습니다.";
    		}else{
    			
    			message="본인인증에 실패했습니다.";
    		}
    	}else{
    		message="아이디가 존재하지 않습니다.";
    	}
		
		return message;
	}
	//회원 탈퇴
	public String removeMember(Member member) {
		String message = "";
		//관리자 아이디는 삭제 불가
    	if(!isExistUid("root")){
    		message="관리자 아이디는 삭제 불가능합니다.";
    	}
    	else{
    		//아이디에 해당하는 회원 가져오기
    		Member mem = getMember(member.getUid());
    		//비밀번호가 일치하면 회원삭제
    		if(mem.getPwd().equals(member.getPwd())){
    			removeMember(member.getUid());
    			//로그인한 회원 session정보 삭제
//    			session.invalidate();
    			//완료되었음을 알림
    			message="회원정보를 삭제하였습니다.";
    		}
    		else{
    			message="회원정보와 비밀번호가 일치하지 않습니다.";
    		}
    	}
		
		return message;
	}
	//회원정보 수정
	public ResultMember reviseMember(Member member) {
		ResultMember result = new ResultMember();
		String message="";
		//Member member = new Member(uid,name,pwd,age,phone,address,gender);
    	Member mem = getMember(member.getUid());
    	//빈칸이면 수정 안하기
    	if(!member.getPwd().isEmpty()){
    		mem.setPwd(member.getPwd());
    	}
    	if(!member.getName().isEmpty()){
    		mem.setName(member.getName());
    	}
    	if(!member.getAge().isEmpty()){
    		mem.setAge(member.getAge());
    	}
    	if(!member.getPhone().isEmpty()){
    		mem.setPhone(member.getPhone());
    	}
    	if(!member.getAddress().isEmpty()){
    		mem.setAddress(member.getAddress());
    	}
    	if(!member.getGender().isEmpty()){
    		mem.setGender(member.getGender());
    	}
    	updateMember(mem);
    	message="회원정보 수정이 완료되었습니다.";
    	result.setMember(mem);
    	result.setMessage(message);
    	
    	
    	return result;
	}
	public ResultMember uidFind(Member member) {
		ResultMember result = new ResultMember();
		//Optional로 나온 회원정보 받아오기
    	Optional<Member> userInfo = findIDMember(member.getName(),member.getPhone());
    	String message = "";
    	//값이 존재하지 않으면
    	if(!userInfo.isPresent()){
    		message="회원가입 정보가 없습니다.";
    		result.setMessage(message);
    	}else{
    		message="회원님의 아이디는 ";
    		Member mem = userInfo.get();
    		result.setMember(mem);
    		result.setMessage(message);
        	//회원의 아이디값 반환
        	//request.setAttribute("uid", mem.getUid());
    	}
    	return result;
	}
	//////////////////////
	// 아이디 존재여부확인
	public boolean isExistUid(String uid) {
		return memberDAO.isExistUid(uid);
	}
	
	///////////////
	//DB에서 해당객체 찾아오기
	public Member getMember(String uid) {
		return memberDAO.getMember(uid);
	}

	////////////////
	//DB에 멤버 추가
	public void addMember(Member newMember) {
		memberDAO.addMember(newMember);
	}

	

	///////////////
	//회원삭제
	public void removeMember(String uid) {
		memberDAO.removeMember(uid);
	}

	/////////////
	//이름,번호가 일치하는 멤버 찾기
	public Optional<Member> findIDMember(String name, String phone) {
		return memberDAO.findIDMember(name, phone);
	}

	///////////////
	//멤버 전체출력을 위해 Map전달
	public List<Member> getMemberList() {
		return memberDAO.getMemberList();
	}

	////////////////////////
	//회원정보 기록하기
	////////////////////////
//	public static void saveFileMemberMap() {
//		memberDAO.saveFileMemberMap();
//
//	}// private void saveFileMemberList()
	
	public void updateMember(Member member) {
		memberDAO.updateMember(member);
	}
	
}
