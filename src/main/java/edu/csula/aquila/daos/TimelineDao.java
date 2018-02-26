package edu.csula.aquila.daos;

import edu.csula.aquila.model.Timeline;

public interface TimelineDao {

	Timeline getTimelineForm(Long id);
	
	Timeline saveTimelineForm(Timeline timeline);

}
