package edu.csula.aquila.model;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface BudgetFileDao {
	
	public BudgetFile getBudget(Long id);
	
	public BudgetFile saveBudgetFile(BudgetFile budgetFile);
	
	public void addFile(Long id, String fileName);
	
	public String saveFile(List<MultipartFile> files, Long id) throws IOException;
	
	public byte[] getFile(String fileName);
	
	public void returnFile(String fileName);
	

}
