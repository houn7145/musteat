package com.lec.musteat.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDto {
	private String mid;
	private String mpw;
	private String mname;
	private String memail;
	private String mtel;
	private String mgender;
	private Date mbirth;
	private Timestamp mrdate;
	public MemberDto() {}
	public MemberDto(String mid, String mpw, String mname, String memail, String mtel, String mgender, Date mbirth,
			Timestamp mrdate) {
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.memail = memail;
		this.mtel = mtel;
		this.mgender = mgender;
		this.mbirth = mbirth;
		this.mrdate = mrdate;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public String getMgender() {
		return mgender;
	}
	public void setMgender(String mgender) {
		this.mgender = mgender;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
	public Timestamp getMrdate() {
		return mrdate;
	}
	public void setMrdate(Timestamp mrdate) {
		this.mrdate = mrdate;
	}
	@Override
	public String toString() {
		return "MemberDto [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", memail=" + memail + ", mtel=" + mtel
				+ ", mgender=" + mgender + ", mbirth=" + mbirth + ", mrdate=" + mrdate + "]";
	}
}
