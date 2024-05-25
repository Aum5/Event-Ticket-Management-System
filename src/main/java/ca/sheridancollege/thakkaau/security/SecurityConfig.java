package ca.sheridancollege.thakkaau.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.AllArgsConstructor;

@Configuration // to register a class for springboot
@EnableWebSecurity   
@AllArgsConstructor

public class SecurityConfig {
	private AccessDeniedHandler accessDenied;
	private UserDetailsService userDetailsService;
	@Bean 
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder=
				new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public AuthenticationManager authManager (HttpSecurity http,
			PasswordEncoder passwordEncoder) throws Exception{
		AuthenticationManagerBuilder authBuilder = 
				http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
		return authBuilder.build();
	}
	/*@Autowired // so spring takes the password Encoder() method into his bag and with autowired annotation we can just use by creating object
	PasswordEncoder e;
	PasswordEncoder e = passwordEncoder();*/
	
	/*@Bean
	public InMemoryUserDetailsManager userDetailsService (PasswordEncoder passwordEncoder) {
		
		//import user from springSecurity core
		
		UserDetails user1 = User.withUsername("Aum")
				                 .password(passwordEncoder.encode("123"))
				                 .roles("USER")
				                 .build();
		
		UserDetails user2 = User.withUsername("Viraj")
								.password(passwordEncoder.encode("123"))
								.roles("USER","PICKLE")
                				.build();
		return new InMemoryUserDetailsManager(user1,user2);
	}	
	*/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		
		//////////////////////////////////////////
		// delete when switching to production code.(here we are just checking if table or database has been made perfectly)
		http.csrf().disable();
		http.headers().frameOptions().disable();
		//////////////////////////////////////////
		http.authorizeHttpRequests((authz)-> authz
				
				// .hasRole("ROLE") for one role
				// .hasAnyRole("Role1", "Role2",...) multiple roles
				// .permitall() for no login required
				// .requestMatcher(antMatcher("/url"))for all HTTPmethod with that
				// .requestMatcher(antMatcher("/user/**")) wildcard
				// matches "/user" "/user/something" "/user/a/b/c/3"
			.requestMatchers(antMatcher("/register")).permitAll()
				.requestMatchers(antMatcher((HttpMethod.POST),"/register")).permitAll()	
				.requestMatchers(antMatcher("/view")).hasAnyRole("VENDER","GUEST")
				.requestMatchers(antMatcher("/")).permitAll()
				.requestMatchers(antMatcher("/add")).hasRole("VENDER")
				.requestMatchers(antMatcher("/edit/{id}")).hasRole("VENDER")
				.requestMatchers(antMatcher("/delete/{id}")).hasRole("VENDER")
				
				.requestMatchers(antMatcher("/h2-console/**")).permitAll()
				.requestMatchers(antMatcher("/img/**")).permitAll()
				.requestMatchers(antMatcher("/css/**")).permitAll()
		
			//	.requestMatchers(antMatcher("/localhost:8080/**")).permitAll()
				.anyRequest().authenticated()
				)
		
		.formLogin((formLogin)->formLogin
				.loginPage("/login")
				.failureUrl("/login?failed")
				.permitAll()
				)
		
		.logout((logout)-> logout
				.deleteCookies("remove")
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/?logout")
				.permitAll()
				)
		.exceptionHandling((ex)-> ex
				.accessDeniedHandler(accessDenied)
				);
		return http.build();
	}


}
