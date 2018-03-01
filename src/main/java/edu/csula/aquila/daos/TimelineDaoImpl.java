package edu.csula.aquila.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.Timeline;

@Repository
public class TimelineDaoImpl implements TimelineDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Timeline getTimelineForm(Long id) {
		return entityManager.find(Timeline.class, id);
	}

	@Override
	@Transactional
	public Timeline saveTimelineForm(Timeline timeline) {
		return entityManager.merge(timeline);
	}


}
