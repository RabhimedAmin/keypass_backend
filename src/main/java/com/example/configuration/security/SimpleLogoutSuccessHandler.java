package com.example.configuration.security;

/**
 * 
 */

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;


/**
 * @author h.rabhi on 2017
 *
 */
@Component
public class SimpleLogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler implements LogoutHandler, LogoutSuccessHandler {


	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null){ 
			new SecurityContextLogoutHandler().logout(request, response, auth);
			auth.setAuthenticated(false);
			System.err.println("/////////// principal user "+ auth.getName());
		}
		
		SecurityContextHolder.getContext().setAuthentication(null);
		
		response.setStatus(HttpServletResponse.SC_OK);
		Principal test =request.getUserPrincipal();
		String str = test!=null? test.getName() : "principal is null";
		System.err.println("+++++++++++ principal user"+ str);
		System.err.println("----------- remote user: "+ request.getRemoteUser());
		new SecurityContextLogoutHandler().logout(request, response, authentication);
		try {
			request.logout();
		} catch ( ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//authentication.setAuthenticated(false);
		SecurityContextHolder.getContext().setAuthentication(null);
		request.getSession().invalidate();
		super.onLogoutSuccess(request, response, authentication);
	}
}
