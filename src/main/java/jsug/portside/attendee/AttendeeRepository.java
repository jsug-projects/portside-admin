package jsug.portside.attendee;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import jsug.portside.JsugProps;

@Repository
public class AttendeeRepository {
	private final WebClient webClient;

	public AttendeeRepository(JsugProps props, WebClient.Builder builder) {
		this.webClient = builder.baseUrl(props.getApiUrl()).build();
	}

	public List<Attendee> findAll() {
		return this.webClient.get().uri("attendees") //
				.retrieve() //
				.bodyToFlux(Attendee.class) //
				.collectList() //
				.block();
	}
}
