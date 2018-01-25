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
	public Timeline getTimelineForm(Long id) {
		return entityManager.find(Timeline.class, id);
	}

	@Override
	@Transactional
	public Timeline saveTimelineForm(Timeline timeline) {
		return entityManager.merge(timeline);
	}

	@Override
	public Stage getStage(Long id) {
		return entityManager.find(Timeline.Stage.class, id);
	}

	@Override
	@Transactional
	public Stage createStage(Timeline.Stage stage) {
		return entityManager.merge(stage);
	}

	@Override
	@Transactional
	public Stage updateStage(Timeline.Stage stage) {
		return entityManager.merge(stage);
	}

	@Override
	@Transactional
	public Stage deleteStage(Timeline.Stage stage) {
		entityManager.remove(stage);
		return null;
	}
	
	
	
	

}
