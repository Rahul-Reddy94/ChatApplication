package com.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorControllers {

	@RequestMapping(value="/403")
	ModelAndView accessDenied(ModelAndView modelAndView) {
		modelAndView.setViewName("app.errorPage");
		modelAndView.getModel().put("errorMessage", "ACCESS IS DENIED");
		return modelAndView;
	}
	
	@RequestMapping(value="/404")
	ModelAndView notFound(ModelAndView modelAndView) {
		modelAndView.setViewName("app.errorPage");
		modelAndView.getModel().put("errorMessage", "We Could not find your resource");
		return modelAndView;
	}
	
}
