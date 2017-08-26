package jsug.portside;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jsug")
@Component
public class JsugProps {
	private Set<String> adminUsers;

	public Set<String> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(Set<String> adminUsers) {
		this.adminUsers = adminUsers;
	}
}
