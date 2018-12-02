package com.ayuan.vo;

public class DishesBean {
	private String spic;
	private int notlikes;
	private int likes;
	private String menuname;
	private int menuid;

	public DishesBean() {
	}

	public DishesBean(String spic, int notlikes, int likes, String menuname, int menuid) {
		this.spic = spic;
		this.notlikes = notlikes;
		this.likes = likes;
		this.menuname = menuname;
		this.menuid = menuid;
	}

	public String getSpic() {
		return spic;
	}

	public void setSpic(String spic) {
		this.spic = spic;
	}

	public int getNotlikes() {
		return notlikes;
	}

	public void setNotlikes(int notlikes) {
		this.notlikes = notlikes;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public int getMenuid() {
		return menuid;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	@Override
	public String toString() {
		return "DishesBean{" +
				"spic='" + spic + '\'' +
				", notlikes=" + notlikes +
				", likes=" + likes +
				", menuname='" + menuname + '\'' +
				", menuid=" + menuid +
				'}';
	}
}
