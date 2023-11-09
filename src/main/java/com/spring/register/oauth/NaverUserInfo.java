package com.spring.register.oauth;
import java.util.Map;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo{
	
	private Map<String, Object> attributes;

	@Override
	public String getProviderId() {
		return (String) attributes.get("response.id");
	}

	@Override
	public String getProvider() {
		return "naver";
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}
}