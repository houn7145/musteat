package com.lec.musteat.dto;

public class RavgDto {
	private int ano;
	private int rno;
	private double avg;
	public RavgDto() {}
	public RavgDto(int ano, int rno, double avg) {
		this.ano = ano;
		this.rno = rno;
		this.avg = avg;
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
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "RavgDto [ano=" + ano + ", rno=" + rno + ", avg=" + avg + "]";
	}
}	
