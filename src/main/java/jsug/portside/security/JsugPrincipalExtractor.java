package jsug.portside.security;

import java.util.Map;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import jsug.portside.JsugUser;

@Component
public class JsugPrincipalExtractor implements PrincipalExtractor {
	@Override
	public JsugUser extractPrincipal(Map<String, Object> map) {
		String github = getValue(map, "login");
		String name = getValue(map, "name");
		return new JsugUser(name.isEmpty() ? github : name, github,
				getValue(map, "email"), getValue(map, "avatar_url"));
	}

	private String getValue(Map<String, Object> map, String key) {
		return Optional.ofNullable(map.get(key)).map(Object::toString).orElse("");
	}
}