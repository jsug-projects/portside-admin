package jsug.portside.session;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

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
	public Mono<String> index(Model model) {
		return this.sessionRepository.findAll() //
				.collectList() //
				.map(sessions -> {
					model.addAttribute("sessions", sessions);
					return "sessions/index";
				});
	}

	@GetMapping(params = "count")
	public Mono<String> count(Model model) {
		return this.sessionRepository.findAllWithCount() //
				.collectList() //
				.map(sessions -> {
					Collections.sort(sessions, Comparator
							.comparingInt(SessionWithCount::getCount).reversed());
					model.addAttribute("sessions", sessions);
					return "sessions/count";
				});
	}

	@GetMapping(params = "new")
	public String newForm(Model model) {
		return "sessions/new";
	}

	@PostMapping(params = "new")
	public Mono<String> newSubmit(@Validated SessionForm sessionForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return Mono.just("sessions/new");
		}
		Session session = sessionForm.toSession();
		return this.sessionRepository.save(session) //
				.then(Mono.just("redirect:/sessions"));
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
	public Mono<String> update(SessionForm sessionForm, @RequestParam String id) {
		Session session = sessionForm.toSession(id);
		return this.sessionRepository.save(session) //
				.then(Mono.just("redirect:/sessions"));
	}

	@PostMapping(params = { "delete", "id" })
	public Mono<String> delete(@RequestParam String id) {
		return this.sessionRepository.delete(id) //
				.then(Mono.just("redirect:/sessions"));
	}
}
