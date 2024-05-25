package ca.sheridancollege.thakkaau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.thakkaau.repository.SecurityRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor

public class SecurityController {
	private  SecurityRepository secRepo;
	 
	@GetMapping("/login")
	public String mylogin() {
		return "login.html";
	}
	
	@GetMapping("/accessdenied")
	public String myad() {
		return "accessdenied.html";
	}
	
	@GetMapping("/register")
	public String myreg() {
		return "registration.html";
	}
	
	@PostMapping("/register")
	public String processRegistration(
			@RequestParam String username,
			@RequestParam String password)
	{
		secRepo.addNewUser(username,password);
		long userid = secRepo.findUserByUsername(username).getUserId();
		secRepo.assignUsersToRoles(userid, 2);//guest
		
		return "redirect:/";
	}

}
