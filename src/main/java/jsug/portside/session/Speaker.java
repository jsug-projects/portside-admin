package jsug.portside.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Speaker {
	private final String id;
	private final String name;
	private final String belongTo;
	private final String profile;
	private final String imageUrl;

	@JsonCreator
	public Speaker(@JsonProperty("id") String id, @JsonProperty("name") String name,
			@JsonProperty("belongTo") String belongTo,
			@JsonProperty("profile") String profile,
			@JsonProperty("imageUrl") String imageUrl) {
		this.id = id;
		this.name = name;
		this.belongTo = belongTo;
		this.profile = profile;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public String getProfile() {
		return profile;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public String toString() {
		return "Speaker{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", belongTo='"
				+ belongTo + '\'' + ", profile='" + profile + '\'' + ", imageUrl='"
				+ imageUrl + '\'' + '}';
	}
}
