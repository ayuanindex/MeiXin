package com.ayuan.vo;

public class Vegetableinfo {
	private String typepic;//图片路径
	private String description;//描述
	private String typeid;//序列id
	private String typename;//名字

	public Vegetableinfo() {
	}

	public Vegetableinfo(String typepic, String description, String typeid, String typename) {
		this.typepic = typepic;
		this.description = description;
		this.typeid = typeid;
		this.typename = typename;
	}

	public String getTypepic() {
		return typepic;
	}

	public void setTypepic(String typepic) {
		this.typepic = typepic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
}
