package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EconomicInterestPIDao;
import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.model.EconomicInterestPI;
import edu.csula.aquila.model.Proposal;

@RestController
public class EconomicInterestPIController {
	@Autowired
	private EconomicInterestPIDao economicInterestPIDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	// get economic interest
	@RequestMapping(value = "/proposal/economicinterest/{id}", method = RequestMethod.GET)
	public EconomicInterestPI getEconomicInterestPIById( @PathVariable Long id )
	{
		return economicInterestPIDao.getEconomicInterestPiById( id );
	}
	// update economic interest
	@RequestMapping(value = "/proposal/editeconomicinterest/{id}", method = RequestMethod.PUT)
	public EconomicInterestPI updateEconomicInterestPI( @RequestBody EconomicInterestPI economicInterestPI, @PathVariable Long id )
	{
		economicInterestPI.setId(id);
		return economicInterestPIDao.updateEconomicInterestPI( economicInterestPI );
	}

}
