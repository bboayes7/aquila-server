package edu.csula.aquila.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.ConflictOfInterestForm;

@Repository
public class ConflictOfInterestFormDaoImpl implements ConflictOfInterestFormDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public ConflictOfInterestForm getConflictOfInterestFormById( Long id )
	{
		return entityManager.find(ConflictOfInterestForm.class, id);
	}

	@Override
	@Transactional
	public ConflictOfInterestForm saveConflictOfInterestForm(ConflictOfInterestForm coiForm) 
	{
		return entityManager.merge( coiForm );
	}

	@Override
	@Transactional
	public ConflictOfInterestForm updateConflictOfInterestForm(ConflictOfInterestForm coiForm) 
	{
		return entityManager.merge( coiForm );
	}

}
