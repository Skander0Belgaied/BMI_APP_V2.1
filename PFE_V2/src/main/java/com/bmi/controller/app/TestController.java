package com.bmi.controller.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bmi.app.repository.UtilisateurRepository;

@Controller
public class TestController {
	@Autowired
	UtilisateurRepository u;
	/*@RequestMapping("/t")
	@ResponseBody
	public String welcome(Authentication authentication) {
		UtilisateurDetails utilisateurDetails = (UtilisateurDetails) authentication.getPrincipal();
		return "User has authorities: " + utilisateurDetails.getUtilisateur().getUtilisateurNom();
	      
	}*/
	
	@RequestMapping("/addEtat")
	public String welcome() {
		return "addEtat";
	      
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/test")
	public String test() {
		/*try {
		     // create and set properties into properties object
		     Properties props = new Properties();
		     props.setProperty("Prop1", "KESKES");
		     
		     props.setProperty("Prop3", "nooo pls");
		     // get or create the file
		     File f = new File("app-properties.properties");
		     OutputStream out = new FileOutputStream( f );
		     // write into it
		     DefaultPropertiesPersister p = new DefaultPropertiesPersister();
		     p.store(props, out, "Header COmment");
		   } catch (Exception e ) {
		    e.printStackTrace();
		   }*/
	      
		return "cfguser2";
	}
	
	@RequestMapping("/form")
	public String form() {

		return "testform";
	}
	
	@GetMapping("/testform")
	public String testform(@RequestParam MultiValueMap<String, String> queryMap,HttpServletRequest req) {
		for(int i = 0;i<queryMap.get("b").size();i++) {
		System.out.println(queryMap.get("b").get(i));
		}
		return "testform";
	}
}