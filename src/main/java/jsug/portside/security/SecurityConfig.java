package jsug.portside.security;

import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JsugOAuth2UserService oAuth2UserService;
	private final ClientRegistrationRepository clientRegistrationRepository;

	public SecurityConfig(JsugOAuth2UserService oAuth2UserService,
			ClientRegistrationRepository clientRegistrationRepository) {
		this.oAuth2UserService = oAuth2UserService;
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring() //
				.mvcMatchers("bootstrap/**", "dist/**", "plugins/**") //
				.requestMatchers(StaticResourceRequest.toCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.anyRequest().hasRole("ADMIN") //
				.and() //
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and() //
				.oauth2Login() //
				.clients(clientRegistrationRepository) //
				.userInfoEndpoint().userService(oAuth2UserService);
	}
}