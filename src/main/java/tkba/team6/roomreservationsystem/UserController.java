package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/user")
	public String index(Model model) {
		return "page/user/index";
	}

	@GetMapping("/user/create")
	public String create(Model model) {
		return "page/user/create";
	}

	@GetMapping("/user/detail")
	public String detail(Model model) {
		return "page/user/detail";
	}
}
