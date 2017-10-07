package jsug.portside.attendee;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import jsug.portside.JsugProps;
import reactor.core.publisher.Flux;

@Repository
public class AttendeeRepository {
	private final WebClient webClient;

	public AttendeeRepository(JsugProps props, WebClient.Builder builder) {
		this.webClient = builder.baseUrl(props.getApiUrl()).build();
	}

	public Flux<Attendee> findAll() {
		return this.webClient.get().uri("attendees") //
				.retrieve() //
				.bodyToFlux(Attendee.class);
	}
}
