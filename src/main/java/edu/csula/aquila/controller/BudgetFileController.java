package edu.csula.aquila.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.aquila.daos.BudgetFileDao;
import edu.csula.aquila.model.BudgetFile;

@RestController
public class BudgetFileController {
	
	@Autowired
	private BudgetFileDao budgetFileDao;
	
	
	@RequestMapping(value = "/proposal/budget/{id}", method = RequestMethod.GET)
	public BudgetFile getBudget(@PathVariable Long id)
	{
		return budgetFileDao.getBudget(id);
	}
	
	
	@RequestMapping(value = "/proposal/budget/save", method = RequestMethod.POST)
	public BudgetFile saveBudgetFile(@RequestBody BudgetFile budget)
	{
		return budgetFileDao.saveBudgetFile(budget);
		
	}
	
	
	
	@RequestMapping(value = "/proposal/budget/{id}/upload", method = RequestMethod.PUT)
	public String uploadSaveFile(@RequestParam("file") MultipartFile file, @PathVariable Long id)throws IOException
	{
		
		String status;
		String fileName = null;
		
		if(file.isEmpty())
		{
			return "Please Choose A Valid File!";
		}
		
		try
		{
			//saveFile saves file to disk and returns new fileName with naming convention
			fileName = budgetFileDao.saveFile(Arrays.asList(file), id);
			System.out.println(fileName);
		

        } 
		catch (IOException e) 
		{
            status = "Bad Request";
        }
		
		//add file with new file name to DB under budgets id
		budgetFileDao.addFile(id, fileName);
		
		status = "success! - " + fileName;
		
		return status;
	}

	
	@RequestMapping(value = "/proposal/budget/file", method = RequestMethod.GET)
	public void returnFile(@RequestParam String fileName)
	{
		budgetFileDao.returnFile(fileName);
	}
}
