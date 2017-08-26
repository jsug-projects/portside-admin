package jsug.portside.attendee;

public class Attendee {
	private final String id;
	private final String email;

	public Attendee(String id, String email) {
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
