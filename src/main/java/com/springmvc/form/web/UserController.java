package com.springmvc.form.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.form.model.User;
import com.springmvc.form.service.UserService;
import com.springmvc.form.validator.UserFormValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 
	 */
	@Autowired
	UserFormValidator userFormValidator;

	/**
	 * 
	 */
	private UserService userService;

	/**
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	/**
	 * @param userService
	 */
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String submitReg(Model model) {
		logger.info("addUser form");
		List<User> userList=userService.findAll();
		model.addAttribute("users", userList);
		model.addAttribute("userForm", new User());
		return "registration";

	}

	/**
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createAdmin(@ModelAttribute("userForm") User user, Model model,BindingResult result) throws Exception {
		userFormValidator.validate(user, result);
		if(result.hasErrors()){
			model.addAttribute("error", result);
			throw new Exception();
			
		}
		userService.saveOrUpdate(user);
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		List<User> userList1=userService.findAll();
		userList.addAll(userList1);
		model.addAttribute("users", userList);
		model.addAttribute("userForm", new User());
		return "redirect:addUser";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/showuserRegFrom", method = RequestMethod.GET)
	public String showUserRegForm() {
		return "UserRegistration";
	}
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
		logger.debug("showUpdateUserForm() : {}", id);
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		List<User> userList=userService.findAll();
		model.addAttribute("users", userList);
		return "registration";
	}
}