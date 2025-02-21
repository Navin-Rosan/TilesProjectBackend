package com.TilesDesign.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

import com.TilesDesign.DTO.TilesDTO;
import com.TilesDesign.entity.TilesEntity;
import com.TilesDesign.repo.TilesRepositery;
import com.TilesDesign.service.TilesService;

@RestController
@RequestMapping("/design")
@CrossOrigin(origins = "*")
public class TilesController {
	
	@Autowired
	private TilesService service;
	@Autowired
	private TilesRepositery repo;
	@PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody TilesDTO userdto) {
        Map<String, Object> response = new HashMap<>();

        if (userdto.getName() == null || userdto.getName().isEmpty()) {
            response.put("message", "Invalid username or empty");
            return ResponseEntity.badRequest().body(response);
        }

        String result = service.registerUser(userdto);

        if ("user successfully registered".equals(result)) {
            response.put("message", result);
            response.put("user", userdto);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", result);
            return ResponseEntity.ok().body(response);
        }
    }
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody TilesDTO userDto) {
		Map<String, Object> response = new HashMap<>();
		boolean isAunthenticated = service.loginUser(userDto);
		if(isAunthenticated) {
			TilesEntity user = repo.findByEmail(userDto.getEmail());
			response.put("message", "user login successfully");
			response.put("user", user);
			return ResponseEntity.ok(response);
		}
		else {
			response.put("message", "invalid email or password");
			return ResponseEntity.ok(response);
		}
//		return service.loginUser(userDto) ? "" : "";
	}

	@PutMapping("/public/img/{email}")
	public void updateUserImg(@PathVariable String email, @RequestBody TilesDTO url) {
		service.updateUserImg(email, url);
//		TilesEntity user = repo.findByEmail(email);
//		user.setImg(url.getImg());
//		repo.save(user);
	}
	
	@PostMapping("/public/save-img/{email}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String saveImage(@PathVariable String email, @RequestBody Map<String, String> imgData) {
		return service.saveImage(email, imgData);
	}
	
	@GetMapping("/public/get-save-img/{email}")
	public List<Map<String, String>> getSavedImg(@PathVariable String email) {
		return service.getSavedImg(email);
	}
	
	@DeleteMapping("/public/delete-save-img/{email}/{title}")
	public String deleteSavedImg(@PathVariable String email, @PathVariable String title) {
		return service.deleteSavedImg(email, title);
	}
	@PostMapping("/public/email/{email}")
	public String email(@PathVariable String email) {
		TilesEntity user = repo.findByEmail(email);
		if(user != null)
			return "success";
		return "This email is not register";
	}
	@PutMapping("/public/update-password")
	public String updatePassword(@RequestBody TilesDTO data) {
		return service.updatePassword(data);
	}
	
	
}
