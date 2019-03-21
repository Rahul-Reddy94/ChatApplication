package com.app.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.Service.StatusUpdateService;
import com.app.model.StatusUpdate;

@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/")
	String home() {
		return "app.homepage";
	}
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
	@RequestMapping("/admin")
	String admin() {
		
		return "app.admin";
	}
	
	@RequestMapping(value = "/addstatus", method = RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView) {
		
		modelAndView.setViewName("app.addStatus");
		StatusUpdate statusUpdate = new StatusUpdate();
		//StatusUpdate latestStatusUpdate = (StatusUpdate) modelAndView.getModel().get("lsuGetKey");
		StatusUpdate latestStatusUpdate = statusUpdateService.findLatest();
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		//modelAndView.getModel().put("lsuGetKey", latestStatusUpdate);
		//StatusUpdate latestStatusUpdate = (StatusUpdate) modelAndView.getModel().get("lsuGetKey");
		return modelAndView;
	}
	
	@RequestMapping(value = "/addstatus", method = RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, StatusUpdate lsuPostKey) {
		
		modelAndView.setViewName("app.addStatus"); 
		statusUpdateService.save(lsuPostKey);
		
		StatusUpdate latestStatusUpdate = statusUpdateService.findLatest();
		modelAndView.getModel().put("lsuGetKey", latestStatusUpdate);
		return modelAndView;
	}
}
