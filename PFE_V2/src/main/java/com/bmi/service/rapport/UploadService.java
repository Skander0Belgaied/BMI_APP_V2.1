package com.bmi.service.rapport;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	Map<String, Object> Uploadfile(MultipartFile file);
}
