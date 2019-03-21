package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.app.App;
import com.app.model.StatusUpdate;
import com.app.model.StatusUpdateDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@Transactional
public class StatusTests {

	@Autowired
	private StatusUpdateDao statusDao;
	
	@Test
	public void testDummy() {
		
		StatusUpdate status = new StatusUpdate("Just Update 3");
		statusDao.save(status);
		assertNotNull("id", status.getId()); 
		assertNotNull("address", status.getAdded());
		
		System.out.println("************" + status.getId() + "********************");
		
		StatusUpdate retrieved = statusDao.findById(status.getId()).get();
		if(status.equals(retrieved)) {
			System.out.println("true");
		}else {
			System.out.println("false");

		}
		assertEquals("Matching status update", status,retrieved);
	}
	
	@Test
	public void testFindLatest() {
		
		Calendar calendar = Calendar.getInstance();
		
		StatusUpdate lastStatusUpdate = null;
		
		for(int i=0; i<10; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			
			StatusUpdate status = new StatusUpdate("Status update " + i, calendar.getTime());
			
			statusDao.save(status);
			
			lastStatusUpdate = status;
		}
		
		StatusUpdate retrieved = statusDao.findFirstByOrderByAddedDesc();
		
		assertEquals("Latest status update", lastStatusUpdate, retrieved);
	}
}
