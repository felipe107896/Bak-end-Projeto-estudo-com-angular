package com.pessoa.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	public String storeFile(MultipartFile multipartFile);
	
	public Resource loadFileAsResource(String fileName);
}
