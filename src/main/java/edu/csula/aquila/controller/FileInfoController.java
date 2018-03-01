package edu.csula.aquila.controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.aquila.daos.FileInfoDao;

@RestController
public class FileInfoController {
	
	@Autowired 
	private FileInfoDao fileInfoDao;
	
	
	//save file to disk , then add filename and date under proposal
	@RequestMapping(value= "/proposal/{id}/fileupload" , method = RequestMethod.PUT)
	public String saveFile(@RequestParam("file") MultipartFile file, @PathVariable Long id)throws IOException
	{
		String uploadStatus;
		String filename = null ;
		
		if(file.isEmpty())
		{
			return "Please Choose A Valid File";
		}
		
		try
		{
			//saveFile saves file to disk and returns new fileName 
			filename = fileInfoDao.saveFileToDisk( Arrays.asList(file), id );		

        } 
		catch (IOException e) 
		{
            uploadStatus = "Bad Request";
        }
		
		System.out.println(filename);
		
		//save file info to db
		fileInfoDao.addFileToDB(id,filename);
		
		
		uploadStatus = "success! - " + filename + " has been uploaded";
		
		return uploadStatus;
	}
	
	//return file
	@RequestMapping(value = "/proposal/fileview", method = RequestMethod.GET)
	public void returnFile(@RequestParam String fileName)
	{
		fileInfoDao.returnFile(fileName);
	}

	
}
