package com.springmvc.form.dao;

import java.util.List;

import com.springmvc.form.model.User;

public interface UserDao {

	/**
	 * @param id
	 * @return
	 */
	User findById(Integer id);

	/**
	 * @return
	 */
	List<User> findAll();

	/**
	 * @param user
	 */
	void save(User user);

	/**
	 * @param user
	 */
	void update(User user);

	/**
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * @param LoginId
	 * @return
	 */
	User findByLoginId(String LoginId);

}