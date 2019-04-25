package com.bmi;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bmi.app.repository.UtilisateurRepository;
import com.bmi.service.app.UtilisateurDetails;
import com.bmi.service.app.UtilisateurDetailsService;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter implements HttpSessionListener{
	
	@Autowired
	private UtilisateurDetailsService utilisateurDetailsService;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		/*List<Utilisateur> users =utilisateurRepository.findAll();
		 for (Utilisateur user : users) {
	        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
	                .withUser(user.getUtilisateurEmail())
	                    .password(user.getUtilisateurPassword())
	                    	.roles(user.getUtilisateurType());
	            
	    }
		auth.authenticationProvider(authProvider());
	}*/
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
	        throws Exception {
	    auth.userDetailsService(utilisateurDetailsService);

	}
	/*
@Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<Utilisateur> users =utilisateurRepository.findAll();
		System.out.println("je suis ici \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		 for (Utilisateur user : users) {
	        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
	                .withUser(user.getUtilisateurEmail())
	                    .password(user.getUtilisateurPassword())
	                    	.roles(user.getUtilisateurType());
	            
	    }
		 
	   }*/

@Bean
public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
}
	/*@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(utilisateurDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/login").permitAll()
		.antMatchers("/cfg-account").hasRole("ADMIN")
		.antMatchers("/cfg-account/").hasRole("ADMIN")
		.antMatchers("/saveUser").hasRole("ADMIN")
		.antMatchers("/delete/**").hasRole("ADMIN")
		.antMatchers("/Users/**").hasRole("ADMIN")
		.antMatchers("/GestionRapport/**").hasRole("ADMIN")
		.and()
		.authorizeRequests()
		.antMatchers("/css/**", "/js/**","/img/**","/scss/**","/vendor/**","/cfg-account/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.defaultSuccessUrl("/",true)
		//.defaultSuccessUrl("/default",true)
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();
	}
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		  se.getSession().setMaxInactiveInterval(900);		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}
	

}
