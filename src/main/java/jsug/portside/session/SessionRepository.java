package jsug.portside.session;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import jsug.portside.JsugProps;

@Repository
public class SessionRepository {
	private final RestTemplate restTemplate;
	private final JsugProps props;

	public SessionRepository(RestTemplate restTemplate, JsugProps props) {
		this.restTemplate = restTemplate;
		this.props = props;
	}

	public List<Session> findAll() {
		return Arrays.asList(restTemplate.getForObject(props.getApiUrl() + "/sessions",
				Session[].class));
	}

	public List<SessionWithCount> findAllWithCount() {
		return Arrays.asList(restTemplate.getForObject(
				props.getApiUrl() + "/sessions/withAttendeeCount",
				SessionWithCount[].class, props.getApiUrl()));
	}

	public Session save(Session session) {
		String sessionId = session.getId();
		if (StringUtils.isEmpty(sessionId)) {
			restTemplate.postForObject(props.getApiUrl() + "/sessions", session,
					Void.class);
			return session;
		}

		restTemplate.put(props.getApiUrl() + "/sessions/{sessionId}", session, sessionId);
		return session;
	}
}
