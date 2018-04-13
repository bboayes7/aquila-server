package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ConflictOfInterestKPNonPHSDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.model.ConflictOfInterestKPNonPHS;
import edu.csula.aquila.model.Proposal;

@RestController
public class ConflictOfInterestKPNonPHSController {
	
	@Autowired
	private ConflictOfInterestKPNonPHSDao conflictOfInterestKPNonPHSDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	@RequestMapping(value = "/proposal/coikpnonphs/{id}", method = RequestMethod.GET)
	public ConflictOfInterestKPNonPHS getConflictOfInterestKPNonPHSById( @PathVariable Long id )
	{
		return conflictOfInterestKPNonPHSDao.getConflictOfInterestKPNonPHSById(id);
	}
	
	@RequestMapping(value = "/proposal/{proposalId}/savecoikpnonphs", method = RequestMethod.POST)
    public ConflictOfInterestKPNonPHS saveConflictOfInterestKPNonPHS( @PathVariable Long proposalId, @RequestBody ConflictOfInterestKPNonPHS coiKPNonPHS )
    {
		Proposal proposal = proposalDao.getProposal(proposalId);
		proposal.setCoiKpNonPhs(coiKPNonPHS);
		coiKPNonPHS.setProposal(proposal);
    	return conflictOfInterestKPNonPHSDao.saveConflictOfInterestKPNonPHS( coiKPNonPHS );
    			
    }
	
	@RequestMapping(value = "/proposal/editcoikpnonphs", method = RequestMethod.PUT)
    public ConflictOfInterestKPNonPHS updateConflictOfInterestKPNonPHS( @RequestBody ConflictOfInterestKPNonPHS coiKPNonPHS )
    {
    	return conflictOfInterestKPNonPHSDao.updateConflictOfInterestKPNonPHS( coiKPNonPHS );
    			
    }


}
