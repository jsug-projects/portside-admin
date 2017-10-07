package jsug.portside.session;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import jsug.portside.JsugProps;
import reactor.core.publisher.Mono;

@Repository
public class SessionRepository {
	private final WebClient webClient;

	public SessionRepository(JsugProps props, WebClient.Builder builder) {
		this.webClient = builder.baseUrl(props.getApiUrl()).build();
	}

	public List<Session> findAll() {
		return this.webClient.get().uri("sessions") //
				.retrieve() //
				.bodyToFlux(Session.class) //
				.collectList() //
				.block();
	}

	public List<SessionWithCount> findAllWithCount() {
		return this.webClient.get().uri("sessions/withAttendeeCount") //
				.retrieve() //
				.bodyToFlux(SessionWithCount.class) //
				.collectList() //
				.block();
	}

	public Session save(Session session) {
		String sessionId = session.getId();
		if (StringUtils.isEmpty(sessionId)) {
			return this.webClient.post().uri("sessions") //
					.syncBody(session) //
					.retrieve() //
					.bodyToMono(Void.class) //
					.then(Mono.just(session)) //
					.block();
		}
		return this.webClient.put().uri("sessions/{sessionId}", sessionId)
				.syncBody(session).retrieve().bodyToMono(Void.class)
				.then(Mono.just(session)).block();
	}
}
