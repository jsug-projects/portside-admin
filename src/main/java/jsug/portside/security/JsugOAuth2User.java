package jsug.portside.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jsug.portside.JsugUser;

public class JsugOAuth2User implements OAuth2User {
	private final JsugUser user;
	private final Collection<? extends GrantedAuthority> authorities;
	private final Map<String, Object> attributes;

	public JsugOAuth2User(JsugUser user,
			Collection<? extends GrantedAuthority> authorities,
			Map<String, Object> attributes) {
		this.user = user;
		this.authorities = authorities;
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public String getName() {
		return this.user.getName();
	}

	public JsugUser getUser() {
		return user;
	}
}
