package jsug.portside.session;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sessions")
public class SessionController {
	private final SessionRepository sessionRepository;

	public SessionController(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@ModelAttribute
	SessionForm setupForm() {
		return new SessionForm();
	}

	@GetMapping
	public String index(Model model) {
		List<Session> sessions = this.sessionRepository.findAll();
		model.addAttribute("sessions", sessions);
		return "sessions/index";
	}

	@GetMapping(params = "new")
	public String newForm(Model model) {
		return "sessions/new";
	}

	@PostMapping(params = "new")
	public String newSubmit(SessionForm sessionForm) {
		Session session = sessionForm.toSession();
		this.sessionRepository.save(session);
		return "redirect:/sessions";
	}
}
