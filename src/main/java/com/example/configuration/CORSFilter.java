package com.example.configuration;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	@Value("${allowed.Origin}")
	private String allowOrigins;

	private String origin="*";

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		Arrays.stream(allowOrigins.split(",")).forEach(str -> {
			if(str.trim().equalsIgnoreCase(request.getHeader("Origin"))){
				origin = request.getHeader("Origin");
			}
		});

		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "7200");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, "
				+ "X-Requested-With, X-Digest, Access-Control-Allow-Origin, Authentication, X-Once, JSESSIONID");
		response.setHeader("Access-Control-Expose-Headers", "X-Secret, X-TokenAccess, WWW-Authenticate, Set-Cookie");
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
		//chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {

	}

	public void destroy() {}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

}