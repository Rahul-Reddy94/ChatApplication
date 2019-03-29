package com.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.model.StatusUpdate;
import com.app.model.StatusUpdateDao;

@Service
public class StatusUpdateService {
	
	private final static int PAGE_SIZE = 3;
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	
	public void save(StatusUpdate lsuPostKey) {
		statusUpdateDao.save(lsuPostKey);
	}
	
	public StatusUpdate findLatest() {
		return statusUpdateDao.findFirstByOrderByAddedDesc();
	}
	
	public Page<StatusUpdate> getPage(int pageNumber){
		PageRequest req = PageRequest.of(pageNumber-1, PAGE_SIZE, Sort.Direction.DESC, "added");
		return statusUpdateDao.findAll(req);
	}

	public void delete(Long id) {
		statusUpdateDao.deleteById(id);
	}

	public Optional<StatusUpdate> get(Long id) {
		return statusUpdateDao.findById(id);
	}
}
