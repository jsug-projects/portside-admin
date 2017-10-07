package jsug.portside.session;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Session {
	private final String id;
	private final String title;
	private final String description;
	private final List<Speaker> speakers;

	@JsonCreator
	public Session(@JsonProperty("id") String id, @JsonProperty("title") String title,
			@JsonProperty("description") String description,
			@JsonProperty("speakers") List<Speaker> speakers) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.speakers = speakers;
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

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public String getSpeakerNames() {
		return this.speakers.stream().map(Speaker::getName)
				.collect(Collectors.joining(","));
	}

	@Override
	public String toString() {
		return "Session{" + "id='" + id + '\'' + ", title='" + title + '\''
				+ ", description='" + description + '\'' + ", speakers=" + speakers + '}';
	}
}
