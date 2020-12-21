package com.ssafy.happyhouse.model;

public class NoticeDto {
	private int no;
	private String title;
	private String author;
	private String content;
	private String regtime;
	private int views;
	
	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", title=" + title + ", author=" + author + ", content=" + content + ", regtime="
				+ regtime + ", views=" + views + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
}
