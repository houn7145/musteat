package com.lec.musteat.dto;

public class RavgDto {
	private int ano;
	private int rno;
	private double rating;
	public RavgDto() {}
	public RavgDto(int ano, int rno, double rating) {
		this.ano = ano;
		this.rno = rno;
		this.rating = rating;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public double getrating() {
		return rating;
	}
	public void setrating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "RavgDto [ano=" + ano + ", rno=" + rno + ", rating=" + rating + "]";
	}
}	
