package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.ProposalDao;
import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.model.Timeline;

@RestController
public class StageController {
	@Autowired
	private StageDao stageDao;
	
	@Autowired
	private ProposalDao proposalDao;
	
	// Get a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.GET)
	public Timeline.Stage getStage(@PathVariable Long id) {
		return stageDao.getStage(id);
	}

	// create a stage
	@RequestMapping(value = "timeline/proposal/{id}/stage/", method = RequestMethod.POST)
	public Timeline.Stage createStage(Timeline.Stage stage, @PathVariable Long id) {
		Timeline timeline = proposalDao.getProposal(id).getTimeline();
		stage.setTimeline(timeline);
		
		
		return stageDao.createStage(stage);
	}

	// update a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.PUT)
	public Timeline.Stage updateStage(Timeline.Stage stage, @PathVariable Long id) {
		return stageDao.updateStage(stage);
	}

	// delete a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DeleteResponse deleteStage(Timeline.Stage stage, @PathVariable Long id) {
		stage = stageDao.getStage(id);
		stageDao.deleteStage(stage);
		return new DeleteResponse("Stage Deleted!");
	}

	// message to send when a stage is deleted
	public class DeleteResponse {
		private String message;

		public DeleteResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
