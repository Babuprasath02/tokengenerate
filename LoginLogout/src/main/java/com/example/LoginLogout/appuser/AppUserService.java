package com.example.LoginLogout.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LoginLogout.registeration.token.ConfirmationToken;
import com.example.LoginLogout.registeration.token.ConfirmationTokenService;

@Service
public class AppUserService implements UserDetailsService{
	private final static String USER_NOT_FOUND_MSG="user with email %s not found";
	private AppUserRepository appUserRepo;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ConfirmationTokenService confirmationTokenService;
	


	public AppUserService(AppUserRepository appUserRepo, BCryptPasswordEncoder bCryptPasswordEncoder,ConfirmationTokenService confirmationTokenService) {
		super();
		this.appUserRepo = appUserRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.confirmationTokenService=confirmationTokenService;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return appUserRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	public String signUpUser(AppUser appUser) {
		boolean userExist=appUserRepo.findByEmail(appUser.getEmail()).isPresent();
		if(userExist) {
			throw new IllegalStateException("emial already exist");
		}
		String encodePassword=bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodePassword);
		appUserRepo.save(appUser);
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token,LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		return token;
	}
//	 public int enableAppUser(String email) {
//	        return appUserRepo.enableAppUser(email);
//	    }
}
