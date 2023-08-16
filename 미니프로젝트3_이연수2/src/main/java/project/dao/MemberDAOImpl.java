package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import project.dto.Member;
import project.service.MemberService;
import project.utils.ConnectionHelper;

public class MemberDAOImpl implements MemberDAO{

//select memberid from member where memberid=?
	@Override
	public boolean isExistUid(String uid) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select memberid from member where memberid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			if(rs.next())return true;
//			else return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		return false;
	}
//insert into member(memberid,name,pwd,age,phone,address,gender) values(?,?,?,?,?,?,?)
	@Override
	public int addMember(Member newMember) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		int count = 0;
		String sql = "insert into member(memberid,name,pwd,age,phone,address,gender) values(?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMember.getUid());
			pstmt.setString(2, newMember.getName());
			pstmt.setString(3, newMember.getPwd());
			pstmt.setString(4, newMember.getAge());
			pstmt.setString(5, newMember.getPhone());
			pstmt.setString(6, newMember.getAddress());
			pstmt.setString(7, newMember.getGender());
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
		}
		return count;
	}
//	select memberid,name,pwd,age,phone,address,gender from member where memberid=?
	@Override
	public Member getMember(String uid) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select memberid,name,pwd,age,phone,address,gender from member where memberid=?";
		Member member = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = Member.builder()
						.uid(rs.getString(1))
						.name(rs.getString(2))
						.pwd(rs.getString(3))
						.age(rs.getString(4))
						.phone(rs.getString(5))
						.address(rs.getString(6))
						.gender(rs.getString(7))
						.build();
				
			}
			return member;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		return null;
	}
// delete from member where memberid=?
	@Override
	public void removeMember(String uid) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		String sql = "delete from member where memberid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
		}
		
	}
//	select memberid,name,pwd,age,phone,address,gender from member where name=? and phone=? 
	@Override
	public Optional<Member> findIDMember(String name, String phone) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select memberid,name,pwd,age,phone,address,gender from member where name=? and phone=?";
		Optional<Member> findMember = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Member member = Member.builder()
						.uid(rs.getString(1))
						.name(rs.getString(2))
						.pwd(rs.getString(3))
						.age(rs.getString(4))
						.phone(rs.getString(5))
						.address(rs.getString(6))
						.gender(rs.getString(7))
						.build();
				findMember = Optional.ofNullable(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		return findMember;
	}
// select memberid,name,pwd,age,phone,address,gender from member
	@Override
	public List<Member> getMemberList() {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select memberid,name,pwd,age,phone,address,gender from member";
		List<Member> memberList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberList.add(Member.builder()
						.uid(rs.getString(1))
						.name(rs.getString(2))
						.pwd(rs.getString(3))
						.age(rs.getString(4))
						.phone(rs.getString(5))
						.address(rs.getString(6))
						.gender(rs.getString(7))
						.build());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		
		
		
		
		return memberList;
	}
	

	//update member set name=?,pwd=?,age=?,phone=?,address=?,gender=? from memberid=?
	@Override
	public void updateMember(Member member) {
		Connection conn = ConnectionHelper.getConnection("oracle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update member set name=?,pwd=?,age=?,phone=?,address=?,gender=? where memberid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getAge());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getGender());
			pstmt.setString(7, member.getUid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
