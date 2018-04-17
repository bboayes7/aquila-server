package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.EquipmentForm;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.Proposal.Status;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.User.Type;

@RestController
public class EquipmentController {

	@Autowired
	private EquipmentDao equipmentDao;

	@Autowired
	private ProposalDao proposalDao;

	@RequestMapping(value = "/proposal/{propId}/equipment/{equipId}", method = RequestMethod.GET)
	public EquipmentForm getEquipmentForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long equipId,
			@PathVariable Long propId) {
		Proposal proposal = proposalDao.getProposal(propId);

		if (currentUser.getType() == Type.INVESTIGATOR && !proposal.getUser().getId().equals(currentUser.getId()))
			throw new RestException(401, "UNAUTHORIZED");

		return equipmentDao.getEquipmentForm(equipId);
	}

	@RequestMapping(value = "/proposal/{propId}/equipment/" , method = RequestMethod.POST)
	public EquipmentForm newEquipmentForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long propId,
			@RequestBody EquipmentForm equipmentForm)
	{
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getUser().getId() != currentUser.getId() ) 
			throw new RestException(401, "UNAUTHORIZED");
		
		return equipmentDao.saveEquipmentForm(equipmentForm);
	}
	
	//new update
	@RequestMapping(value= "/proposal/{propId}/equipment/{equipId}", method = RequestMethod.PUT)
	public EquipmentForm updateEquipmentForm(@ModelAttribute("currentUser") User currentUser, 
			@RequestBody EquipmentForm equipmentForm, @PathVariable Long equipId, @PathVariable Long propId)
	{
		Proposal proposal = proposalDao.getProposal(propId);
		
		equipmentForm.setId(equipId);
		switch(proposal.getStatus()){
		//DRAFT
			case DRAFT : {
				switch(currentUser.getType()){
					case INVESTIGATOR : {
						throw new RestException(401, "UNAUTHORIZED");
					}
				
					case UAS_ANALYST : {
						throw new RestException(401, "UNAUTHORIZED");
					}
		
					case SYSADMIN : {
						return equipmentDao.saveEquipmentForm(equipmentForm);
					}	
				}
			}
		//CANCELLED
			case CANCELLED : {
					switch(currentUser.getType()){
						case INVESTIGATOR : {
							throw new RestException(401, "UNAUTHORIZED");
						}
						
						case UAS_ANALYST : {
							throw new RestException(401, "UNAUTHORIZED");
						}
			
						case SYSADMIN : {
							return equipmentDao.saveEquipmentForm(equipmentForm);
						}	
					}
			}
		//MEETING
			case MEETING : {
					switch(currentUser.getType()){
						case INVESTIGATOR : {
							throw new RestException(401, "UNAUTHORIZED");
						}
						
						case UAS_ANALYST : {
							return equipmentDao.saveEquipmentForm(equipmentForm);
						}
			
						case SYSADMIN : {
							return equipmentDao.saveEquipmentForm(equipmentForm);
						}
					}
			}
		//POSTMEETING
			case POSTMEETING : {
					switch(currentUser.getType()){
						case INVESTIGATOR : {
							if(proposal.getUser().getId().equals(currentUser.getId())) {
								return equipmentDao.saveEquipmentForm(equipmentForm);	
							} else {
								throw new RestException(401, "UNAUTHORIZED");
							}
						}
						
						case UAS_ANALYST : {
							return equipmentDao.saveEquipmentForm(equipmentForm);
						}
			
						case SYSADMIN : {
							return equipmentDao.saveEquipmentForm(equipmentForm);
						}
					}
			}
		//FINAL
			case FINAL : {
					switch(currentUser.getType()){
						case INVESTIGATOR : {
							throw new RestException(401, "UNAUTHORIZED");
						}
						
						case UAS_ANALYST : {
							throw new RestException(401, "UNAUTHORIZED");
						}
			
						case SYSADMIN : {
							return equipmentDao.saveEquipmentForm(equipmentForm);
						}	
					}
			}
		}
		
		return null;
	}
	
}
