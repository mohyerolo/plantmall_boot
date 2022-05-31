package com.example.plantmall.service;

import com.example.plantmall.domain.User;

public interface AuthService {

	User getUser(String email);
	User getUser(String email, String password);
	void insertUser(User user);
	void updateUser(User user);
	void deleteUser(String id);
}
