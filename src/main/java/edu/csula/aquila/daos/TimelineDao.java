package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline;

public interface TimelineDao {

	Timeline getTimeline(Long id);
	
	Timeline saveTimeline(Timeline timeline);

}
