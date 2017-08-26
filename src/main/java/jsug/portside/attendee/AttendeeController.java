package jsug.portside.attendee;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("attendees")
public class AttendeeController {
	private final AttendeeRepository attendeeRepository;

	public AttendeeController(AttendeeRepository attendeeRepository) {
		this.attendeeRepository = attendeeRepository;
	}

	@GetMapping
	public String index(Model model) {
		List<Attendee> attendees = attendeeRepository.findAll();
		model.addAttribute("attendees", attendees);
		return "attendees/index";
	}
}
