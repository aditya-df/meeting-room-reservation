package tkba.team6.roomreservationsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tkba.team6.roomreservationsystem.db.entity.Users;
import tkba.team6.roomreservationsystem.db.model.user.UserService;
import tkba.team6.roomreservationsystem.db.repository.UserRepository;
import tkba.team6.roomreservationsystem.utilty.Hasing;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		insertUser();
	}

	private void insertUser() {
		List<Users> UserList = userService.getAllUsers();
		if (UserList.size() == 0) {
			String passwordHasingAdmin = Hasing.converTextToSHA256("admin");

			Users UserAdmin = new Users();
			UserAdmin.setUsername("admin");
			UserAdmin.setEmail("admin@test.test");
			UserAdmin.setPassword(passwordHasingAdmin);
			UserAdmin.setRole("Admin");
			userRepository.save(UserAdmin);

			String passwordHasing = Hasing.converTextToSHA256("user");

			Users User = new Users();
			User.setUsername("user");
			User.setEmail("user@test.test");
			User.setPassword(passwordHasing);
			User.setRole("User");
			userRepository.save(User);
		}
	}
}