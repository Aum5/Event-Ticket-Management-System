package ca.sheridancollege.thakkaau.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.thakkaau.repository.SecurityRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class UserServiceDetailsImp1 implements UserDetailsService {

private SecurityRepository secRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ca.sheridancollege.thakkaau.beans.User user =
				secRepo.findUserByUsername(username);
		
	// If the user doesn't exist then throw an exception
	if (user == null) {
		System.out.println("Username not found");
		throw new UsernameNotFoundException("User not found");
	}
	
	// Find the roles based on the user id
	List <String> roles = secRepo.getRolesByUserId(user.getUserId());
	
	// Convert our list of roles into a list of Granted Authority
	List <GrantedAuthority> grantList = new ArrayList <GrantedAuthority>();
	for (String r : roles ) {
		grantList.add(new SimpleGrantedAuthority(r));
	}
	
	// Create a spring security user using the above information
	User springUser = new User(user.getUserName(),
			user.getEncryptedpassword(), grantList);
	
	return (UserDetails)springUser;
	}

}
