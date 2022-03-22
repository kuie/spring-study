package com.example.spring_study;

//데이터 오브젝트 
public class News {
	private int aid;
	private String title;
	private String img;
	private String time;
	private String content;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return " [" + aid + "," + title + ", " + img + ", " + time + ", " + content	+ "]";
	}

	
}
