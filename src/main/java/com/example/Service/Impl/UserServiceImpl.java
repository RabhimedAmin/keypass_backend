package com.example.Service.Impl;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Member;
import com.example.repository.MemberRepository;

/**
 * Created by hamed
 */
@Service
@Transactional
public class UserServiceImpl implements  UserDetailsService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
	@Autowired
	private MemberRepository memberRepository;

	@Override
	@Transactional(readOnly =true)
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		LOGGER.info("find  user where login=" + login);
		Member user = memberRepository.findFirstByEmail(login);
		
		if(user == null){
			throw new UsernameNotFoundException("user inexistant avec le login: "+ login);
		}

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
        
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
        		true,true,true,true, grantedAuthorities);
	}
}
