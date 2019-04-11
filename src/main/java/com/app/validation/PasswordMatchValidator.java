package com.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.model.User;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, User>{

	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {
		String plainPassword = value.getPlainPassword();
		String repeatPassword = value.getRepeatPassword();
		
		if(plainPassword == null || plainPassword.length() == 0) {
			return true;
		}
		
		if(plainPassword!=null && plainPassword.equals(repeatPassword)) {
			return true;
		}
		
		return false;
	}

}
