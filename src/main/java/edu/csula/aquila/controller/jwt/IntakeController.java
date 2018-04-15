package edu.csula.aquila.controller.jwt;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.IntakeDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.IntakeForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;

@RestController
public class IntakeController {

	@Autowired
	private IntakeDao intakeDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	//TimelineController timelineController;
	
	//create a new form
	@RequestMapping(value = "/intake", method = RequestMethod.POST)
	public IntakeForm newIntakeForm( @RequestBody IntakeForm intakeForm) 
	{
		
		return intakeDao.saveIntakeForm(intakeForm);
	}

	//update a form
	@RequestMapping(value = "/proposal/{propId}/intake/{intakeId}", method = RequestMethod.PUT)
	public IntakeForm updateIntakeForm(@ModelAttribute("currentUser") User currentUser, @RequestBody IntakeForm intakeForm, 
			@PathVariable Long propId, @PathVariable Long intakeId) 
	{
		intakeForm.setId(intakeId);
		
		Proposal proposal = proposalDao.getProposal(propId);
		Long userId = proposal.getUser().getId();
		System.out.println(userId);
		
		switch( proposal.getStatus() ){

			case DRAFT : {
				switch(currentUser.getType()){ 
					case UAS_ANALYST : 
						throw new RestException(401, "UNAUTHORIZED");
					
					case INVESTIGATOR :
						System.out.println(currentUser.getId());
						if(currentUser.getId() != userId) 
							throw new RestException(401, "UNAUTHORIZED");
					
						intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
						break;
						
					case SYSADMIN : 
						intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
						break;
				}
			}

			case CANCELLED : {
					switch(currentUser.getType()){
						case INVESTIGATOR :
						case UAS_ANALYST : 
							throw new RestException(401, "UNAUTHORIZED");
							
						case SYSADMIN : 
							intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
							break;
					}
			}

			case MEETING : {
					switch(currentUser.getType()){
						case INVESTIGATOR : 
							throw new RestException(401, "UNAUTHORIZED");
						
						case UAS_ANALYST :													
						case SYSADMIN : 
							intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
							break;
					}
			}

			case POSTMEETING : {
					switch(currentUser.getType()){
						case INVESTIGATOR :
							if(currentUser.getId() != userId)
								throw new RestException(401, "UNAUTHORIZED");
							
							intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
							break;
							
						case UAS_ANALYST :
						case SYSADMIN : 
							intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
							break;
					}
			}
			
			case FINAL : {
					switch(currentUser.getType()){
						case INVESTIGATOR : 						
						case UAS_ANALYST : 
							throw new RestException(401, "UNAUTHORIZED");
						
		
						case SYSADMIN : 
							intakeForm = intakeDao.saveIntakeForm(intakeForm) ;
							break;
					}
			}
		}

		
		return intakeForm;
	}
	
	//return a form 
	@RequestMapping(value = "/proposal/{propId}/intake/{intakeId}", method = RequestMethod.GET)
	public IntakeForm getIntakeForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long propId, @PathVariable Long intakeId) 
	{
		Proposal proposal = proposalDao.getProposal(propId);
		Long userId = proposal.getUser().getId();
		
		System.out.println(currentUser.getId() + " -- " + userId + " "+ currentUser.getType());
		if(currentUser.getType() == Type.INVESTIGATOR && currentUser.getId() != userId )
			throw new RestException(401, "UNAUTHORIZED");
		
		return intakeDao.getIntakeForm(intakeId);
	}
}
