package ca.sheridancollege.kaur2281.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3481685843411615814L;

	private int orderID;
	
	private int dishID;
	
	private String custEmail;
	
	private int qty;
}
