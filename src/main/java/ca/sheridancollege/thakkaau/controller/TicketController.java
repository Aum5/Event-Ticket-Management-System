
package ca.sheridancollege.thakkaau.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.thakkaau.beans.Ticket;
import ca.sheridancollege.thakkaau.repository.SecurityRepository;
import ca.sheridancollege.thakkaau.repository.TicketRepository;
import lombok.AllArgsConstructor;

@Controller

@AllArgsConstructor

public class TicketController {

	private TicketRepository ticketrepo;
	
	@GetMapping("/")
	public String myroot() {
		return "root.html";
	}
	
	@GetMapping("/add")
	public String myaddticket(Model model) {
		
		ArrayList<String> UserName = ticketrepo.getUsernameByRoleId(2);
		model.addAttribute("UserName", UserName);
		
		  //  model.addAttribute("names", ticketrepo.getguest());	
		   model.addAttribute("genders", ticketrepo.getgender());
			model.addAttribute("prices", ticketrepo.getprice());
			return "Add.html";
	}
	
	@PostMapping("/adding")
	public String myaddingticket(Model model,@ModelAttribute Ticket order ) {
			model.addAttribute("ticket", new Ticket());
			ticketrepo.addticket(order);
			return "redirect:/add";
	}
	@GetMapping("/view")
	public String view(Principal principal, Authentication auth, Model model) {
	    String userName = principal.getName();
	    java.util.Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

	    if (userName.equals("Jon")) {
	        model.addAttribute("Esports", ticketrepo.getticket());
	        return "view.html";
	    } else {
	        model.addAttribute("Esports", ticketrepo.getTicketByname(userName));
	        double subTotal=0;
			ArrayList<String> prices = ticketrepo.getPricesFromUsername(userName);
			for (String price : prices) {
			String total = price.replaceAll("[^\\d.]", "");
			double number = Double.parseDouble(total);
			 subTotal += number;
			 }
			double tax = subTotal *0.13;
			tax = Math.round(tax*100)/100.00;
			double Total = tax + subTotal;
			model.addAttribute("subTotal",subTotal);
			model.addAttribute("tax",tax);
			model.addAttribute("Total",Total);
				
			

	        return "view.html";
	    }
	}

	@GetMapping("/edit/{id}")
	public String editticket(Model model ,@PathVariable int id) {
		Ticket ticket = ticketrepo.getTicketById(id);
		
		ArrayList<String> UserName = ticketrepo.getUsernameByRoleId(id);
		model.addAttribute("UserName", UserName);
		
		ArrayList<Ticket> gender = ticketrepo.getgender();
		model.addAttribute("genders", gender);
		ArrayList<Ticket> price = ticketrepo.getprice();
		model.addAttribute("prices", price);		
		model.addAttribute("ticket", ticket);
		return "editticket.html";
	}

	@PostMapping("/edit")
	public String processEdit(Model model, @ModelAttribute Ticket confirm) {
		model.addAttribute("ticket", new Ticket());
		ticketrepo.editTicket(confirm);
		return"redirect:/view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteticket( @PathVariable int id) {
		Ticket ticket = ticketrepo.getTicketById(id);
	    ticketrepo.deleteticketbyId(ticket);
		return "redirect:/view";	
	}


	
}
