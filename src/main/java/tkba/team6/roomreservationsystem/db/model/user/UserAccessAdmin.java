package tkba.team6.roomreservationsystem.db.model.user;

import org.springframework.stereotype.Service;

@Service
public class UserAccessAdmin extends UserService {
    public boolean getAccessMenuUser() {
        return true;
    }

    public boolean getAccessMenuRoom() {
        return true;
    }

    public boolean getAccessMenuRoomReservation() {
        return true;
    }
}
