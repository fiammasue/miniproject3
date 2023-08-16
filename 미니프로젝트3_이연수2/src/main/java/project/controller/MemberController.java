package project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import project.dto.Member;
import project.service.MemberService;

public class MemberController {
	
	private MemberService memberService;
	
	
	public String isExistUid(Member member,HttpServletRequest request, HttpServletResponse response) throws Exception  {
		JSONObject jsonObject = new JSONObject();
		if(memberService.isExistUid(member.getUid())) {
			jsonObject.put("status", true);
			jsonObject.put("message", "아이디가 사용불가능합니다.");
		}else {
			jsonObject.put("status", false);
			jsonObject.put("message", "아이디가 사용가능합니다.");
			
		}
		
		return jsonObject.toString();
	}
	public String AllMemberShow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
	public String Index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "index.jsp";
	}
	public String agreeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/agreeForm.html";
	}
	public String insertForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/insertForm.html";
	}
	public String InsertMember(Member member,HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = memberService.insertMember(member);
		return jsonObject.toString();
	}
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "member/loginForm.html";
	}
	public String LoginMember(Member member,HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		JSONObject jsonObject = memberService.loginMember(member);
		if(jsonObject.has("loginMember")) {
			session.setAttribute("loginMember", jsonObject.get("loginMember"));
		}
		return jsonObject.toString();
	}
	public String LogoutMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		session.invalidate();
		jsonObject.put("message", "로그아웃 되었습니다.");
		jsonObject.put("status", true);
		return jsonObject.toString();
	}
	public String pwdFindForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/pwdFindForm.html";
	}
	public String PwdFind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
	public String removeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/removeForm.html";
	}
	public String RemoveMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
	public String ReviseMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
	public String ReviseForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	public String uidFindForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "member/uidFindForm.html";
	}
	public String UidFind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
	public String View(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return null;
	}
}
