package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
	@GetMapping("")
	public String index(Model model) {
		return "page/user/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		return "page/user/create";
	}

	@PostMapping("/create")
	public String createConfirm(Model model) {
		return "page/user/create";
	}

	@GetMapping("/{id}")
	public String detail(Model model, @PathVariable Long id) {
		model.addAttribute("Username", "Hello, Thymeleaf!");
		return "page/user/detail";
	}

	@PostMapping("/{id}")
	public String detailUpdate(HttpServletRequest request, Model model, @PathVariable Long id) {
		String customAttribute = (String) request.getAttribute("customAttribute");
		model.addAttribute("Username", customAttribute);
		return "page/user/detail";
	}
}
