package ca.sheridancollege.thakkaau.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Ticket {
	
	private String name;
	private String email;
	private String gender;
	private String number;
	private String price;
	private String pay;
	private int id;
	
}
