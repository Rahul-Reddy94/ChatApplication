package com.app.controllers;

import javax.validation.Valid;

import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.Service.ProfileService;
import com.app.Service.UserService;
import com.app.model.Profile;
import com.app.model.User;

@Controller
public class ProfileController {

	@Autowired
	UserService userService;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	private PolicyFactory htmlPolicyFactory;
	
	private User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return userService.get(email);
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public ModelAndView viewProfile(ModelAndView modelAndView) {
		modelAndView.setViewName("app.profile");
		User user = getUser();
		Profile profile = profileService.getUserProfile(user);
		
		if(profile == null) {
			profile = new Profile();
			profile.setUser(user);
			profileService.save(profile);
		}
		
		
		Profile webProfile = new Profile(); 
		webProfile.safeCopyFrom(profile);
		 
		modelAndView.getModel().put("profile", profile);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-profile-about", method=RequestMethod.GET)
	public ModelAndView editProfileAbout(ModelAndView modelAndView) {
		
		modelAndView.setViewName("app.editProfile");
		User user = getUser();
		Profile profile = profileService.getUserProfile(user);
		
		Profile webProfile = new Profile(); 
		webProfile.safeCopyFrom(profile);
		
		if(profile != null) {
			modelAndView.getModel().put("editProfile", webProfile);
		}

		return modelAndView;
	}
	
	@RequestMapping(value="/edit-profile-about", method=RequestMethod.POST)
	public ModelAndView editProfileAbout(ModelAndView modelAndView, @Valid @ModelAttribute("editProfile") Profile webProfile, BindingResult result) {
		
		modelAndView.setViewName("app.editProfile");
		User user = getUser();
		Profile profile = profileService.getUserProfile(user);
		
		System.out.println(profile.getId() + " ..... " + profile.getAbout() + "... " + profile.getUser());
		System.out.println(webProfile.getId() + " @@@@@@@@@@@ " + webProfile.getAbout() + "@@@@@@@@@" + webProfile.getUser());

		profile.safeMergeFrom(webProfile,htmlPolicyFactory);
		
		if(!result.hasErrors()) {
			
			profileService.save(profile);
			modelAndView.setViewName("redirect:/profile");
		}

		return modelAndView;
	}

}
