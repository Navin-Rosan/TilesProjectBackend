package com.TilesDesign.DTO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TilesDTO {
	private String name;
	private String email;
	private String password;
	private String img;
	private List<Map<String, String>> savedImg;
	public List<Map<String, String>> getSavedImg() {
		return savedImg;
	}
	public void setSavedImg(List<Map<String, String>> savedImg) {
		this.savedImg = savedImg;
	}
	public TilesDTO() {
		super();
	}
	public TilesDTO(String name, String email, String password, String img, List<Map<String, String>> savedImg) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.img = img;
		this.savedImg = savedImg;
	}
	public TilesDTO(String name, String email, String password, String img) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "TilesDTO [name=" + name + ", email=" + email + ", password=" + password + ", img=" + img + ", savedImg="
				+ savedImg + "]";
	}
	
}
