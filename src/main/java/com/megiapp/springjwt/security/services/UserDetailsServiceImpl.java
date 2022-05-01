package com.megiapp.springjwt.security.services;

import com.megiapp.springjwt.models.User;
import com.megiapp.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
//marrja e users nga db
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
}

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	UserRepository userRepository;
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws NullPointerException  {
//		User user = userRepository.findByUsername(username);
//		if (user == null) {
//			throw  new UsernameNotFoundException("");
//		}else  {
//			return UserDetailsImpl.build(user);
//		}
//
//	}
//
//}
