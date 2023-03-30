package com.lec.musteat.dto;

import java.sql.Timestamp;

public class NoticeBoardDto {
	private int nno;
	private String aid;
	private String ntitle;
	private String ncontent;
	private Timestamp nrdate;
	private int nhit;
	private String aname;
	public NoticeBoardDto() {}
	public NoticeBoardDto(int nno, String aid, String ntitle, String ncontent, Timestamp nrdate, int nhit, String aname) {
		this.nno = nno;
		this.aid = aid;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nrdate = nrdate;
		this.nhit = nhit;
		this.aname = aname;
	}
	public int getNno() {
		return nno;
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Timestamp getNrdate() {
		return nrdate;
	}
	public void setNrdate(Timestamp nrdate) {
		this.nrdate = nrdate;
	}
	public int getNhit() {
		return nhit;
	}
	public void setNhit(int nhit) {
		this.nhit = nhit;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Override
	public String toString() {
		return "NoticeBoardDto [nno=" + nno + ", aid=" + aid + ", ntitle=" + ntitle + ", ncontent=" + ncontent
				+ ", nrdate=" + nrdate + ", nhit=" + nhit + ", aname=" + aname + "]";
	}
}
