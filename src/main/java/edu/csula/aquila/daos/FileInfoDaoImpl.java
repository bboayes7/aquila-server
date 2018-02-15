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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.Proposal;


@Repository
public class FileInfoDaoImpl implements FileInfoDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	//files saved here
	private static String directory = "C:\\Proposals_UAS\\";
	

	@Override
	public FileInfo getFile(Long id) 
	{
		return entityManager.find(FileInfo.class, id);
	}

	@Override
	@Transactional
	public FileInfo addFileToDB(Long id, String filename) 
	{
		//find proposal by id
		Proposal proposal = entityManager.find(Proposal.class, id);
		String nameOfUploader = proposal.getUser().getFirstName() + proposal.getUser().getLastName();
		Date fileAddDate = new Date();
		String path = directory + filename;
		
		FileInfo fileInfo = new FileInfo(nameOfUploader, filename,"filetype", path, fileAddDate);
		
		fileInfo = entityManager.merge(fileInfo);
		return fileInfo;
		

	}

	@Override
	public String saveFileToDisk(List<MultipartFile> files, Long id) throws IOException 
	{
		//initialize counter for file control
		int count = 1;
			
		String checkName =  Calendar.getInstance().get(Calendar.YEAR) + "Proposal_ID" +
								id + "file" + count ;

		
		//checks if filename already exists, increments file counter if it does
		while(new File(directory + checkName).exists()) 
		{
			count++;
			checkName =  Calendar.getInstance().get(Calendar.YEAR) + "Proposal_ID" + 
							id + "file" + count ;
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
