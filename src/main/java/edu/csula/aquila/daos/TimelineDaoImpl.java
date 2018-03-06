package edu.csula.aquila.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

@Repository
public class TimelineDaoImpl implements TimelineDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Timeline getTimeline(Long id) {
		return entityManager.find(Timeline.class, id);
	}

	@Override
	@Transactional
	public Timeline saveTimeline(Timeline timeline) {
		return entityManager.merge(timeline);
	}
	
	

}
