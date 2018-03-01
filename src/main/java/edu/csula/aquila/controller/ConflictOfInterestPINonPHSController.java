package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestPINonPHSDao;
import edu.csula.aquila.model.ConflictOfInterestPINonPHS;

@RestController
public class ConflictOfInterestPINonPHSController {

	@Autowired
	private ConflictOfInterestPINonPHSDao conflictOfInterestPINonPHSDao;
	
	@RequestMapping(value = "/proposal/coipinonphs/{id}", method = RequestMethod.GET)
	public ConflictOfInterestPINonPHS getConflictOfInterestPINonPHSById( @PathVariable Long id )
	{
		return conflictOfInterestPINonPHSDao.getConflictOfInterestPINonPHSById(id);
	}
	
	@RequestMapping(value = "/proposal/savecoipinonphs", method = RequestMethod.POST)
    public ConflictOfInterestPINonPHS saveConflictOfInterestPINonPHS( @RequestBody ConflictOfInterestPINonPHS coiPINonPHS )
    {
    	return conflictOfInterestPINonPHSDao.saveConflictOfInterestPINonPHS( coiPINonPHS );
    			
    }
	
	@RequestMapping(value = "/proposal/editcoipinonphs", method = RequestMethod.PUT)
    public ConflictOfInterestPINonPHS updateConflictOfInterestPINonPHS( @RequestBody ConflictOfInterestPINonPHS coiPINonPHS )
    {
    	return conflictOfInterestPINonPHSDao.updateConflictOfInterestPINonPHS( coiPINonPHS );
    			
    }
	
}
