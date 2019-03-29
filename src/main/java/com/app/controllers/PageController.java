package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.Service.StatusUpdateService;
import com.app.model.StatusUpdate;

@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/")
	ModelAndView home(ModelAndView modelAndView) {
		StatusUpdate homePageStatusUpdate = statusUpdateService.findLatest();
		System.out.println(homePageStatusUpdate.getText());
		modelAndView.setViewName("app.homepage");
		modelAndView.getModel().put("homePageStatusUpdateKey", homePageStatusUpdate);
		return modelAndView; 
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
	

}

//modelAndView.getModel().put("update", new StatusUpdate());

//System.out.println("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
//System.out.println("888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
//System.out.println("**********************************************************************************");
//System.out.println("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
