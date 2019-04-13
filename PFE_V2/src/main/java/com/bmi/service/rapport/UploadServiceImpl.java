package com.bmi.service.rapport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadServiceImpl implements UploadService {
//	@Autowired
//	BmiequRepository bmiequRepository;
	  @Autowired
	    ApplicationContext context;
	@Override
	public Map<String, Object> Uploadfile(MultipartFile file) {
		Map<String, Object> FileStatus = new HashMap<String, Object>();

		 Resource resource = context.getResource("classpath:reports/"+file.getOriginalFilename());
		   if(resource.exists())
		   {	FileStatus.put("status",false);
		   		FileStatus.put("msg", "Unable to upload  Rapport :"+file.getOriginalFilename()+" is already exists or a error happend");
			   return FileStatus; 
		   }   
		if(!(file.getOriginalFilename().contains(".jrxml")))
		{
			FileStatus.put("status",false);
	   		FileStatus.put("msg","Please select a file with extension jrxml");
	   		return FileStatus;
		}
		//preparation du msg si le fichier n'exicte pas 
		  if (file.isEmpty()) {
			  FileStatus.put("status",false);
		   		FileStatus.put("msg","Please select a file to upload");
				return FileStatus;
	          
	        }

	        try {
	        	final String imagePath = "src/main/resources/reports/";
	            FileOutputStream output  = new FileOutputStream(imagePath+file.getOriginalFilename());
	            output.write(file.getBytes());
	            
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally {
	        	FileStatus.put("status",true);
		   		FileStatus.put("msg","Rapport "+file.getOriginalFilename()+"uploaded succsesfully ");
				return FileStatus;
			}
	        	
	}
	


}
