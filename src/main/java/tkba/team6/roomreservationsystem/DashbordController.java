package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashbordController {
	@GetMapping("/")
	public String index(Model model) {
		return "page/dashbord";
	}
}
