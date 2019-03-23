package com.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/viewstatus", method = RequestMethod.GET) 
	ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p",defaultValue="1") int pageNumber) {
 
		Page<StatusUpdate> statusPage= statusUpdateService.getPage(pageNumber);
		/*
		 * List<StatusUpdate> list = page.getContent(); for (StatusUpdate statusUpdate :
		 * list) { System.out.println(statusUpdate.getText() +
		 * "..............................................s"); }
		 */
		modelAndView.setViewName("app.viewstatus");
		modelAndView.getModel().put("statusPageKey", statusPage);

		
		return modelAndView;
	}
	
	@RequestMapping(value = "/addstatus", method = RequestMethod.GET) 
	ModelAndView addStatus(ModelAndView modelAndView,  @ModelAttribute("update") StatusUpdate modelGetKey) {

		modelAndView.setViewName("app.addStatus");
		StatusUpdate latestStatusUpdate = statusUpdateService.findLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/addstatus", method = RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, @Valid @ModelAttribute("update") StatusUpdate modelPostKey, BindingResult bindedResult) {
		
		modelAndView.setViewName("app.addStatus"); 
		
		if(!bindedResult.hasErrors()) {
			statusUpdateService.save(modelPostKey);
			modelAndView.getModel().put("update", new StatusUpdate());
		}
		
		StatusUpdate latestStatusUpdate = statusUpdateService.findLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		return modelAndView;
	}
}

//modelAndView.getModel().put("update", new StatusUpdate());

//System.out.println("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
//System.out.println("888888888888888888888888888888888888888888888888888888888888888888888888888888888888");
//System.out.println("**********************************************************************************");
//System.out.println("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
