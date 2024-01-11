package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {
	@GetMapping("")
	public String index(Model model) {
		return "page/room/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		return "page/room/create";
	}

	@PostMapping("/create")
	public String createConfirm(Model model) {
		return "page/room/create";
	}

	@GetMapping("/{id}")
	public String detail(Model model, @PathVariable Long id) {
		model.addAttribute("roomName", "Hello, Thymeleaf!");
		return "page/room/detail";
	}

	@PostMapping("/{id}")
	public String detailUpdate(Model model, @PathVariable Long id) {
		model.addAttribute("roomName", "Hello, Thymeleaf!");
		return "page/room/detail";
	}
}
