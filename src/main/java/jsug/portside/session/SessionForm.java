package jsug.portside.session;

public class SessionForm {
	private String title;
	private String description;
	private String speaker;

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

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	@Override
	public String toString() {
		return "SessionForm{" + "title='" + title + '\'' + ", description='" + description
				+ '\'' + ", speaker='" + speaker + '\'' + '}';
	}

	public Session toSession() {
		return new Session(null, this.title, this.description, this.speaker);
	}

	public static SessionForm fromSession(Session session) {
		SessionForm sessionForm = new SessionForm();
		sessionForm.setTitle(session.getTitle());
		sessionForm.setDescription(session.getDescription());
		sessionForm.setSpeaker(session.getSpeaker());
		return sessionForm;
	}
}
