package com.lec.musteat.dto;

import java.sql.Timestamp;

public class FreeBoardDto {
	private int fno;
	private String mid;
	private String ftitle;
	private String fcontent;
	private String fimage1;
	private String fimage2;
	private Timestamp frdate;
	private int fhit;
	private int fgroup;
	private int fstep;
	private int findent;
	private String mname;
	public FreeBoardDto() {}
	public FreeBoardDto(int fno, String mid, String ftitle, String fcontent, String fimage1, String fimage2,
			Timestamp frdate, int fhit, int fgroup, int fstep, int findent, String mname) {
		this.fno = fno;
		this.mid = mid;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.fimage1 = fimage1;
		this.fimage2 = fimage2;
		this.frdate = frdate;
		this.fhit = fhit;
		this.fgroup = fgroup;
		this.fstep = fstep;
		this.findent = findent;
		this.mname = mname;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getFimage1() {
		return fimage1;
	}
	public void setFimage1(String fimage1) {
		this.fimage1 = fimage1;
	}
	public String getFimage2() {
		return fimage2;
	}
	public void setFimage2(String fimage2) {
		this.fimage2 = fimage2;
	}
	public Timestamp getFrdate() {
		return frdate;
	}
	public void setFrdate(Timestamp frdate) {
		this.frdate = frdate;
	}
	public int getFhit() {
		return fhit;
	}
	public void setFhit(int fhit) {
		this.fhit = fhit;
	}
	public int getFgroup() {
		return fgroup;
	}
	public void setFgroup(int fgroup) {
		this.fgroup = fgroup;
	}
	public int getFstep() {
		return fstep;
	}
	public void setFstep(int fstep) {
		this.fstep = fstep;
	}
	public int getFindent() {
		return findent;
	}
	public void setFindent(int findent) {
		this.findent = findent;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Override
	public String toString() {
		return "FreeBoardDto [fno=" + fno + ", mid=" + mid + ", ftitle=" + ftitle + ", fcontent=" + fcontent
				+ ", fimage1=" + fimage1 + ", fimage2=" + fimage2 + ", frdate=" + frdate + ", fhit=" + fhit
				+ ", fgroup=" + fgroup + ", fstep=" + fstep + ", findent=" + findent + ", mname=" + mname + "]";
	}
	
}
