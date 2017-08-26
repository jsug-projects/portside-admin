package jsug.portside.attendee;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Attendee {
	private final String id;
	private final String email;

	@JsonCreator
	public Attendee(@JsonProperty("id") String id, @JsonProperty("email") String email) {
		this.id = id;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
}
