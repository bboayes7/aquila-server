package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import edu.csula.aquila.model.EconomicInterestPI;
import edu.csula.aquila.model.EconomicInterestPIDao;

@RestController
public class EconomicInterestPIController {
	@Autowired
	private EconomicInterestPIDao economicInterestPIDao;
	
	@RequestMapping(value = "/proposal/saveeconomicinterest", method = RequestMethod.POST)
	public EconomicInterestPI saveEconomicInterestPI(@RequestBody EconomicInterestPI economicInterestPI)
	{
		return economicInterestPIDao.saveEconomicInterestPI( economicInterestPI );
	}
	
	@RequestMapping(value = "/proposal/economicinterest", method = RequestMethod.GET)
	public EconomicInterestPI getEconomicInterestPIById( @RequestParam Long id )
	{
		return economicInterestPIDao.getEconomicInterestPiById( id );
	}
	
	@RequestMapping(value = "/proposal/editeconomicinterest", method = RequestMethod.PUT)
	public EconomicInterestPI updateEconomicInterestPI( @RequestBody EconomicInterestPI economicInterestPI )
	{
		return economicInterestPIDao.updateEconomicInterestPI( economicInterestPI );
	}

}
