package jsug.portside.session;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sessions")
public class SessionController {
	private final SessionRepository sessionRepository;

	public SessionController(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@GetMapping
	public String index(Model model) {
		List<Session> sessions = sessionRepository.findAll();
		model.addAttribute("sessions", sessions);
		return "sessions/index";
	}
}
