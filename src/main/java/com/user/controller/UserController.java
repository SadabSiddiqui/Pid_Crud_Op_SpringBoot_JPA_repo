package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.entities.Status;
import com.user.entities.User;
import com.user.entities.UserDto;
import com.user.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@DeleteMapping("/delete_pid/{pid}")
	public HttpStatusCode pid(@PathVariable Integer pid) {
		return service.delPid(pid);
	}

	// get all users by Get request
	@GetMapping("/users")
	public List<User> users() {
		return service.getUsers();
	}

	// search user
	@GetMapping("/search/{keyword}")
	public List<User> search(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "status", required = false) Status status) {
		System.out.println(name + " " + city + " " + status);
		List<User> user = service.search(name, city, status);

		return user;
	}

	@GetMapping("/search")
	public List<Long> searchData() {
		List<Long> user = service.search();

		return user;
	}

	// get single user by Get request
	@GetMapping("/user/{userId}")
	public User user(@PathVariable Integer userId) {
		return service.getUser(userId);
	}

	// add user by Post request
	@PostMapping("/user")
	public int user(@RequestBody User user) {
		return service.addUser(user);
	}

	// add multiple users by Post request
	@PostMapping("/users")
	public List<User> users(@RequestBody List<UserDto> users) {
		return service.addUsers(users);
	}

	// update user by Put request
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	// delete user by Delete request
	@DeleteMapping("/user/{userId}")
	public User deleteUser(@PathVariable Integer userId) {
		return service.deleteUser(userId);
	}
}
