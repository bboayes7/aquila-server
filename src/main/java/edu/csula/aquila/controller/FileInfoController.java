package edu.csula.aquila.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

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
import edu.csula.aquila.model.Stage;

@RestController
public class FileInfoController {

	@Autowired
	private FileInfoDao fileInfoDao;
	
	@Autowired
	private StageDao stageDao;

	
	//save file to disk , database, then add to Stage
	@RequestMapping(value= "/proposal/{propId}/stage/{stageId}/fileupload/{fileName}" , method = RequestMethod.PUT)
	public FileInfo uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long propId, @PathVariable String fileName, @PathVariable Long stageId)throws IOException
	{

		String diskFilename = null ;
		FileInfo fileInfo ;
		
		
		try
		{
			//saveFile saves file to disk and returns new fileName 
			diskFilename = fileInfoDao.saveFileToDisk( Arrays.asList(file), propId, fileName );		

        } 
		catch (IOException e) 
		{
            System.out.println("Bad Request");
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
		
		
		
		return fileInfo;
	}

	
	// view file
	@RequestMapping(value = "/proposal/{propId}/fileview", method = RequestMethod.GET)
	public void returnFile(@RequestParam String fileName) {
		fileInfoDao.returnFile(fileName);
	}
	
	// delete file
	@RequestMapping(value = "/timeline/{timelineId}/stage/{stageId}/deletefile/{fileId}", method = RequestMethod.DELETE)
	public String deleteFile(@PathVariable Long timelineId, @PathVariable Long stageId, @PathVariable Long fileId) throws FileNotFoundException
	{
		FileInfo fileInfo = fileInfoDao.getFile(fileId);
		Stage stage = stageDao.getStage(stageId);
		String deleteStatus;
		
		Map<String, FileInfo> requiredFiles = stage.getRequiredFiles();
		
		//if file name is in map, remove file from map by key(file name)
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

	// download file
	@RequestMapping( value = "/downloadfile/{fileId}", method = RequestMethod.GET )
	public void dowloadFile( HttpServletResponse response, @PathVariable Long fileId) throws IOException
	{
		FileInfo fileInfo = fileInfoDao.getFile(fileId);
		File fileToDownload = new File(fileInfo.getFilePath());
		
	      
	        MimetypesFileTypeMap mimetypesFileTypeMap=new MimetypesFileTypeMap();
	        response.setContentType(mimetypesFileTypeMap.getContentType(fileToDownload));
	        // Set the response headers. File.length() returns the size of the file
	        // as a long, which we need to convert to a String.
	        
	        response.setContentLength((int) fileToDownload.length());
	        response.setHeader( "Content-Disposition", "attachment; filename=" + fileInfo.getFileName() );

	        // Binary files need to read/written in bytes.
	        FileInputStream in = new FileInputStream( fileToDownload );
	        OutputStream out = response.getOutputStream();
	        byte buffer[] = new byte[2048];
	        int bytesRead = -1;
	        while( (bytesRead = in.read( buffer )) != -1 ) 
	        {
	            out.write( buffer, 0, bytesRead );
	        }
	        in.close();
	        out.close();
	  }
	
}
