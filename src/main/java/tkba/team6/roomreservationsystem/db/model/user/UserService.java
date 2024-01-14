package tkba.team6.roomreservationsystem.db.model.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tkba.team6.roomreservationsystem.db.repository.UserRepository;
import tkba.team6.roomreservationsystem.db.entity.Users;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public final List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public final Users getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public final void saveUser(Users Users) {
        userRepository.save(Users);
    }

    public final Users authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public boolean getAccessMenuUser() {
        return false;
    }

    public boolean getAccessMenuRoom() {
        return false;
    }

    public boolean getAccessMenuRoomReservation() {
        return false;
    }
}
