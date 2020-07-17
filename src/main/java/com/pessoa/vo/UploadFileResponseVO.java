package com.pessoa.vo;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
	public UploadFileResponseVO() {
		
	}

	public UploadFileResponseVO(String fileName2, String fileDowloaduri, String contentType, long size2) {
		this.fileName = fileName2;
		this.fileDownloadUri = fileDowloaduri;
		this.fileType = contentType;
		this.size = size2;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	

}
