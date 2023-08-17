package project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import project.dao.MemberDAO;
import project.dto.Member;


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
	public JSONObject pwdFind(Member member) {
		JSONObject jsonObject = new JSONObject();
		//아이디에 해당하는 사람이 존재하면 회원 가져오기
    	if(isExistUid(member.getUid())){
    		Member temp = getMember(member.getUid());
    		//가저온 회원의 이름과 폰번호 정보가 일치하면 비밀번호를 변경한다.
    		if(temp.isNamePhone(member)){
    			temp.setPwd(member.getPwd());
    			//비밀번호가 바뀐 회원의 정보를 DB에 업데이트한다.
    			updateMember(temp);
    			 
    			//비밀번호 변경완료 메세지 전달
    			jsonObject.put("message", "비밀번호가 변경되었습니다.");
    		}else{
    			jsonObject.put("message","본인인증에 실패했습니다.");
    		}
    	}else{
    		jsonObject.put("message","아이디가 존재하지 않습니다.");
    	}
		
		return jsonObject;
	}
	//회원 탈퇴
	public JSONObject removeMember(Member member) {
		JSONObject jsonObject = new JSONObject();
		//관리자 아이디는 삭제 불가
    	if(!isExistUid("root")){
    		jsonObject.put("message", "관리자 아이디는 삭제 불가능합니다.");
    		jsonObject.put("status",true);
    	}
    	else{
    		//아이디에 해당하는 회원 가져오기
    		Member temp = getMember(member.getUid());
    		//비밀번호가 일치하면 회원삭제
    		if(temp.isPwd(member.getPwd())){
    			removeMember(member.getUid());
    			//로그인한 회원 session정보 삭제
//    			session.invalidate();
    			//완료되었음을 알림
    			jsonObject.put("message", "회원정보를 삭제하였습니다.");
    			jsonObject.put("status",true);

    		}
    		else{
    			jsonObject.put("message", "회원정보와 비밀번호가 일치하지 않습니다.");
    			jsonObject.put("status",true);
    		}
    	}
		return jsonObject;
	}
	//회원정보 수정
	public JSONObject reviseMember(Member member) {
		JSONObject jsonObject = new JSONObject();
		//Member member = new Member(uid,name,pwd,age,phone,address,gender);
    	Member temp = getMember(member.getUid());
    	//빈칸이면 수정 안하기
    	if(!member.getPwd().isEmpty()){
    		temp.setPwd(member.getPwd());
    	}
    	if(!member.getName().isEmpty()){
    		temp.setName(member.getName());
    	}
    	if(!member.getAge().isEmpty()){
    		temp.setAge(member.getAge());
    	}
    	if(!member.getPhone().isEmpty()){
    		temp.setPhone(member.getPhone());
    	}
    	if(!member.getAddress().isEmpty()){
    		temp.setAddress(member.getAddress());
    	}
    	if(!member.getGender().isEmpty()){
    		temp.setGender(member.getGender());
    	}
    	updateMember(temp);
    	jsonObject.put("message","회원정보 수정이 완료되었습니다.");
    	jsonObject.put("loginMember", temp);

    	return jsonObject;
	}
	public JSONObject uidFind(Member member) {
		JSONObject jsonObject = new JSONObject();
		//Optional로 나온 회원정보 받아오기
    	Optional<Member> userInfo = findIDMember(member.getName(),member.getPhone());
    	//값이 존재하지 않으면
    	if(!userInfo.isPresent()){
    		jsonObject.put("message", "회원가입 정보가 없습니다.");
    	}else{
    		Member temp = userInfo.get();
    		jsonObject.put("message", "회원님의 아이디는 "+temp.getUid());
    	}
    	return jsonObject;
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

	public void updateMember(Member member) {
		memberDAO.updateMember(member);
	}



	public JSONObject index(Member member) {
		JSONObject jsonObject = new JSONObject();
		
		if(member != null)
			jsonObject.put("equalsAdmin",member.isAdmin());
		
		
		
		return jsonObject;
		
	}
	
}
