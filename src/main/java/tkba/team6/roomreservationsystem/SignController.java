package tkba.team6.roomreservationsystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tkba.team6.roomreservationsystem.db.entity.Users;
import tkba.team6.roomreservationsystem.db.model.user.UserService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/login")
public class SignController {
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String index(Model model) {
		return "page/login";
	}

	@PostMapping("")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Users user = userService.authenticate(username, password);
			if(user == null) {
				throw new NullPointerException("username / password invalid");
			}

			session.setAttribute("UserLoginId", user.getId());
			session.setAttribute("UserLoginRole", user.getRole());

			response.sendRedirect(request.getContextPath());
		} catch (NullPointerException e) {
			session.setAttribute("errorMessage", e.getMessage());
		}

		return "page/login";
	}
}
