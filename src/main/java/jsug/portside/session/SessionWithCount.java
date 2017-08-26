package jsug.portside.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionWithCount {
	private final Session session;
	private final int count;

	@JsonCreator
	public SessionWithCount(@JsonProperty("session") Session session,
			@JsonProperty("attendeeCount") int count) {
		this.session = session;
		this.count = count;
	}

	public Session getSession() {
		return session;
	}

	public int getCount() {
		return count;
	}
}
