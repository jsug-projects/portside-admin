package jsug.portside.session;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Session {
	private final String id;
	private final String title;
	private final String description;
	private final String speaker;

	@JsonCreator
	public Session(@JsonProperty("id") String id, @JsonProperty("title") String title,
				   @JsonProperty("description") String description,
				   @JsonProperty("speaker") String speaker) {
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
