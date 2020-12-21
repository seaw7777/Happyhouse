package com.ssafy.happyhouse.model;

public class MemberDto {

	/******************** 멤버 변수 *************************/
	private String id;
	private String pw;
	private String name;
	private String city;
	private String gu;
	private String dong;
	private int state;
	
	/******************** 생성자 *************************/
	public MemberDto() {}
	
	public MemberDto(String id, String pw, String name, String city, String gu, String dong, int state) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.city = city;
		this.gu = gu;
		this.dong = dong;
		this.state = state;
	}
	
	/******************** Set/Getters *************************/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

		/******************** toString *************************/
	
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", name=" + name + ", city=" + city + ", gu=" + gu + ", dong="
				+ dong + ", state=" + state + "]";
	}
	

	
	

	
}
