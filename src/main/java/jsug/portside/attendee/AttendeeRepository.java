package jsug.portside.attendee;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import jsug.portside.JsugProps;

@Repository
public class AttendeeRepository {
	private final RestTemplate restTemplate;
	private final JsugProps props;

	public AttendeeRepository(RestTemplate restTemplate, JsugProps props) {
		this.restTemplate = restTemplate;
		this.props = props;
	}

	public List<Attendee> findAll() {
		return Arrays.asList(restTemplate.getForObject(props.getApiUrl() + "/attendees",
				Attendee[].class));
	}
}
