package jsug.portside.audit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("audit")
public class AuditController {
	private final AuditEventRepository auditEventRepository;

	public AuditController(AuditEventRepository auditEventRepository) {
		this.auditEventRepository = auditEventRepository;
	}

	@GetMapping
	public String index(Model model) {
		Instant threeDaysAgo = Instant.now().minus(3, ChronoUnit.DAYS);
		List<AuditEvent> auditEvents = this.auditEventRepository
				.find(Date.from(threeDaysAgo));
		Collections.reverse(auditEvents);
		model.addAttribute("auditEvents", auditEvents);
		return "audit/index";
	}
}
