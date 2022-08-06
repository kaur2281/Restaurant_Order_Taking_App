package ca.sheridancollege.kaur2281.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.kaur2281.database.DatabaceAccess;

@Controller
public class HomeController {
	
	@Autowired
	DatabaceAccess da;

	@GetMapping("/")
	public String index(Model model){
		model.addAttribute("dishes",da.getAllDish());
		return "index";
	}
	
	@GetMapping("/vieworders")
	public String vieworders() {
		return "vieworders";
	}

	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}
}
