package com.springmvc.form.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.form.model.Login;
import com.springmvc.form.model.User;
import com.springmvc.form.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * 
	 */
	private UserService userService;

	/**
	 * @return
	 */
	public UserService getUserService() {
		return userService;
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
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new Login());
		return "login";

	}

	/**
	 * @param loginUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.GET)
	public String doLogin(@ModelAttribute("loginForm") Login loginUser,
			Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("users", userList);
		return "list";

	}

	@RequestMapping(value = "/maintenance", method = RequestMethod.GET)
	public String showMaintenance() {
		return "maintenance";
	}

	@RequestMapping(value = "/doLogin1", method = RequestMethod.GET)
	public String doLogin1(@ModelAttribute("loginForm") Login loginUser,
			Model model) {
		User user = new User();
		user.setName("ram");
		user.setEmail("ss");
		model.addAttribute("user", user);
		return "list";

	}

}
