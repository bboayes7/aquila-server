package edu.csula.aquila.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.csula.aquila.daos.FileInfoDao;
import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.Timeline.Stage;

@RestController
public class FileInfoController {

	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Autowired
	private StageDao stageDao;

	
	//save file to disk , database, then add to Stage
	@RequestMapping(value= "/proposal/{propId}/stage/{stageId}/fileupload/{fileName}" , method = RequestMethod.PUT)
	public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long propId, @PathVariable String fileName, @PathVariable Long stageId)throws IOException
	{
		String uploadStatus;
		String diskFilename = null ;
		FileInfo fileInfo ;
		
		
		try
		{
			//saveFile saves file to disk and returns new fileName 
			diskFilename = fileInfoDao.saveFileToDisk( Arrays.asList(file), propId, fileName );		

        } 
		catch (IOException e) 
		{
            uploadStatus = "Bad Request";
        }
		
		System.out.println(diskFilename);
		
		//check if fileIfo exist in database, if yes then update, if no then add
		Stage stage = stageDao.getStage(stageId);
		Map<String,FileInfo> requiredFiles = stage.getRequiredFiles();
		
		if(requiredFiles.containsKey(fileName)) 
		{
			fileInfo = fileInfoDao.updateFile(fileName, propId, diskFilename);
		}
		else {
			fileInfo =	fileInfoDao.addFileToDB(propId, diskFilename, fileName);
		}
		
		stage.getRequiredFiles().put(fileName, fileInfo);
		stageDao.saveStage(stage);
		
		
		uploadStatus = "success! - " + diskFilename + " has been uploaded";
		
		return uploadStatus;
	}

	
	// view file
	@RequestMapping(value = "/proposal/{propId}/fileview", method = RequestMethod.GET)
	public void returnFile(@RequestParam String fileName) {
		fileInfoDao.returnFile(fileName);
	}
	
	
	@RequestMapping(value = "/timeline/{timelineId}/stage/{stageId}/deletefile/{fileId}", method = RequestMethod.DELETE)
	public String deleteFile(@PathVariable Long timelineId, @PathVariable Long stageId, @PathVariable Long fileId)
	{
		FileInfo fileInfo = fileInfoDao.getFile(fileId);
		Stage stage = stageDao.getStage(stageId);
		String deleteStatus;
		
		Map<String, FileInfo> requiredFiles = stage.getRequiredFiles();
		
		if(requiredFiles.containsKey(fileInfo.getFileName())) 
		{
			requiredFiles.remove(fileInfo.getFileName());
			deleteStatus = "Deleted Successfully";
		}
		else
		{
			deleteStatus = "File Does not Exist";
		}
		
		fileInfoDao.deleteFile(fileId);
		return deleteStatus;
	}

}
