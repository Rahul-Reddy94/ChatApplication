package com.app.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.Service.MailService;
import com.app.Service.UserService;
import com.app.model.User;
import com.app.model.VerificationToken;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
	@Value("${message.registration.cofirmed}")
	private String registrationconfirmedMessage;
	
	@Value("${message.invalid.user}")
	private String invaliduserMessage;
	
	@Value("${message.expired.token}")
	private String expiredtokenMessage;
	
	@RequestMapping("/login")
	String admin() {
		return "app.login"; 
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	ModelAndView register(ModelAndView modelAndView, @ModelAttribute("user") User user) {
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
			String token = userService.createToken(user);
			mailService.sendVerficationEmail(user.getEmail(), token);

			modelAndView.setViewName("redirect:/verifyemail");
		}
		
		return modelAndView;
			
	}
	
	@RequestMapping("/confirmregistration")
	ModelAndView registrationconfirmed(ModelAndView modelAndView, @RequestParam("t") String tokenString) {
		VerificationToken token = userService.getVerificationToken(tokenString);
		
		if(token == null) {
			modelAndView.setViewName("redirect:/invaliduser");
			userService.deleteToken(token);
			return modelAndView;			
		}
		Date expiryDate = token.getExpiryDate();
		
		if(expiryDate.before(new Date())) {
			modelAndView.setViewName("redirect:/expiredtoken");
			userService.deleteToken(token);
			return modelAndView;
		}
	
		User user = token.getUser();
		
		if(user == null) {
			modelAndView.setViewName("redirect:/invaliduser");
			userService.deleteToken(token);
			return modelAndView;			
		}
		
		userService.deleteToken(token);
		user.setEnabled(true);
		userService.save(user);
		modelAndView.getModel().put("message", registrationconfirmedMessage);
		modelAndView.setViewName("app.message");

		return modelAndView;
	}
	
	@RequestMapping("/registrationconfirmed")
	ModelAndView registrationconfirmed(ModelAndView modelAndView) {
		modelAndView.setViewName("app.message");
		modelAndView.getModel().put("message", registrationconfirmedMessage);
		return modelAndView;
	}
	
	@RequestMapping("/expiredtoken")
	ModelAndView expiredtoken(ModelAndView modelAndView) {
		modelAndView.setViewName("app.message");
		modelAndView.getModel().put("message", expiredtokenMessage);
		return modelAndView;
	}
	@RequestMapping("/invaliduser")
	ModelAndView invaliduser(ModelAndView modelAndView) {
		modelAndView.setViewName("app.message");
		modelAndView.getModel().put("message", invaliduserMessage);
		return modelAndView;
	}
	
	@RequestMapping("/errors")
	ModelAndView setErrorPage(ModelAndView modelAndView) {
		modelAndView.setViewName("app.errorPage");
		return modelAndView;
	}

	@RequestMapping("/verifyemail")
	ModelAndView verify(ModelAndView modelAndView) {
		modelAndView.setViewName("app.verifyEmail");
		return modelAndView;
	}
}
