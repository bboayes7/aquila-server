package edu.csula.aquila.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import edu.csula.aquila.model.EquipmentForm;
@Repository
public class EquipmentDaoImpl implements EquipmentDao{

	@PersistenceContext
	private  EntityManager entityManager;
	
	
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
