package com.example.configuration.security;

/**
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author h.rabhi on 2017
 *
 */
@Component
public class SimpleAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAuthenticationSuccessHandler.class);

    private final ObjectMapper mapper;

    @Autowired
    SimpleAuthenticationSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
    		throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        UserDetails  userDetails = (UserDetails) authentication.getPrincipal();
        //String secret = TimeBasedOneTimePassword.generateSecret();
        //userRepository.updateSecret(authentication.getName(), secret);

        //User user = userRepository.findOneByLogin(authentication.getName());
        LOGGER.info(userDetails.getUsername() + " is connected ");

        PrintWriter writer = response.getWriter();
        //mapper.writeValue(writer, secret);
       // mapper.writeValue(writer, user);
        
        writer.flush();
        writer.close();
    }
}
