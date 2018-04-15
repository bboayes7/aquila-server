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
		
		System.out.println(proposal.getUser().getId() + " == "+ currentUser.getId());
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
		Long userId = proposal.getUser().getId();
		
		System.out.println(currentUser.getId());
		if(currentUser.getType() == Type.INVESTIGATOR && currentUser.getId() != userId )
			throw new RestException(401, "UNAUTHORIZED");
		
		return economicInterestPIDao.getEconomicInterestPiById( econIntId );
	}
	
	@RequestMapping(value = "/proposal/{propId}/editeconomicinterest/{econIntId}", method = RequestMethod.PUT)
	public EconomicInterestPI updateEconomicInterestPI( @ModelAttribute("currentUser") User currentUser, @PathVariable Long propId,
			@PathVariable Long econIntId, @RequestBody EconomicInterestPI economicInterestPI )
	{
		economicInterestPI.setId(econIntId);
		
		Proposal proposal = proposalDao.getProposal(propId);
		Long userId = proposal.getUser().getId();
		System.out.println(userId);
		
		switch( proposal.getStatus() ){

			case DRAFT : {
				switch(currentUser.getType()){ 
					case INVESTIGATOR :
					case UAS_ANALYST : 
						throw new RestException(401, "UNAUTHORIZED");
						
					case SYSADMIN : 
						economicInterestPI = economicInterestPIDao.saveEconomicInterestPI(economicInterestPI) ;
						break;
				}
			}

			case CANCELLED : {
					switch(currentUser.getType()){
						case INVESTIGATOR :
						case UAS_ANALYST : 
							throw new RestException(401, "UNAUTHORIZED");
							
						case SYSADMIN : 
							economicInterestPI = economicInterestPIDao.saveEconomicInterestPI(economicInterestPI) ;
							break;
					}
			}

			case MEETING : {
					switch(currentUser.getType()){
						case INVESTIGATOR : 
							throw new RestException(401, "UNAUTHORIZED");
						
						case UAS_ANALYST :													
						case SYSADMIN : 
							economicInterestPI = economicInterestPIDao.saveEconomicInterestPI(economicInterestPI) ;
							break;
					}
			}

			case POSTMEETING : {
					switch(currentUser.getType()){
						case INVESTIGATOR :
							if(currentUser.getId() != userId)
								throw new RestException(401, "UNAUTHORIZED");
							
							economicInterestPI = economicInterestPIDao.saveEconomicInterestPI(economicInterestPI) ;
							break;
							
						case UAS_ANALYST :
						case SYSADMIN : 
							economicInterestPI = economicInterestPIDao.saveEconomicInterestPI(economicInterestPI) ;
							break;
					}
			}
			
			case FINAL : {
					switch(currentUser.getType()){
						case INVESTIGATOR : 						
						case UAS_ANALYST : 
							throw new RestException(401, "UNAUTHORIZED");
						
		
						case SYSADMIN : 
							economicInterestPI = economicInterestPIDao.saveEconomicInterestPI(economicInterestPI) ;
							break;
					}
			}
		}
			
		return economicInterestPI ;
	}

}
