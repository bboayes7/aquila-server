package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.model.Timeline.Stage;

@RestController
public class StageController {
	
	@Autowired
	private StageDao stageDao;
	
	
	@RequestMapping(value = "/timeline/stage/{id}" , method = RequestMethod.GET)
	public Stage getStage( @PathVariable Long id )
	{
		return stageDao.getStage(id);
	}
	
	@RequestMapping(value = "" , method = RequestMethod.PUT)
	public Stage newStage( @RequestBody Stage stage )
	{
		return stageDao.saveStage(stage);
	}
	
	@RequestMapping(value = "" , method = RequestMethod.POST)
	public Stage updateStage( @RequestBody Stage stage )
	{
		return stageDao.saveStage(stage);
	}

}
