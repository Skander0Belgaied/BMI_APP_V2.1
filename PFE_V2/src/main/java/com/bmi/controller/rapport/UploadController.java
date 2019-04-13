package com.bmi.controller.rapport;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bmi.app.repository.RapportRepository;
import com.bmi.service.rapport.UploadServiceImpl;


@Controller
public class UploadController {
	  @Autowired
	    UploadServiceImpl	uploadServiceImpl;
@Autowired
RapportRepository rapportRepository;
	  
	  
	@GetMapping(path = "/addEtat")
	String addreport()
	{
		return "addEtat";
		
	}
	//@GetMapping(path = "/upload")
	   @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model
                                  /* @RequestParam("file")  */                  
    		) {
		   Map<String, Object> FileStatus = new HashMap<String, Object>();
		  // rapportRepository.save(entity)
		   FileStatus=uploadServiceImpl.Uploadfile(file);
		   if((boolean) FileStatus.get("status")) {
			   model.addAttribute("status",FileStatus.get("msg"));
		        return "/upload-status";
		   }else
			   {
			   	model.addAttribute("status",FileStatus.get("msg"));
		        return "/addEtat";
			   }
    }

}
