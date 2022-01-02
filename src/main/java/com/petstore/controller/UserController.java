package com.petstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petstore.entity.User;
import com.petstore.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/createWithList")
	public ResponseEntity<?> addAllUsers(@RequestBody List<User> users){
		for(User user : users) {
			userRepository.save(user);
		}
	
		return ResponseEntity.ok("All users added");
	}
	@PostMapping("")
	public ResponseEntity<?> addUser(@RequestBody User user){
		
		User localUser = userRepository.save(user);
		return ResponseEntity.ok(localUser);
	}
	@GetMapping("login")
	public ResponseEntity<?> userLogin(@RequestParam String username, @RequestParam String password) {
		String message = "";
		User user = userRepository.findByUsername(username);
		if(user!=null) {
			if(password.equals(user.getPassword())) {
				message = "Login success";
			}else {
				message = "Invalid password...";
			}
		}else {
			message = "Invalid username...";
		}

		return ResponseEntity.ok(message);
	}
	
	@PutMapping("/{userName}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
		User localUser = userRepository.findByUsername(userName);
		if(localUser!=null) {
			localUser.setEmail(user.getEmail());
			localUser.setFirstName(user.getFirstName());
			localUser.setLastName(user.getLastName());
			localUser.setPassword(user.getPassword());
			localUser.setPhone(user.getPhone());
			userRepository.save(localUser);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(localUser);
	}
	
	@DeleteMapping("/{userName}")
	public ResponseEntity<?> deleteUser(@PathVariable String userName){
		User localUser = userRepository.findByUsername(userName);
		if(localUser!=null) {
			userRepository.deleteById(localUser.getId());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found...");
		}
		return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully...");
	}
	
}
