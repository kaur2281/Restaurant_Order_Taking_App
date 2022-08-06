package ca.sheridancollege.kaur2281.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.kaur2281.beans.Orders;
import ca.sheridancollege.kaur2281.database.DatabaceAccess;

@RestController
@RequestMapping("/orders")
public class OrderControllers {

	@Autowired
	private DatabaceAccess da;

	@PostMapping(consumes = "application/json")
	public Integer postStudent(@RequestBody Orders order) {
		return da.addOrder(order);
	}

	@GetMapping
	public List<Orders> getStudentCollection() {
		return da.getAllOrders();
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable int id) {
		da.deleteOrders(id);
	}

	@PutMapping("/{id}")
	public void updateOrder(@PathVariable int id,@RequestBody Orders order) {
		order.setOrderID(id);
		da.updateOrders(order);
	}
		
	
}
