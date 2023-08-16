package project.dto;

import java.sql.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Board {
	private int boardid;
	private String title;
	private String contents;
	private Date regdate;
	private int hit;
	private String boardtype;
	private String delete_yn;
	private String fixed_yn;
	private String memberid;
	
	public boolean isWriter(String loginId) {
		return Objects.equals(memberid, loginId);
	}
	public boolean isNotice(String param) {
		return Objects.equals(boardtype, param);
	}
}
