package com.app.extras;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class ExceptionHandlers {
	
	@Value("${exception.mainMsg}" )
	private String exceptionMessage;

	@Value("${exception.duplicateUsersMsg}" )	
	private String duplicateUserMessage;

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("errorMessage", exceptionMessage);
		modelAndView.getModel().put("url", req.getRequestURL());
		modelAndView.getModel().put("exceptionTrace", e.getStackTrace());
		modelAndView.setViewName("app.errorPage");
		return modelAndView;
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ModelAndView constraintValidationErrorHandler(HttpServletRequest req, Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.getModel().put("errorMessage", duplicateUserMessage);
		modelAndView.getModel().put("url", req.getRequestURL());
		modelAndView.getModel().put("exceptionTrace", e.getStackTrace());
		modelAndView.setViewName("app.errorPage");
		return modelAndView;
	}

	
}