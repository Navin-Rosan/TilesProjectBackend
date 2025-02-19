package com.TilesDesign.entity;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class TilesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_email")
	private String email;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_profile_img", columnDefinition = "LONGTEXT")
	private String img = "../images/user.png";
	@Convert(converter = SavedImgArrayConverter.class)
    @Column(columnDefinition = "json")
    private List<Map<String, String>> savedImg;
	public List<Map<String, String>> getSavedImg() {
		return savedImg;
	}
	public void setSavedImg(List<Map<String, String>> savedImg) {
		this.savedImg = savedImg;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public TilesEntity() {
		super();
	}
	public TilesEntity(long id, String name, String email, String password, String img,
			List<Map<String, String>> savedImg) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.img = img;
		this.savedImg = savedImg;
	}
	public TilesEntity(long id, String name, String email, String password, String img) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.img = img;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "TilesEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", img="
				+ img + ", savedImg=" + savedImg + "]";
	}
	
	
}
