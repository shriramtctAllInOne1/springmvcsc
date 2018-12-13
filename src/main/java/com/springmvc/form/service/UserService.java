package com.springmvc.form.service;

import java.util.List;

import com.springmvc.form.model.User;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();

	void saveOrUpdate(User user);
	
	void delete(int id);
	
	User findByLoginId(String id);
	
}