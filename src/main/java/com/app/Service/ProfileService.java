package com.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Profile;
import com.app.model.ProfileDao;
import com.app.model.User;

@Service
public class ProfileService {

	@Autowired
	ProfileDao profileDao;

	public void save(Profile profile) {
		profileDao.save(profile);
	 }
	
	public Profile getUserProfile(User user) {
		return profileDao.findByUser(user);
	}
}
