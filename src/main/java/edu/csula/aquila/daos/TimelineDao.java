package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

public interface TimelineDao {

	Timeline getTimelineForm(Long id);
	
	Timeline saveTimelineForm(Timeline timeline);

//	Stage getStage(Long id);
//
//	Stage createStage(Stage stage);
//
//	Stage updateStage(Stage stage);
//
//	Stage deleteStage(Stage stage);
	
}
