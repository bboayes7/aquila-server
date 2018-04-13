package edu.csula.aquila.controller.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.csula.aquila.daos.EquipmentDao;
import edu.csula.aquila.model.EquipmentForm;

@RestController
public class EquipmentController {

	@Autowired 
	private EquipmentDao equipmentDao;
	
	@RequestMapping(value = "equipment/{id}", method = RequestMethod.GET)
	public EquipmentForm getEquipmentForm(@PathVariable Long id) {
		return equipmentDao.getEquipmentForm(id);
	}
	
	@RequestMapping(value = "equipment/" , method = RequestMethod.POST)
	public EquipmentForm newEquipmentForm(@RequestBody EquipmentForm equipmentForm) {
		return equipmentDao.saveEquipmentForm(equipmentForm);
	}
	
	@RequestMapping(value= "equipment/{id}", method = RequestMethod.PUT)
	public EquipmentForm updateEquipmentForm(@RequestBody EquipmentForm equipmentForm, @PathVariable Long id) {
		equipmentForm.setId(id);	
		return equipmentDao.saveEquipmentForm(equipmentForm);
	}
}
