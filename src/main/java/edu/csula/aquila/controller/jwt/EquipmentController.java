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
	
	@RequestMapping(value = "/proposal/{propId}/equipment/{id}", method = RequestMethod.GET)
	public EquipmentForm getEquipmentForm(@ModelAttribute("currentUser") User currentUser, @PathVariable Long id, @PathVariable Long propId) 
	{
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getUser().getId() != currentUser.getId() )
			throw new RestException(401, "UNAUTHORIZED");
				
		return equipmentDao.getEquipmentForm(id);
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
	
	@RequestMapping(value= "/proposal/{propId}/equipment/{id}", method = RequestMethod.PUT)
	public EquipmentForm updateEquipmentForm(@ModelAttribute("currentUser") User currentUser, 
			@RequestBody EquipmentForm equipmentForm, @PathVariable Long id, @PathVariable Long propId)
	{
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.UAS_ANALYST && proposal.getStatus() != Status.MEETING ) 
			throw new RestException(401, "UNAUTHORIZED");
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getStatus() != Status.DRAFT)
			throw new RestException(403, "FORBIDDEN");
		
		equipmentForm.setId(id);	
		return equipmentDao.saveEquipmentForm(equipmentForm);
	}
	
}
