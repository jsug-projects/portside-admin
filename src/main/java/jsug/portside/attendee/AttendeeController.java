package jsug.portside.attendee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("attendees")
public class AttendeeController {
	private final AttendeeRepository attendeeRepository;

	public AttendeeController(AttendeeRepository attendeeRepository) {
		this.attendeeRepository = attendeeRepository;
	}

	@GetMapping
	public Mono<String> index(Model model) {
		return attendeeRepository.findAll() //
				.collectList() //
				.map(attendees -> {
					model.addAttribute("attendees", attendees);
					return "attendees/index";
				});
	}
}
