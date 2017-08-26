package jsug.portside.session;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository {
	private Map<String, Session> sessions;

	public List<Session> findAll() {
		return new ArrayList<>(sessions.values());
	}

	@PostConstruct
	public void init() {
		LinkedHashMap<String, Session> linkedHashMap = new LinkedHashMap<>();
		for (int i = 0; i < 20; i++) {
			String key = String.format("id%02d", i);
			Session session = new Session(key, "title" + i, "desc" + i, "speaker" + 1);
			linkedHashMap.put(key, session);
		}
		this.sessions = Collections.unmodifiableMap(linkedHashMap);
	}
}
