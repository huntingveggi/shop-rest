package de.is.project.shop.gui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@Autowired
	SecurityContextRepository repository;

/*	@Autowired
	RememberMeServices rememberMeServices;*/

	@RequestMapping(method = RequestMethod.GET)
	public void login() {
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String performLogin(@RequestParam("email") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			Authentication auth = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			repository.saveContext(SecurityContextHolder.getContext(), request, response);
			//rememberMeServices.loginSuccess(request, response, auth);
			return "{\"status\": true}";
		} catch (BadCredentialsException ex) {
			return "{\"status\": false, \"error\": \"Bad Credentials\"}";
		}
	}
}
