package edu.csula.aquila.daos;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.csula.aquila.model.FileInfo;

public interface FileInfoDao {
	
	public FileInfo getFile(Long id);
	
	public FileInfo addFileToDB(Long id, String filename);
	
	public String saveFileToDisk(List<MultipartFile> files, Long id) throws IOException;
	
	public void returnFile(String filename);

}
