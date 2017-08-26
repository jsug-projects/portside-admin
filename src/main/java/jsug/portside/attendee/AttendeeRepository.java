package jsug.portside.attendee;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class AttendeeRepository {
	private Map<String, Attendee> attendees;

	public List<Attendee> findAll() {
		return new ArrayList<>(attendees.values());
	}

	@PostConstruct
	public void init() {
		LinkedHashMap<String, Attendee> linkedHashMap = new LinkedHashMap<>();
		for (int i = 0; i < 20; i++) {
			String key = String.format("id%02d", i);
			linkedHashMap.put(key, new Attendee(key, "attendee" + i + "@example.com"));
		}
		this.attendees = Collections.unmodifiableMap(linkedHashMap);
	}
}
