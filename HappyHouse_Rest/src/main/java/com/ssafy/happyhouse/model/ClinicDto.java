package com.ssafy.happyhouse.model;

public class ClinicDto {
	private String csido;
	private String cgugun;
	private String cname;
	private String caddress;
	private String cweekdays;
	private String csaturday;
	private String choliday;
	private String cphone;
	
	@Override
	public String toString() {
		return "ClinicDto [csido=" + csido + ", cgugun=" + cgugun + ", cname=" + cname + ", caddress=" + caddress
				+ ", cweekdays=" + cweekdays + ", csaturday=" + csaturday + ", choliday=" + choliday + ", cphone="
				+ cphone + "]";
	}

	public String getCsido() {
		return csido;
	}

	public void setCsido(String csido) {
		this.csido = csido;
	}

	public String getCgugun() {
		return cgugun;
	}

	public void setCgugun(String cgugun) {
		this.cgugun = cgugun;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getCweekdays() {
		return cweekdays;
	}

	public void setCweekdays(String cweekdays) {
		this.cweekdays = cweekdays;
	}

	public String getCsaturday() {
		return csaturday;
	}

	public void setCsaturday(String csaturday) {
		this.csaturday = csaturday;
	}

	public String getCholiday() {
		return choliday;
	}

	public void setCholiday(String choliday) {
		this.choliday = choliday;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	
}
