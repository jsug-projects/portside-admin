package jsug.portside;

import static java.util.Collections.emptyList;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JsugAuthoritiesExtractor implements AuthoritiesExtractor {
	private final JsugProps jsugProps;

	public JsugAuthoritiesExtractor(JsugProps jsugProps) {
		this.jsugProps = jsugProps;
	}

	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		String login = (String) map.get("login");
		if (jsugProps.getAdminUsers().contains(login)) {
			return createAuthorityList("ROLE_ADMIN", "ROLE_ACTUATOR");
		}
		return emptyList();
	}
}
