package jsug.portside.security;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationProperties;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@ConfigurationProperties(prefix = "security.oauth2.client")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JsugOAuth2UserService oAuth2UserService;
	private Map<String, ClientRegistrationProperties> registrations;

	public SecurityConfig(JsugOAuth2UserService oAuth2UserService) {
		this.oAuth2UserService = oAuth2UserService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.requestMatchers(StaticResourceRequest.toCommonLocations()).permitAll() //
				.anyRequest().hasRole("ADMIN") //
				.and() //
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and() //
				.oauth2Login() //
				.userInfoEndpoint().userInfoService(oAuth2UserService);
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = this.registrations.values().stream() //
				.peek(c -> c.setRegistrationId(c.getClientId())) //
				.map(c -> new ClientRegistration.Builder(c).build()) //
				.collect(Collectors.toList());
		return new InMemoryClientRegistrationRepository(registrations);
	}

	public Map<String, ClientRegistrationProperties> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(
			Map<String, ClientRegistrationProperties> registrations) {
		this.registrations = registrations;
	}
}