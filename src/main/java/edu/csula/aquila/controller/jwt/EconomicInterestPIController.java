package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EconomicInterestPIDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.error.RestException;
import edu.csula.aquila.model.EconomicInterestPI;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.model.User;
import edu.csula.aquila.model.Proposal.Status;
import edu.csula.aquila.model.User.Type;

@RestController
public class EconomicInterestPIController {
	@Autowired
	private EconomicInterestPIDao economicInterestPIDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/{proposalId}/economicinterest", method = RequestMethod.POST)
	public EconomicInterestPI newEconomicInterestPI(@ModelAttribute("currentUser") User currentUser, @PathVariable Long proposalId, @RequestBody EconomicInterestPI economicInterestPI)
	{
		Proposal proposal = proposalDao.getProposal(proposalId);
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getUser().getId() != currentUser.getId() ) 
			throw new RestException(401, "UNAUTHORIZED");
		
		proposal.setEconomicInterestPi(economicInterestPI);
		economicInterestPI.setProposal(proposal);
		return economicInterestPIDao.saveEconomicInterestPI( economicInterestPI );
	}
	
	@RequestMapping(value = "/proposal/{propId}/economicinterest/{econIntId}", method = RequestMethod.GET)
	public EconomicInterestPI getEconomicInterestPIById( @ModelAttribute("currentUser") User currentUser, @PathVariable Long propId,
			@PathVariable Long econIntId )
	{
		
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getUser().getId() != currentUser.getId() )
			throw new RestException(401, "UNAUTHORIZED");
		
		return economicInterestPIDao.getEconomicInterestPiById( econIntId );
	}
	
	@RequestMapping(value = "/proposal/{propId}/editeconomicinterest/{econIntId}", method = RequestMethod.PUT)
	public EconomicInterestPI updateEconomicInterestPI( @ModelAttribute("currentUser") User currentUser, @PathVariable Long propId,
			@PathVariable Long econIntId, @RequestBody EconomicInterestPI economicInterestPI )
	{
		Proposal proposal = proposalDao.getProposal(propId);
		
		if(currentUser.getType() == Type.UAS_ANALYST && proposal.getStatus() != Status.MEETING ) 
			throw new RestException(401, "UNAUTHORIZED");
		
		if(currentUser.getType() == Type.INVESTIGATOR && proposal.getStatus() != Status.DRAFT)
			throw new RestException(403, "FORBIDDEN");
		
		economicInterestPI.setId(econIntId);	
		return economicInterestPIDao.updateEconomicInterestPI( economicInterestPI );
	}

}
