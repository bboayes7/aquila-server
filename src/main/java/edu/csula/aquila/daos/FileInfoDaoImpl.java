package edu.csula.aquila.daos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.multipart.MultipartFile;

import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.Proposal;
import edu.csula.aquila.daos.ProposalDaoImpl;

public class FileInfoDaoImpl implements FileInfoDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	//files saved here
	private static String directory = "C:\\uas_uploads\\";
	

	@Override
	public FileInfo getFile(Long id) 
	{
		return entityManager.find(FileInfo.class, id);
	}

	@Override
	public void addFileToDB(Long id, String filename) 
	{
		//find proposal by id
		Proposal proposal = entityManager.find(Proposal.class, id);
		Date fileAddDate = new Date();
		
		//save file to proposal, update proposal
		proposal.getFilePaths().put(filename, fileAddDate);
		

	}

	@Override
	public String saveFileToDisk(Long id, List<MultipartFile> files) throws IOException 
	{
		//initialize counter for version control
		int count = 1;
			
		String checkName =  Calendar.getInstance().get(Calendar.YEAR) + "ProposalID" + id + "version" + count + ".xls";

		//checks if version within budget already exists, increments version counter if it does
		while(new File(directory + checkName).exists()) {
			count++;
			checkName =  Calendar.getInstance().get(Calendar.YEAR) + "id" + id + "version" + count ;
		}
		String newFileName = checkName;
		
		//loop through list of files, and save to disk with new name
		for (MultipartFile file : files) 
		{
			if (file.isEmpty()) 
			{
				continue; 
	        }
			//save bytes to the created path(with new filename)
            byte[] bytes = file.getBytes();
            Path path = Paths.get(directory + newFileName);
            Files.write(path, bytes);   

	    }
		return newFileName;
		
	}

	
	@Override
	public void returnFile(String filename)
	{
		try
		{
			Runtime.getRuntime().exec("cmd /c start " + directory + filename);
		} catch (IOException e) 
		{
			System.out.println("Invalid File Name");
			e.printStackTrace();
		}
	}

}
