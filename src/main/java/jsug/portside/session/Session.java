package jsug.portside.session;

public class Session {
	private final String id;
	private final String title;
	private final String description;
	private final String speaker;

	public Session(String id, String title, String description, String speaker) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.speaker = speaker;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getSpeaker() {
		return speaker;
	}
}
