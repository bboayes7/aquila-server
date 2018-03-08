package edu.csula.aquila.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.Timeline;
import edu.csula.aquila.model.Timeline.Stage;

@Repository
public class StageDaoImpl implements StageDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Stage getStage(Long id) {
		return entityManager.find(Stage.class, id);
	}

	@Override
	@Transactional
	public Stage saveStage(Stage stage) {
		return entityManager.merge(stage);
	}
	
	@Override
	@Transactional
	public Stage updateStage(Stage stage) {
		return entityManager.merge(stage);
	}

	@Override
	@Transactional
	public void deleteStage( Long id ) 
	{
		Stage stage = entityManager.find(Timeline.Stage.class, id);
		entityManager.remove(stage);
	}
}
