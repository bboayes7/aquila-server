package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

public interface TimelineDao {

	Timeline getTimeline(Long id);
	
	Timeline saveTimeline(Timeline timeline);

}
