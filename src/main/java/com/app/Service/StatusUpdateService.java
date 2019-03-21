package com.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.StatusUpdate;
import com.app.model.StatusUpdateDao;

@Service
public class StatusUpdateService {
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	
	public void save(StatusUpdate lsuPostKey) {
		statusUpdateDao.save(lsuPostKey);
	}
	
	public StatusUpdate findLatest() {
		return statusUpdateDao.findFirstByOrderByAddedDesc();
	}
}
