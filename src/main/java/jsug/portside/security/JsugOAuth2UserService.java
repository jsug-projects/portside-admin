package jsug.portside.security;

import static java.util.Collections.emptyList;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.client.user.OAuth2UserService;
import org.springframework.security.oauth2.client.user.UserInfoRetriever;
import org.springframework.security.oauth2.client.user.nimbus.NimbusUserInfoRetriever;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import jsug.portside.JsugProps;
import jsug.portside.JsugUser;

@Component
public class JsugOAuth2UserService implements OAuth2UserService {
	private UserInfoRetriever userInfoRetriever = new NimbusUserInfoRetriever();
	private final JsugProps jsugProps;

	public JsugOAuth2UserService(JsugProps jsugProps) {
		this.jsugProps = jsugProps;
	}

	@Override
	public OAuth2User loadUser(OAuth2ClientAuthenticationToken token)
			throws OAuth2AuthenticationException {
		Map<String, Object> attributes = userInfoRetriever.retrieve(token);
		return new JsugOAuth2User(extractPrincipal(attributes),
				extractAuthorities(attributes), attributes);
	}

	private JsugUser extractPrincipal(Map<String, Object> map) {
		String github = getValue(map, "login");
		String name = getValue(map, "name");
		return new JsugUser(name.isEmpty() ? github : name, github,
				getValue(map, "email"), getValue(map, "avatar_url"));
	}

	private String getValue(Map<String, Object> map, String key) {
		return Optional.ofNullable(map.get(key)).map(Object::toString).orElse("");
	}

	private List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		String login = (String) map.get("login");
		if (jsugProps.getAdminUsers().contains(login)) {
			return createAuthorityList("ROLE_ADMIN", "ROLE_ACTUATOR");
		}
		return emptyList();
	}
}
