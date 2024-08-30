package com.tr.controllers;

import java.util.Optional;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tr.entity.AppResponse;
import com.tr.entity.Post;
import com.tr.entity.User;
import com.tr.exceptions.DuplicateException;
import com.tr.exceptions.NotFoundException;
import com.tr.repositories.PostRepository;
import com.tr.repositories.UserRepository;

import ch.qos.logback.classic.*;

import java.util.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse.List;

@RestController
@RequestMapping("Socialmedia")
public class UserController {
	
	
	Logger log=(Logger) org.slf4j.LoggerFactory.getLogger(User.class);
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	PostRepository postrepo;
	
	@PostMapping("save")
	public ResponseEntity<AppResponse> saveUser(@Valid @RequestBody User user){
		
		if(userrepo.existsById(user.getUserid())) {
			throw new DuplicateException("Failed", "User with Id "+user.getUserid()+" already exist");
		}else {
			userrepo.save(user);
			return new ResponseEntity<AppResponse>(new AppResponse("Success", "user with id "+user.getUserid()+" saved successfully"), HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/{id}")
	public User getUserOne(@PathVariable int id) {
		Optional<User> opt=userrepo.findById(id);
		if(!opt.isPresent()) {
			throw new NotFoundException("404", "User with Id "+id+" not found");
		}else {
			return opt.get();
		}
	}
	
	@GetMapping("/users")
	public java.util.List<User> getAllUser(){
		java.util.List<User> list=userrepo.findAll();
		log.info("Fetching all the user details");
		return list;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponse> deleteUser(@PathVariable int id) {
		Optional<User> opt=userrepo.findById(id);
		if(!opt.isPresent()){
			throw new NotFoundException("Not Found", "User with Id "+id+" not found");
		}else {
			userrepo.deleteById(id);
			return new ResponseEntity<AppResponse>(new AppResponse("Success", "user with id "+id+" deleted successfully"), HttpStatus.OK);
		}
	}
	
	@PostMapping("/post/{id}/posts")
	public ResponseEntity<AppResponse> savePoseUser(@PathVariable int id, @Valid @RequestBody  Post post){
		Optional<User> opt=userrepo.findById(id);
		if(opt.isEmpty()) {
			throw new NotFoundException("Not Found", "User with Id "+id+" Not found");
		}else if(postrepo.existsById(post.getId())){
			throw new DuplicateException("Failed", "Post with Id "+post.getId()+" already exist");
		}
		else{
			post.setUser(opt.get());
			postrepo.save(post);
			return new ResponseEntity<AppResponse>(new AppResponse("Success", "Post with id "+post.getId()+" saved successfully for User "+opt.get().getUserid()), HttpStatus.OK);
		}
	}
	
	@GetMapping("/user/{id}/posts")
	public java.util.List<Post> getUserPosts(@PathVariable int id) {
		Optional<User> opt=userrepo.findById(id);
		if(!opt.isPresent()) {
			throw new NotFoundException("Not Found", "User with Id "+id+" not found");
		}else {
			return opt.get().getPosts();
		}
	}
	
	@GetMapping("/search")
	public java.util.List<User> searchByName(@RequestParam String query){
		log.info("Fetching the user details with given character");
		return userrepo.findByNameStartingWithIgnoreCase(query);
		//http://localhost:8080/Socialmedia/search?query=a
	}
	
}
