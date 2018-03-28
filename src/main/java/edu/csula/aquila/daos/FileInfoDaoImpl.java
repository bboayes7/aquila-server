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
import edu.csula.aquila.model.User;


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
	public FileInfo getFile( String fileName)
	{
		String query = "from file_info where lower(fileName) = :fileName";

		List<FileInfo> fileInfos = entityManager.createQuery( query, FileInfo.class )
	            .setParameter( "fileName", fileName.toLowerCase() )
	            .getResultList();
	        return fileInfos.size() == 0 ? null : fileInfos.get( 0 );
	}
	
	
	@Override
	@Transactional
	public FileInfo saveFile( FileInfo fileInfo )
	{
		return entityManager.merge(fileInfo);
	}
	
	
	@Override
	@Transactional
	public FileInfo addFileToDB(Long id, String diskFilename, String fileName) 
	{
		//find proposal by id
		Proposal proposal = entityManager.find(Proposal.class, id);
		String nameOfUploader = proposal.getUser().getFirstName() + " " + proposal.getUser().getLastName();
		
		//get file type
		String [] ext = diskFilename.split("\\.");
		int extIndex = ext.length - 1;
		String fileType = ext[extIndex].toUpperCase();
		
		Date fileAddDate = new Date();
		String path = directory + diskFilename;
		
		//create new file then merge, file type in progress
		FileInfo fileInfo = new FileInfo(nameOfUploader, fileName, fileType, path, fileAddDate, true);
		
		return fileInfo = entityManager.merge(fileInfo);
				
	}
	
	
	@Override
	@Transactional
	public FileInfo updateFile( String fileName, Long id, String diskFilename )
	{
		String query = "from file_info where lower(fileName) = :fileName";

		List<FileInfo> fileInfos = entityManager.createQuery( query, FileInfo.class )
	            .setParameter( "fileName", fileName.toLowerCase() )
	            .getResultList();
	        FileInfo fileInfo = fileInfos.get( 0 );
	        
	    //find proposal by id
		Proposal proposal = entityManager.find(Proposal.class, id);
		String nameOfUploader = proposal.getUser().getFirstName() + " " + proposal.getUser().getLastName();
			
		//get file type
		String [] ext = diskFilename.split("\\.");
		int extIndex = ext.length - 1;
		String fileType = ext[extIndex].toUpperCase();
		
		Date fileAddDate = new Date();
		String path = directory + diskFilename;
		
		
		//update current object in database
		fileInfo.setNameOfUploader(nameOfUploader);
		fileInfo.setFilePath(path);
		fileInfo.setFileType(fileType);
		fileInfo.setFileName(fileName);
		fileInfo.setUploadDate(fileAddDate);
		fileInfo.setUploaded(true);
				
	        
		return entityManager.merge(fileInfo);
	}
	
	
	@Override
	public String saveFileToDisk(List<MultipartFile> files, Long id, String fileName) throws IOException 
	{
		//initialize counter for file control
		int count = 0;
		String extension = null;
		String checkName =  Calendar.getInstance().get(Calendar.YEAR) + "Proposal_ID" +
								id + fileName ;

		
		//checks if filename already exists, increments file counter if it does
		while(new File(directory + checkName).exists()) 
		{
			count++;
			checkName =  Calendar.getInstance().get(Calendar.YEAR) + "Proposal_ID" + 
							id + fileName + count ;
		}
		String newFileName = checkName;
		
		
		//loop through list of files, and save to disk with new name
		for (MultipartFile file : files) 
		{
			if (file.isEmpty()) 
			{
				continue; 
	        }
			
			//get file type
			String filename = file.getOriginalFilename();
			String [] ext = filename.split("\\.");
			int extIndex = ext.length - 1;
			extension = ext[extIndex];
			
			//save bytes to the created path(with new filename)
            byte[] bytes = file.getBytes();
            Path path = Paths.get(directory + newFileName );
            Files.write(path, bytes); 
            
		}
		
		return newFileName + "." + extension;
		
	}

	
	@Override
	public void returnFile(String diskFilename)
	{
		try
		{
			Runtime.getRuntime().exec("cmd /c start " + directory + diskFilename);
		} catch (IOException e) 
		{
			System.out.println("Invalid File Name");
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public void deleteFile( Long id )
	{
		FileInfo fileInfo = entityManager.find(FileInfo.class, id);
		File fileToDelete = new File(fileInfo.getFilePath());
		
		if( !fileToDelete.delete() )
			System.out.println("File was not Deleted Succesfully" );
		
		entityManager.remove(fileToDelete);
		
	}

}
