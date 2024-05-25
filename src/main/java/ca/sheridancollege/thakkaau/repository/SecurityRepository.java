package ca.sheridancollege.thakkaau.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.thakkaau.beans.User;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor

public class SecurityRepository {

private NamedParameterJdbcTemplate jdbc;
	
	public User findUserByUsername(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		   String query ="SELECT * FROM SEC_USER WHERE userName=:meow";
		   parameters.addValue("meow", username);
		   ArrayList<User> users = 
				   (ArrayList<User>)jdbc.query(query, parameters,
						   new BeanPropertyRowMapper<User>(User.class));
		if (users.size()>0)
			return users.get(0);
		else
		return null;
	}
	
	public List<String> getRolesByUserId(long userId){
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		   String query ="SELECT user_role.userId, sec_role.roleName FROM  USER_ROLE, SEC_ROLE WHERE user_role.roleId=sec_role.roleId and userId=:uid ";
		   parameters.addValue("uid", userId);
		   ArrayList<String> roles = new ArrayList<String>();
		   List<Map<String, Object>> rows=
				   jdbc.queryForList(query, parameters);
				   for(Map<String, Object> row: rows) {
					   roles.add((String)row.get("roleName"));
					  }
			   
			return roles;
		
	}
	
	public void addNewUser(String username, String password) {
		
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
	
		String query="INSERT into SEC_USER (userName, encryptedPassword, ENABLED)\r\n"
				+ "VALUES (:un,:pa,1)";
				
		   parameters.addValue("un", username);
		   parameters.addValue("pa", encodedPassword);
		   jdbc.update(query, parameters);
	   
	}
	
	public void assignUsersToRoles(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		String query="INSERT into USER_ROLE (userId, roleId)"
				+ "VALUES (:uid, :rid);";
				
		   parameters.addValue("uid", userId);
		   parameters.addValue("rid", roleId);
		   jdbc.update(query, parameters);
	
	}


}
