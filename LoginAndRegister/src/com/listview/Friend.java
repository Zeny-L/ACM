package com.listview;

public class Friend {

	private int imag;
	private String title;
	private String info;

	public Friend(int imag, String title, String info) {
		this.imag = imag;
		this.title = title;
		this.info = info;
	}

	public int getImage() {
		return imag;
	}

	public void setImage(int imag) {
		this.imag = imag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
