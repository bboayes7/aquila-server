package edu.csula.aquila.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_info")
public class FileInfo implements Serializable{

	private static final long serialVersionUID = -1872944428804671621L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "file_info_id")
	private Long Id;
	
	@Column(name = "uploader")
	private String nameOfUploader;
	
	@Column(name ="file_name")
	private String fileName;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name ="upload_date")
	private Date uploadDate;
	
	@Column(name = "is_uploaded")
	private boolean isUploaded;
	
	public FileInfo() {}

	public FileInfo(String nameOfUploader, String fileName, String fileType, String filePath, Date uploadDate) {
		super();
		this.nameOfUploader = nameOfUploader;
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
	}
	
	public FileInfo(String filePath)
	{
		this.filePath = filePath;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNameOfUploader() {
		return nameOfUploader;
	}

	public void setNameOfUploader(String nameOfUploader) {
		this.nameOfUploader = nameOfUploader;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public boolean isUploaded() {
		return isUploaded;
	}

	public void setUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}
	
	
}
