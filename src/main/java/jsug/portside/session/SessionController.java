package jsug.portside.session;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("sessions")
public class SessionController {
	private final SessionRepository sessionRepository;

	public SessionController(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@ModelAttribute
	SessionForm setupForm() {
		SessionForm sessionForm = new SessionForm();
		Deque<SessionForm.SpeakerForm> speakerForms = new LinkedList<>();
		speakerForms.add(new SessionForm.SpeakerForm());
		sessionForm.setSpeakers(speakerForms);
		return sessionForm;
	}

	@GetMapping
	public String index(Model model) {
		List<Session> sessions = this.sessionRepository.findAll();
		model.addAttribute("sessions", sessions);
		return "sessions/index";
	}

	@GetMapping(params = "count")
	public String count(Model model) {
		List<SessionWithCount> sessions = this.sessionRepository.findAllWithCount();
		model.addAttribute("sessions", sessions);
		return "sessions/count";
	}

	@GetMapping(params = "new")
	public String newForm(Model model) {
		return "sessions/new";
	}

	@PostMapping(params = "new")
	public String newSubmit(@Validated SessionForm sessionForm, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "sessions/new";
		}
		Session session = sessionForm.toSession();
		this.sessionRepository.save(session);
		return "redirect:/sessions";
	}

	@PostMapping(params = "add-speaker")
	public String addSpeaker(SessionForm sessionForm) {
		sessionForm.getSpeakers().add(new SessionForm.SpeakerForm());
		return "sessions/new";
	}

	@PostMapping(params = "remove-speaker")
	public String removeSpeaker(SessionForm sessionForm) {
		sessionForm.getSpeakers().removeLast();
		return "sessions/new";
	}

	@GetMapping(params = { "update", "id" })
	public String updateForm(SessionForm sessionForm, Model model,
			@RequestParam String id) {
		model.addAttribute("id", id);
		return "sessions/update";
	}

	@PostMapping(params = { "update", "id" })
	public String update(SessionForm sessionForm, @RequestParam String id) {
		Session session = sessionForm.toSession(id);
		this.sessionRepository.save(session);
		return "redirect:/sessions";
	}
}
