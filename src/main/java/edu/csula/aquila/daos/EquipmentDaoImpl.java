package edu.csula.aquila.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csula.aquila.model.EquipmentForm;

@Repository
public class EquipmentDaoImpl implements EquipmentDao{

	@PersistenceContext
	private EntityManager entityManager;
		
	@Override
	public EquipmentForm getEquipmentForm(Long id) {
		return entityManager.find(EquipmentForm.class , id);
	}

	@Override
  @Transactional
	public EquipmentForm saveEquipmentForm(EquipmentForm equipmentForm) {
		return entityManager.merge(equipmentForm);
	}
  
}
