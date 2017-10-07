package jsug.portside.session;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class SessionForm {
	@NotEmpty
	private String title;
	@NotEmpty
	private String description;
	@NotEmpty
	@Valid
	private Deque<SpeakerForm> speakers;

	public static class SpeakerForm {
		private String id;
		@NotEmpty
		private String name;
		@NotEmpty
		private String belongTo;
		@NotEmpty
		private String profile;
		@NotEmpty
		@URL
		private String imageUrl;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBelongTo() {
			return belongTo;
		}

		public void setBelongTo(String belongTo) {
			this.belongTo = belongTo;
		}

		public String getProfile() {
			return profile;
		}

		public void setProfile(String profile) {
			this.profile = profile;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public SpeakerForm apply(Speaker speaker) {
			this.setId(speaker.getId());
			this.setName(speaker.getName());
			this.setBelongTo(speaker.getBelongTo());
			this.setProfile(speaker.getProfile());
			this.setImageUrl(speaker.getImageUrl());
			return this;
		}

		public static SpeakerForm fromSpeaker(Speaker speaker) {
			return new SpeakerForm().apply(speaker);
		}

		public Speaker toSpeaker() {
			return new Speaker(id, name, belongTo, profile, imageUrl);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Deque<SpeakerForm> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(Deque<SpeakerForm> speakers) {
		this.speakers = speakers;
	}

	public Session toSession() {
		return this.toSession(null);
	}

	public Session toSession(String id) {
		return new Session(id, this.title, this.description, this.speakers.stream()
				.map(SpeakerForm::toSpeaker).collect(Collectors.toList()));
	}

	public SessionForm apply(Session session) {
		this.setTitle(session.getTitle());
		this.setDescription(session.getDescription());
		this.setSpeakers(new LinkedList<>(session.getSpeakers().stream()
				.map(SpeakerForm::fromSpeaker).collect(Collectors.toList())));
		return this;
	}

	public static SessionForm fromSession(Session session) {
		SessionForm sessionForm = new SessionForm();
		return sessionForm.apply(session);
	}
}
