package com.app.Service;

import java.util.List;
import java.util.UUID;

import javax.imageio.spi.RegisterableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.Token_Type;
import com.app.model.User;
import com.app.model.UserDao;
import com.app.model.VerificationDao;
import com.app.model.VerificationToken;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationDao verificationDao;
	
	public void register(User user) { 
		user.setRole("ROLE_USER");
		userDao.save(user);
	}
	
	public void save(User user) { 
		userDao.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userDao.findByEmail(email);
		
		if(user == null) 
			return null;
		
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
		String password = user.getPassword();
		Boolean enabled = user.getEnabled();
		return new org.springframework.security.core.userdetails.User(email, password,enabled,true,true,true, auth);
	}
	
	public String createToken(User user) {
		VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user, Token_Type.REGISTRATION);
		verificationDao.save(token);
		return token.getToken();
	}

	public VerificationToken getVerificationToken(String token) {
		return verificationDao.findByToken(token);
	}

	public void deleteToken(VerificationToken token) {

		verificationDao.delete(token);
	}

	public User get(String email) {
		return userDao.findByEmail(email);
	}
}

