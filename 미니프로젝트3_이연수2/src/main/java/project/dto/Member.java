package project.dto;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements Serializable {
	//필드 : 객체를 파일로 저장하기 위해 직렬화가 필요한데 이를위한 Serializable의 번호
	private static final long serialVersionUID = -1036524153261734687L;
	//필드 : 회원들의 정보를 저장하기 위한 변수
	private String uid;
	private String name;
	private String pwd;
	private String age;
	private String phone;
	private String address;
	private String gender;
	
	//private int currentCount;
	


	///////////////////
	//메소드 : 객체 비교를 위한 equals 와 hashcode
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(uid, other.uid);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uid);
	}

	public boolean isAdmin() {
		return Objects.equals("root", uid);
	}
	public boolean isPwd(String pwd) {
		return this.pwd.equals(pwd);
	}
}
