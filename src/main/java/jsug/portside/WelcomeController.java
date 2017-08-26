package jsug.portside;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WelcomeController {

	@GetMapping
	public String welcome(Principal principal) {
		System.out.println(principal);
		return "index";
	}
}
