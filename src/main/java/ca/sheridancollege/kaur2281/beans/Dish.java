package ca.sheridancollege.kaur2281.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5417963115376497864L;

	private int dishID;
	
	private String dishName;
	
	private float dishPrice;
}
