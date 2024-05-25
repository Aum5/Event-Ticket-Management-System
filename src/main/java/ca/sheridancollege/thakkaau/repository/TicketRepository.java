package ca.sheridancollege.thakkaau.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.thakkaau.beans.Ticket;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor

public class TicketRepository {
	private NamedParameterJdbcTemplate jdbc;

	
	public ArrayList<Ticket> getgender()
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT DISTINCT gender FROM ticket";
			
		ArrayList<Ticket> gender = (ArrayList<Ticket>)jdbc.query(query, parameters, new BeanPropertyRowMapper<Ticket>(Ticket.class));
		return gender;
}
	
	public ArrayList<Ticket> getprice()
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT DISTINCT price FROM ticket";
			
		ArrayList<Ticket> price = (ArrayList<Ticket>)jdbc.query(query, parameters, new BeanPropertyRowMapper<Ticket>(Ticket.class));
		return price;
}
	
	public void addticket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO ticket (name, email, gender, number, price, pay)VALUES"
				+ "(:na, :em, :ge, :nu, :pr, :pa)";
		parameters.addValue("na", ticket.getName());

		parameters.addValue("em", ticket.getEmail());

		parameters.addValue("ge", ticket.getGender());
		
		parameters.addValue("nu", ticket.getNumber());
		
		parameters.addValue("pr", ticket.getPrice());
		
		parameters.addValue("pa", ticket.getPay());

		jdbc.update(query, parameters);
	}

	public ArrayList<Ticket> getticket()
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket";
			
		ArrayList<Ticket> view = (ArrayList<Ticket>)jdbc.query(query, parameters, new BeanPropertyRowMapper<Ticket>(Ticket.class));
		return view;
}



	
	public Ticket getTicketById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket WHERE id =:tic";
		parameters.addValue("tic", id);
		ArrayList<Ticket> tickets = (ArrayList<Ticket>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
		if (tickets.size()>0)
			return tickets.get(0);
		else 
			return null;
	}
	
	public void editTicket(Ticket ticket) {

		   MapSqlParameterSource parameters = new MapSqlParameterSource();
		   String query = "UPDATE ticket SET name=:na,"
				   + "email=:em,"
				   + "gender=:ge,"
				   + "number=:nu,"
				   + "price=:pr,"
				   +"pay=:pa WHERE id=:tic";

				   
				  parameters.addValue("tic", ticket.getId());
		   
		   parameters.addValue("na", ticket.getName());
		   parameters.addValue("em", ticket.getEmail());
		   parameters.addValue("ge", ticket.getGender());
		   parameters.addValue("nu", ticket.getNumber());
		   parameters.addValue("pr", ticket.getPrice());
		   parameters.addValue("pa", ticket.getPay());
			  
		    jdbc.update(query, parameters);
		}
	public void deleteticketbyId(Ticket ticket) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query  = "DELETE FROM ticket WHERE id=:i";
		parameter.addValue("i", ticket.getId());
		jdbc.update(query, parameter);
		
	}
	
	public ArrayList<Ticket> getTicketByname(String name) {
		// TODO Auto-generated method stub
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM ticket WHERE name=:meow";
		parameters.addValue("meow", name);
		ArrayList<Ticket> contacts = (ArrayList<Ticket>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));

		if (contacts.size() > 0)
			return contacts;
		else
			return null;
	}
	
public ArrayList<String> getPricesFromUsername(String username) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		 String query ="SELECT price FROM ticket WHERE name=:woof";
		   parameter.addValue("woof", username);
		   ArrayList<String> prices = new ArrayList<String>();
		   List<Map<String, Object>> rows = jdbc.queryForList(query, parameter);
			
			for(Map<String, Object> row : rows) {
				prices.add((String)row.get("price"));
				
			}

		return prices;
}
public ArrayList<String> getUsernameByRoleId(int i) {
	// TODO Auto-generated method stub
	MapSqlParameterSource parameters = new MapSqlParameterSource();
	String query = "SELECT u.userName\r\n"
			+ "FROM SEC_User u\r\n"
			+ "JOIN user_role ur ON u.userId = ur.userId\r\n"
			+ "JOIN sec_role r ON ur.roleId = r.roleId\r\n"
			+ "WHERE r.roleName = 'ROLE_GUEST'\r\n"
			+ "ORDER BY u.userName ASC";
	
	parameters.addValue("ROLE_GUEST", i);
	ArrayList<String> usernames = new ArrayList<String>();
	List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

	for (Map<String, Object> row : rows) {
		usernames.add((String) row.get("userName"));

	}
	return usernames;
}






	
}

