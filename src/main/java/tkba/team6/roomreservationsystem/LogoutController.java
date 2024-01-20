package tkba.team6.roomreservationsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	@GetMapping("")
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		session.invalidate();

		return "redirect:/login";
	}
}
