package edu.csula.aquila.daos;

import edu.csula.aquila.model.EquipmentForm;

public interface EquipmentDao {

	EquipmentForm getEquipmentForm(Long id);
	EquipmentForm saveEquipmentForm(EquipmentForm equipmentForm);
}
