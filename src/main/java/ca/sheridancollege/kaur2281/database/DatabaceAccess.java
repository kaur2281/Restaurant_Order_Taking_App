package ca.sheridancollege.kaur2281.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.kaur2281.beans.Dish;
import ca.sheridancollege.kaur2281.beans.Orders;

@Repository
public class DatabaceAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public Integer addOrder(Orders order){
		MapSqlParameterSource namedParameters=
				new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query="INSERT INTO orders(dishID,cust_email,qty) VALUES(:dishID,:cust_email,:qty)";
		namedParameters.addValue("dishID",order.getDishID());
		namedParameters.addValue("cust_email",order.getCustEmail());
		namedParameters.addValue("qty",order.getQty());
		jdbc.update(query, namedParameters,generatedKeyHolder);
		return (Integer)generatedKeyHolder.getKey();
	}
	
	public List<Dish> getAllDish(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM dish";
		return jdbc.query(query,  namedParameters, 
				new BeanPropertyRowMapper<Dish>(Dish.class));
	}
	
	public List<Orders> getAllOrders(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM orders";
		return jdbc.query(query,  namedParameters, 
				new BeanPropertyRowMapper<Orders>(Orders.class));
	}
	
	public boolean updateOrders(Orders order){
		MapSqlParameterSource namedParameters=
				new MapSqlParameterSource();
		String query="UPDATE orders set dishID=:dishID,cust_email=:cust_email,qty=:qty where orderID = :id";
		namedParameters.addValue("id", order.getOrderID());
		namedParameters.addValue("dishID",order.getDishID());
		namedParameters.addValue("cust_email",order.getCustEmail());
		namedParameters.addValue("qty",order.getQty());
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if  (rowsAffected > 0)
			return true;
		else
			return false;
	}
	
	public boolean deleteOrders(int id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE  FROM orders where orderID=:id";
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		if  (rowsAffected > 0)
			return true;
		else
			return false;
	}
	
	public List<Dish> findById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();  
		String query = "SELECT * FROM dish WHERE dishID =:id";
		namedParameters.addValue("id",id);
		return jdbc.query(query, namedParameters,new  BeanPropertyRowMapper<Dish>(Dish.class));
	}
}
