package com.ssafy.happyhouse.model;

public class StoreDto {
	
	/***** 멤버 변수 *****/
	
	private String name;
	private String bigkind;
	private String middlekind;
	private String smallkind;
	private String sido;
	private String gugun;
	private String dong;
	private String lng;
	private String lat;
	
	
	/***** 생성자 *****/
	
	public StoreDto() {}
	public StoreDto(String name, String bigkind, String middlekind, String smallkind, String sido, String gugun,
			String dong, String lng, String lat) {
		super();
		this.name = name;
		this.bigkind = bigkind;
		this.middlekind = middlekind;
		this.smallkind = smallkind;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.lng = lng;
		this.lat = lat;
	}
	
	/***** Setters/Getters *****/
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBigkind() {
		return bigkind;
	}
	public void setBigkind(String bigkind) {
		this.bigkind = bigkind;
	}
	public String getMiddlekind() {
		return middlekind;
	}
	public void setMiddlekind(String middlekind) {
		this.middlekind = middlekind;
	}
	public String getSmallkind() {
		return smallkind;
	}
	public void setSmallkind(String smallkind) {
		this.smallkind = smallkind;
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
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
	/***** toString *****/
	
	@Override
	public String toString() {
		return "StoreDto [name=" + name + ", bigkind=" + bigkind + ", middlekind=" + middlekind + ", smallkind="
				+ smallkind + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + ", lng=" + lng + ", lat=" + lat
				+ "]";
	}
	
}
