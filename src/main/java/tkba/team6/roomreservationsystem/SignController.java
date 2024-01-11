package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/login")
public class SignController {
	@GetMapping("")
	public String index(Model model) {
		return "page/login";
	}

	@PostMapping("")
	public String login(Model model) {
		return "page/login";
	}
}
