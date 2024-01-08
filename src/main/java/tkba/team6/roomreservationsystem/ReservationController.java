package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
	@GetMapping("/reservation")
	public String index(Model model) {
		return "page/reservation/index";
	}

	@GetMapping("/reservation/create")
	public String create(Model model) {
		return "page/reservation/create";
	}

	@GetMapping("/reservation/review")
	public String review(Model model) {
		return "page/reservation/review";
	}
	
}
