package edu.csula.aquila.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.IntakeDao;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Timeline;

@RestController
public class IntakeController {

	@Autowired
	private IntakeDao intakeDao;
	
	TimelineController timelineController;
	
	//create a new form
	@RequestMapping(value = "intake/", method = RequestMethod.POST)
	public IntakeForm newIntakeForm(@RequestBody IntakeForm intakeForm) {
		
		return intakeDao.saveIntakeForm(intakeForm);
	}

	//update a form
	@RequestMapping(value = "intake/{id}", method = RequestMethod.PUT)
	public IntakeForm updateIntakeForm(@RequestBody IntakeForm intakeForm, @PathVariable Long id) {
		intakeForm.setId(id);
		
		return intakeDao.saveIntakeForm(intakeForm);
	}
	
	//return a form 
	@RequestMapping(value = "intake/{id}", method = RequestMethod.GET)
	public IntakeForm getIntakeForm(@PathVariable Long id) {
		
		return intakeDao.getIntakeForm(id);
	}
}
