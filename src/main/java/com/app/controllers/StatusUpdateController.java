package com.app.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class StatusUpdateController{

	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping(value = "/viewstatus", method = RequestMethod.GET) 
	ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p",defaultValue="1") int pageNumber) {
 
		Page<StatusUpdate> statusPage= statusUpdateService.getPage(pageNumber);
		/*
		 * List<StatusUpdate> list = page.getContent(); for (StatusUpdate statusUpdate :
		 * list) { System.out.println(statusUpdate.getText() +
		 * "..............................................s"); }
		 */
		modelAndView.setViewName("app.viewStatus");
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
		
//		modelAndView.setViewName("app.addStatus"); 
		
		if(!bindedResult.hasErrors()) {
			statusUpdateService.save(modelPostKey);
			modelAndView.getModel().put("update", new StatusUpdate());
			modelAndView.setViewName("redirect:/viewstatus");
		}
		
		StatusUpdate latestStatusUpdate = statusUpdateService.findLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

		return modelAndView;
	}
	
	@RequestMapping(value = "/deletestatus", method = RequestMethod.GET) 
	ModelAndView deleteStatus(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {
		statusUpdateService.delete(id);
		modelAndView.setViewName("redirect:/viewstatus");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editstatus", method = RequestMethod.GET) 
	ModelAndView editStatus(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {
		Optional<StatusUpdate> statusUpdate = statusUpdateService.get(id);
		modelAndView.setViewName("app.editStatus");
		modelAndView.getModel().put("editStatusUpdateKey", statusUpdate); 
		return modelAndView;
	}
	
	@RequestMapping(value = "/editstatus", method = RequestMethod.POST)
	ModelAndView editStatus(ModelAndView modelAndView, @Valid @ModelAttribute("update") StatusUpdate editStatusUpdateKey, BindingResult bindedResult) {
		
		modelAndView.setViewName("app.editStatus");
		System.out.println("\n "+ editStatusUpdateKey.getAdded() + "***************************************** \n ");
		
		if(!bindedResult.hasErrors()) {
			statusUpdateService.save(editStatusUpdateKey);
			modelAndView.setViewName("redirect:/viewstatus");
		}
		return modelAndView; 
	}
}
