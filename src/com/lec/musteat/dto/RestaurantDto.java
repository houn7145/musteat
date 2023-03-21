package com.lec.musteat.dto;

import java.sql.Timestamp;

public class RestaurantDto {
	private int rno;
	private String mid;
	private int cno;
	private String rname;
	private String rcontent;
	private String rplace;
	private String mainimg;
	private String subimg1;
	private String subimg2;
	private String rtel;
	private String rmenu;
	private String rprice;
	private int rhit;
	private Timestamp rrdate;
	private int avghit;
	public RestaurantDto() {}
	public RestaurantDto(int rno, String mid, int cno, String rname, String rcontent, String rplace, String mainimg,
			String subimg1, String subimg2, String rtel, String rmenu, String rprice, int rhit, Timestamp rrdate,
			int avghit) {
		this.rno = rno;
		this.mid = mid;
		this.cno = cno;
		this.rname = rname;
		this.rcontent = rcontent;
		this.rplace = rplace;
		this.mainimg = mainimg;
		this.subimg1 = subimg1;
		this.subimg2 = subimg2;
		this.rtel = rtel;
		this.rmenu = rmenu;
		this.rprice = rprice;
		this.rhit = rhit;
		this.rrdate = rrdate;
		this.avghit = avghit;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRplace() {
		return rplace;
	}
	public void setRplace(String rplace) {
		this.rplace = rplace;
	}
	public String getMainimg() {
		return mainimg;
	}
	public void setMainimg(String mainimg) {
		this.mainimg = mainimg;
	}
	public String getSubimg1() {
		return subimg1;
	}
	public void setSubimg1(String subimg1) {
		this.subimg1 = subimg1;
	}
	public String getSubimg2() {
		return subimg2;
	}
	public void setSubimg2(String subimg2) {
		this.subimg2 = subimg2;
	}
	public String getRtel() {
		return rtel;
	}
	public void setRtel(String rtel) {
		this.rtel = rtel;
	}
	public String getRmenu() {
		return rmenu;
	}
	public void setRmenu(String rmenu) {
		this.rmenu = rmenu;
	}
	public String getRprice() {
		return rprice;
	}
	public void setRprice(String rprice) {
		this.rprice = rprice;
	}
	public int getRhit() {
		return rhit;
	}
	public void setRhit(int rhit) {
		this.rhit = rhit;
	}
	public Timestamp getRrdate() {
		return rrdate;
	}
	public void setRrdate(Timestamp rrdate) {
		this.rrdate = rrdate;
	}
	public int getAvghit() {
		return avghit;
	}
	public void setAvghit(int avghit) {
		this.avghit = avghit;
	}
	@Override
	public String toString() {
		return "RestaurantDto [rno=" + rno + ", mid=" + mid + ", cno=" + cno + ", rname=" + rname + ", rcontent="
				+ rcontent + ", rplace=" + rplace + ", mainimg=" + mainimg + ", subimg1=" + subimg1 + ", subimg2="
				+ subimg2 + ", rtel=" + rtel + ", rmenu=" + rmenu + ", rprice=" + rprice + ", rhit=" + rhit
				+ ", rrdate=" + rrdate + ", avghit=" + avghit + "]";
	}
}
