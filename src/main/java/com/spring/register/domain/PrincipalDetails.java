package com.spring.register.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private Users users;
	
	public PrincipalDetails(Users users) {
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getEmail();
	}

	//살아있는지물어봄 (=생존하고 있는 id인지? 휴면id면 false)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	private Map<String, Object> attributes;
	
	public PrincipalDetails(Users users, Map<String, Object> attributes) {
		this.users = users;
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes(){
		return attributes; //Map으로 받는 이유: 이메일, 프로필이 value값이라서
	}
	
	@Override
	public String getName() {
		return null;
	}
	

}
