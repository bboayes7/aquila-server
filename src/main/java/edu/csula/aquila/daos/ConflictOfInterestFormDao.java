package edu.csula.aquila.daos;

import edu.csula.aquila.model.ConflictOfInterestForm;

public interface ConflictOfInterestFormDao {
	
	public ConflictOfInterestForm getConflictOfInterestFormById( Long id );
	
	public ConflictOfInterestForm saveConflictOfInterestForm( ConflictOfInterestForm coi);
	
	public ConflictOfInterestForm updateConflictOfInterestForm( ConflictOfInterestForm coi);
	

}
