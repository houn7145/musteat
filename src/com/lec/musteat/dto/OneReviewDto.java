package com.lec.musteat.dto;

public class OneReviewDto {
	private int ono;
	private int rno;
	private String mid;
	private String ocontent;
	private int orecommand;
	public OneReviewDto() {}
	public OneReviewDto(int ono, int rno, String mid, String ocontent, int orecommand) {
		this.ono = ono;
		this.rno = rno;
		this.mid = mid;
		this.ocontent = ocontent;
		this.orecommand = orecommand;
	}
	public int getOno() {
		return ono;
	}
	public void setOno(int ono) {
		this.ono = ono;
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
	public String getOcontent() {
		return ocontent;
	}
	public void setOcontent(String ocontent) {
		this.ocontent = ocontent;
	}
	public int getOrecommand() {
		return orecommand;
	}
	public void setOrecommand(int orecommand) {
		this.orecommand = orecommand;
	}
	@Override
	public String toString() {
		return "OneReviewDto [ono=" + ono + ", rno=" + rno + ", mid=" + mid + ", ocontent=" + ocontent + ", orecommand="
				+ orecommand + "]";
	}
}
