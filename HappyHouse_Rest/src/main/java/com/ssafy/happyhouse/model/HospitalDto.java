package com.ssafy.happyhouse.model;

public class HospitalDto {
	private String sido;
	private String gugun;
	private String name;
	private String address;
	private String phone;
	
	@Override
	public String toString() {
		return "HospitalDto [sido=" + sido + ", gugun=" + gugun + ", name=" + name + ", address=" + address + ", phone="
				+ phone + "]";
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

	
