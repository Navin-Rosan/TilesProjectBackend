package com.TilesDesign.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TilesDesign.DTO.TilesDTO;
import com.TilesDesign.entity.TilesEntity;
import com.TilesDesign.repo.TilesRepositery;

@Service
public class TilesService {
	
	@Autowired
	private TilesRepositery repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String registerUser(TilesDTO tilesdto) {
		if(repo.findByName(tilesdto.getName()) != null) {
			return "user name already exist";
		}
		TilesEntity user = new TilesEntity();
		user.setName(tilesdto.getName());
		user.setEmail(tilesdto.getEmail());
		user.setPassword(passwordEncoder.encode(tilesdto.getPassword()));
		repo.save(user);
		return "user successfully registered";
	}
	
	public boolean loginUser(TilesDTO userDto) {
		TilesEntity user = repo.findByEmail(userDto.getEmail());
		if(user == null)
			return false;
		return passwordEncoder.matches(userDto.getPassword(),user.getPassword());
	}
	
	
	public void updateUserImg(String email, TilesDTO url) {
		TilesEntity user = repo.findByEmail(email);
		if(user != null) {
			user.setImg(url.getImg());
			repo.save(user);
		}
	}
	
	public String saveImage(String email, Map<String, String> imgData) {
		TilesEntity user = repo.findByEmail(email);
		
		if(user != null) {
			List<Map<String, String>> savedImg = user.getSavedImg();
			
			if(savedImg == null) {
				savedImg = new ArrayList<>();
			}
			
			boolean titleExist = savedImg.stream().anyMatch(img -> img.get("title").equals(imgData.get("title")));
			if(titleExist)
				return "Texture title already exist";
			
			savedImg.add(imgData);
			user.setSavedImg(savedImg);
			repo.save(user);
			return "user favorite texture saved succefully";
		}
		
		else {
			return "user not found";
		}
	}
	
	public List<Map<String, String>> getSavedImg(String email) {
		TilesEntity user = repo.findByEmail(email);
		return user.getSavedImg();
	}
	
	public String deleteSavedImg(String email, String title) {
		TilesEntity user = repo.findByEmail(email);
		if (user == null) {
			return "User not found";
		}
		List<Map<String, String>> savedImg = user.getSavedImg();
		if (savedImg == null || savedImg.isEmpty()) {
			return "No saved images found for this user";
		}
		boolean isRemove = savedImg.removeIf(img -> title.equals(img.get("title")));
		if(isRemove) {
			user.setSavedImg(savedImg);
			repo.save(user);
			return "img delete successfully";
		}
		else
			return "img not found" + title;
	}
	
	
	public String updatePassword(TilesDTO data) {
		TilesEntity user = repo.findByEmail(data.getEmail());
		if(user == null)
			return "This email is not register";
			
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		repo.save(user);
		return "Password changed successfully";
	}
	
	
	
	
	
}
