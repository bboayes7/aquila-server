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
import edu.csula.aquila.daos.StageDao;
import edu.csula.aquila.model.FileInfo;
import edu.csula.aquila.model.Timeline.Stage;

@RestController
public class FileInfoController {

	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Autowired
	private StageDao stageDao;

	
	//save file to disk , then add filename and date under proposal
	@RequestMapping(value= "/proposal/{propId}/fileupload/{fileName}/{stageId}" , method = RequestMethod.PUT)
	public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long propId, @PathVariable String fileName, @PathVariable Long stageId)throws IOException
	{
		String uploadStatus;
		String filename = null ;
		
		
		try
		{
			//saveFile saves file to disk and returns new fileName 
			filename = fileInfoDao.saveFileToDisk( Arrays.asList(file), propId, fileName );		

        } 
		catch (IOException e) 
		{
            uploadStatus = "Bad Request";
        }
		
		System.out.println(filename);
		
		//save file info to db
		FileInfo fileInfo =	fileInfoDao.addFileToDB(propId,filename);
		Stage stage = stageDao.getStage(stageId);
		stage.getRequiredFiles().put(fileName, fileInfo);
		stageDao.saveStage(stage);
		
		
		uploadStatus = "success! - " + filename + " has been uploaded";
		
		return uploadStatus;
	}

	// return file
	@RequestMapping(value = "/proposal/fileview", method = RequestMethod.GET)
	public void returnFile(@RequestParam String fileName) {
		fileInfoDao.returnFile(fileName);
	}

}
