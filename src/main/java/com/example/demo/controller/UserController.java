package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
    //  to fetch all the records from database
	 @GetMapping("/all") 
	  public List<User> getAllUsers() {
	  logger.info("Getting all users");
	  return userRepository.findAll(); 
	  
	   }
	 
	 //  for add the new records in mongodb database
	 @PostMapping("/add")
	 public String saveUser(@RequestBody User u) {
		 userRepository.insert(u);
		 return "Added Employee Id" + u.getUserId();
	 }

	 //  To find the particular records from database
	 @GetMapping(value = "/{id}")
	 public User getOne(@PathVariable String id) {
	     return userRepository.findById(id)
	         .orElseThrow(() -> new ResourceNotFoundException());
	 }
	 
	
	 // for update the records in mongodb 
	 
	 @PutMapping(value = "/{id}")
	 public User update(@PathVariable String id, @RequestBody User updatedUser) {
	     User user = userRepository.findById(id)
	         .orElseThrow(() -> new ResourceNotFoundException());
	       //user.setName(updatedUser.getName());
	       user.setSkill(updatedUser.getSkill());
	       return userRepository.save(user);
	     
	      }
	 
	 
	 //  delete the records of particular id 
	 @DeleteMapping("/{id}")
	 @ResponseStatus(value = HttpStatus.ACCEPTED)
	 public void deleteUser(@PathVariable String id) {
		 User user = userRepository.findById(id)
		         .orElseThrow(() -> new ResourceNotFoundException());
		   userRepository.delete(user);
		 
		 
	 }
	 
}
