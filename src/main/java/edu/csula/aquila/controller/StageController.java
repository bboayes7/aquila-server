package edu.csula.aquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.daos.TimelineDao;
import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

@RestController
public class StageController {
	@Autowired
	private StageDao stageDao;
	
	@Autowired
	private TimelineDao timelineDao;
	
	// Get a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.GET)
	public Stage getStage(@PathVariable Long id) {
		return stageDao.getStage(id);
	}

	// create a stage
	@RequestMapping(value = "proposal/timeline/{timelineId}/stage/", method = RequestMethod.POST)
	public Stage createStage(Stage stage, @PathVariable Long timelineId) {
		Timeline timeline = timelineDao.getTimeline(timelineId);
		stage.setTimeline(timeline);
		
		
		return stageDao.saveStage(stage);
	}

	// update a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.PUT)
	public Stage updateStage(Timeline.Stage stage, @PathVariable Long id) {
		stage.setId(id);
		return stageDao.saveStage(stage);
	}

	// delete a stage
	@RequestMapping(value = "timeline/stage/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public DeleteResponse deleteStage(@PathVariable Long id) {
		
		stageDao.deleteStage(id);
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
