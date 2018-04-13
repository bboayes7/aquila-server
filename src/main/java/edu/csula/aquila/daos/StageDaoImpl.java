package edu.csula.aquila.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.Stage;

@Repository
public class StageDaoImpl implements StageDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Stage getStage(Long id) {
		return entityManager.find(Stage.class, id);
	}
	
	@Override
	public List<Stage> getStages(Long timelineId){
		String query = "from Stage where timeline_id = :id order by stage_order";
		return entityManager.createQuery( query , Stage.class )
				.setParameter("id", timelineId)
	            .getResultList();
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
		Stage stage = entityManager.find(Stage.class, id);
		entityManager.remove(stage);
	}
}
