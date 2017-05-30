package com.example.configuration.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Security configuration
 */
@Configuration 
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
	 
	 public SecurityConfiguration() {
	        super();
	        logger.info("loading SecurityConfig ......................... ");
	    }
	 
	 @Autowired
	private UserDetailsService userDetailsService;
	 
	@Autowired
	private SimpleAuthenticationSuccessHandler authenticationSuccessHandler;

//	@Autowired
//	private AuthenticationFailureHandler authenticationFailureHandler;

	
	@Autowired
	private SimpleLogoutSuccessHandler SimpleLogoutSuccessHandler;

	private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;

	@Autowired
	private SimpleAuthenticationFailureHandler authenticationFailureHandler;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("*.{ico}").and();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
				.httpBasic().and().csrf().disable().authorizeRequests()
				.antMatchers("/v2/api-docs", "/swagger-resources/**", "swagger-resources/configuration/ui","/swagger-ui.html","/webjars/**")
				.permitAll()
				.antMatchers("/code/**").permitAll()
				.antMatchers("/resetPW/").permitAll()
				.antMatchers("/**").permitAll()
				.and().formLogin()
				.loginProcessingUrl("/login").failureHandler(authenticationFailureHandler)
				.authenticationDetailsSource(authenticationDetailsSource)
				.successHandler(authenticationSuccessHandler)
				.and()
				.logout().logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true)
				.logoutSuccessHandler(SimpleLogoutSuccessHandler).addLogoutHandler(SimpleLogoutSuccessHandler)
				.deleteCookies("JSESSIONID").permitAll().and().rememberMe().tokenValiditySeconds(7200).key("keyPass").and()
				.sessionManagement().invalidSessionUrl("/invalidate").sessionFixation().newSession()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).enableSessionUrlRewriting(false).maximumSessions(1).expiredUrl("/invalidate").sessionRegistry(sessionRegistry());
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
}
