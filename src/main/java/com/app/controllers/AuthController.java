package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.Service.UserService;
import com.app.model.User;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	String admin() {
		return "app.login"; 
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView, @ModelAttribute("user") User user) {
		System.out.println("I AM HERE IN GET ************************************************************");
		modelAndView.setViewName("app.register");
		modelAndView.getModel().put("user", user);
		return modelAndView;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	ModelAndView register(ModelAndView modelAndView, @Valid @ModelAttribute("user") User user,BindingResult result) {
	
		modelAndView.setViewName("app.register");
		
		if(!result.hasErrors()) { 	
			System.out.println("FUCKKK NO ERRORS****************************************************************************");
			userService.register(user);
			System.out.println("SAVED USER ****************************************************************************");
			modelAndView.setViewName("redirect:/about");
		}
		
		return modelAndView; 
			
	}


}
