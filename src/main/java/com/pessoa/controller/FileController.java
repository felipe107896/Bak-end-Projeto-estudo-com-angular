package com.pessoa.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pessoa.service.FileStorageService;
import com.pessoa.vo.UploadFileResponseVO;

import io.swagger.annotations.Api;

@Api(value = "arquivos", description = "Controle dos arquivos", tags = {"Arquivos endpoint"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile multipartFile ) {
		String fileName = fileStorageService.storeFile(multipartFile);
		
		String fileDowloaduri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("api/file/v1/dowloadFile")
				.path(fileName)
				.toUriString();
		
		return new UploadFileResponseVO(fileName, fileDowloaduri,multipartFile.getContentType(),multipartFile.getSize());
		
	}
	
	@PostMapping("/uploadMultiplesFile")
	public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files ) {
	return Arrays.asList(files)
			.stream()
			.map(file -> uploadFile(file))
			.collect(Collectors.toList());
		
	}


}
