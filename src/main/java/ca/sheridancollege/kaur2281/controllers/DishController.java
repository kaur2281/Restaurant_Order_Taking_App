package ca.sheridancollege.kaur2281.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.kaur2281.beans.Dish;
import ca.sheridancollege.kaur2281.database.DatabaceAccess;

@RestController
@RequestMapping("/dishes")
public class DishController {

	@Autowired
	DatabaceAccess da;
	
	@GetMapping("/{id}")
	public Dish getIndividualStudent(@PathVariable Long id) {  
		return da.findById(id).get(0);
	}
}
