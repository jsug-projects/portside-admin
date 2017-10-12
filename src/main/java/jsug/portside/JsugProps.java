package jsug.portside;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jsug")
@Component
public class JsugProps {
	private Set<String> adminUsers;
	private String apiUrl;
	private String apiUsername;
	private String apiPassword;
	private String uiUrl;

	public Set<String> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(Set<String> adminUsers) {
		this.adminUsers = adminUsers;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiUsername() {
		return apiUsername;
	}

	public void setApiUsername(String apiUsername) {
		this.apiUsername = apiUsername;
	}

	public String getApiPassword() {
		return apiPassword;
	}

	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}

	public String getUiUrl() {
		return uiUrl;
	}

	public void setUiUrl(String uiUrl) {
		this.uiUrl = uiUrl;
	}

	public Consumer<HttpHeaders> toHeaders() {
		return httpHeaders -> {
			if (this.apiUsername != null && this.apiPassword != null) {
				byte[] basic = (this.apiUsername + ":" + this.apiPassword)
						.getBytes(StandardCharsets.UTF_8);
				httpHeaders.add(HttpHeaders.AUTHORIZATION,
						"Basic " + Base64.getEncoder().encodeToString(basic));
			}
		};
	}
}
