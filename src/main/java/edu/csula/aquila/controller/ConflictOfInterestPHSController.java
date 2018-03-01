package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestPHSDao;
import edu.csula.aquila.model.ConflictOfInterestPHS;

@RestController
public class ConflictOfInterestPHSController {
	
	@Autowired
	private ConflictOfInterestPHSDao conflictOfInterestPHSDao;
	
	@RequestMapping(value = "/proposal/coiphs/{id}", method = RequestMethod.GET)
	public ConflictOfInterestPHS getConflictOfInterestPHSById( @PathVariable Long id )
	{
		return conflictOfInterestPHSDao.getConflictOfInterestPHSById(id);
	}
	
	@RequestMapping(value = "/proposal/savecoiphs", method = RequestMethod.POST)
    public ConflictOfInterestPHS saveConflictOfInterestPHS( @RequestBody ConflictOfInterestPHS coiPHS )
    {
    	return conflictOfInterestPHSDao.saveConflictOfInterestPHS( coiPHS );
    			
    }
	
	@RequestMapping(value = "/proposal/editcoiphs", method = RequestMethod.PUT)
    public ConflictOfInterestPHS updateConflictOfInterestPHS( @RequestBody ConflictOfInterestPHS coiPHS )
    {
    	return conflictOfInterestPHSDao.updateConflictOfInterestPHS( coiPHS );
    			
    }

}
