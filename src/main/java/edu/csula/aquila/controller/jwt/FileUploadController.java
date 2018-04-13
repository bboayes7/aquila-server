package edu.csula.aquila.controller.jwt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	private final String FILE_UPLOAD_PATH = "C:\\upload\\";
	
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		File upload = new File(FILE_UPLOAD_PATH + file.getOriginalFilename());
		upload.createNewFile();
		FileOutputStream fout = new FileOutputStream(upload);
		fout.write(file.getBytes());
		fout.close();
		
		return new ResponseEntity<>("File is uploaded Successfuly!", HttpStatus.OK);
		
	}
	
}
