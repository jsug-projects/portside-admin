package jsug.portside.session;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import jsug.portside.JsugProps;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SessionRepository {
	private final WebClient webClient;

	public SessionRepository(JsugProps props, WebClient.Builder builder) {
		this.webClient = builder.defaultHeaders(props.toHeaders())
				.baseUrl(props.getApiUrl()).build();
	}

	public Flux<Session> findAll() {
		return this.webClient.get().uri("sessions") //
				.retrieve() //
				.bodyToFlux(Session.class);
	}

	public Flux<SessionWithCount> findAllWithCount() {
		return this.webClient.get().uri("sessions/withAttendeeCount") //
				.retrieve() //
				.bodyToFlux(SessionWithCount.class);
	}

	public Mono<Session> save(Session session) {
		String sessionId = session.getId();
		if (StringUtils.isEmpty(sessionId)) {
			return this.webClient.post().uri("sessions") //
					.syncBody(session) //
					.retrieve() //
					.bodyToMono(Void.class) //
					.then(Mono.just(session));
		}
		return this.webClient.put().uri("sessions/{sessionId}", sessionId)
				.syncBody(session).retrieve().bodyToMono(Void.class)
				.then(Mono.just(session));
	}

	public Mono<Void> delete(String id) {
		return this.webClient.delete().uri("sessions/{sessionId}", id) //
				.exchange() //
				.then();
	}
}
