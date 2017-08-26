package jsug.portside;

import java.io.Serializable;

public class JsugUser implements Serializable {
	private final String name;
	private final String github;
	private final String email;
	private final String avatarUrl;

	public JsugUser(String name, String github, String email, String avatarUrl) {
		this.name = name;
		this.github = github;
		this.email = email;
		this.avatarUrl = avatarUrl;
	}

	public String getName() {
		return name;
	}

	public String getGithub() {
		return github;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

    @Override
    public String toString() {
        return "JsugUser{" +
                "name='" + name + '\'' +
                ", github='" + github + '\'' +
                ", email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
