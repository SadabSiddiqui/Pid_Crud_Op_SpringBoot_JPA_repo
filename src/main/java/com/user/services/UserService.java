package com.user.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.user.controller.HttpStatusCode;
import com.user.entities.Status;
import com.user.entities.User;
import com.user.entities.UserDto;
import com.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// get all users
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	// search users
	public List<User> search(String name, String city, Status status) {
		List<User> user = userRepository.findByNameContainingAndCityAndStatus(name, city, status);
		System.out.println(status);

		System.out.println(user);

		return user;
	}

	public List<Long> search() {
//		int user = userRepository.getBackendPID();
//		System.out.println(user);
//		List<Long> user1 = new ArrayList<>();

		return null;
	}

	// get single user
	public User getUser(Integer userId) {

		return userRepository.findAllById(userId);
	}

	// add user
	public int addUser(User user) {
		int pid = userRepository.findPidByPgBackendPid();
		userRepository.save(user);
		return pid;
	}

	// add multiple user
	public List<User> addUsers(List<UserDto> userDto) {
		List<User> users = new ArrayList<>();
		for (UserDto dto : userDto) {
			User user = new User();
			user.setName(dto.getName());
			user.setCity(dto.getCity());
			user.setStatus(dto.getStatus());
			users.add(user);
		}
		return userRepository.saveAll(users);
	}

	// update user
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public User deleteUser(Integer userId) {
		User user = userRepository.findAllById(userId);
		userRepository.delete(user);
		return user;
	}

//	public HttpStatusCode delPid(Integer pid) {
//		HttpStatusCode httpStatusCode = new HttpStatusCode();
//		deletePid(pid);
//		httpStatusCode.setStatusCode(HttpStatus.OK.value());
//		httpStatusCode.setMessage("Success");
//		return httpStatusCode;
//	}
//
//	private void deletePid(Integer pid) {
//		try {
//			userRepository.deleteByPid(pid);
//		} catch (Exception e) {
//		}
//	}

	public HttpStatusCode delPid(Integer pid) {
		HttpStatusCode httpStatusCode = new HttpStatusCode();
		try {
			userRepository.deleteByPid(pid);
		} catch (Exception e) {}
		httpStatusCode.setStatusCode(HttpStatus.OK.value());
		httpStatusCode.setMessage("Success");
		return httpStatusCode;
	}

}
