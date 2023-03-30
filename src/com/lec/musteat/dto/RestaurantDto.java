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
	private String rtel;
	private String rmenu;
	private String rprice;
	private int rhit;
	private Timestamp rrdate;
	private int avghit;
	private double ravg;
	private String cname;
	public RestaurantDto() {}
	public RestaurantDto(int rno, String mid, int cno, String rname, String rcontent, String rplace, String mainimg, String rtel, String rmenu, String rprice, int rhit, Timestamp rrdate,
			int avghit) {
		this.rno = rno;
		this.mid = mid;
		this.cno = cno;
		this.rname = rname;
		this.rcontent = rcontent;
		this.rplace = rplace;
		this.mainimg = mainimg;
		this.rtel = rtel;
		this.rmenu = rmenu;
		this.rprice = rprice;
		this.rhit = rhit;
		this.rrdate = rrdate;
		this.avghit = avghit;
	}
	public RestaurantDto(int rno, String mid, int cno, String rname, String rcontent, String rplace, String mainimg,
			 String rtel, String rmenu, String rprice, int rhit, Timestamp rrdate,
			int avghit, double ravg) {
		this.rno = rno;
		this.mid = mid;
		this.cno = cno;
		this.rname = rname;
		this.rcontent = rcontent;
		this.rplace = rplace;
		this.mainimg = mainimg;
		this.rtel = rtel;
		this.rmenu = rmenu;
		this.rprice = rprice;
		this.rhit = rhit;
		this.rrdate = rrdate;
		this.avghit = avghit;
		this.ravg = ravg;
	}
	public RestaurantDto(int rno, String mid, int cno, String rname, String rcontent, String rplace, String mainimg,
			 String rtel, String rmenu, String rprice, int rhit, Timestamp rrdate,
			int avghit, double ravg, String cname) {
		this.rno = rno;
		this.mid = mid;
		this.cno = cno;
		this.rname = rname;
		this.rcontent = rcontent;
		this.rplace = rplace;
		this.mainimg = mainimg;
		this.rtel = rtel;
		this.rmenu = rmenu;
		this.rprice = rprice;
		this.rhit = rhit;
		this.rrdate = rrdate;
		this.avghit = avghit;
		this.ravg = ravg;
		this.cname = cname;
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
	public double getRavg() {
		return ravg;
	}
	public void setRavg(double ravg) {
		this.ravg = ravg;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "RestaurantDto [rno=" + rno + ", mid=" + mid + ", cno=" + cno + ", rname=" + rname + ", rcontent="
				+ rcontent + ", rplace=" + rplace + ", mainimg=" + mainimg + " rtel=" + rtel + ", rmenu=" + rmenu + ", rprice=" + rprice + ", rhit=" + rhit
				+ ", rrdate=" + rrdate + ", avghit=" + avghit + ", ravg=" + ravg + ", cname=" + cname + "]";
	}
}
