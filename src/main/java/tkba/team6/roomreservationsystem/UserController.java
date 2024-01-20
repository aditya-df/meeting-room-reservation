package tkba.team6.roomreservationsystem;

import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.query.QueryArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tkba.team6.roomreservationsystem.db.entity.Users;
import tkba.team6.roomreservationsystem.db.model.user.UserService;
import tkba.team6.roomreservationsystem.db.repository.UserRepository;
import tkba.team6.roomreservationsystem.utilty.Hasing;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public String index(HttpServletRequest request, Model model) {
		List<Users> UserList = userService.getAllUsers();
		model.addAttribute("UserList", UserList);
		return "page/user/index";
	}

	@GetMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "page/user/create";
	}

	@PostMapping("/create")
	public String createConfirm(HttpServletRequest request, RedirectAttributes redirectAttributes,
			HttpServletResponse response, Model model) {
		try {
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String passwordHasing = Hasing.converTextToSHA256(password);
			String role = request.getParameter("role");

			Users users = new Users();
			users.setUsername(username);
			users.setEmail(email);
			users.setPassword(passwordHasing);
			users.setRole(role);

			userRepository.save(users);
			redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
		} catch (QueryArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error creating user: " + e.getMessage());
		}

		return "redirect:/user";
	}

	@GetMapping("/edit/{id}")
	public String detail(Model model, @PathVariable Long id) {
		model.addAttribute("Users", userService.getUserById(id));
		return "page/user/detail";
	}

	@PostMapping("/update/{id}")
	public String updateUser(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model,
			Users users, @PathVariable Long id) {
		try {
			String password = request.getParameter("password");

			// get user from database by id
			Users existingUser = userService.getUserById(id);
			existingUser.setId(id);
			existingUser.setEmail(users.getEmail());
			if (password != "") {
				String passwordHasing = Hasing.converTextToSHA256(password);
				existingUser.setPassword(passwordHasing);
			}

			// save updated user object
			userService.updateUser(existingUser);
			redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
		} catch (QueryArgumentException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error updated user: " + e.getMessage());
		}

		return "redirect:/user";
	}

	@GetMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		userService.deleteUserById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Deleted User successfully!");
		return "redirect:/user";
	}
}
