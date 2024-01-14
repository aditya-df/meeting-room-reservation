package tkba.team6.roomreservationsystem.db.model.user;

import org.springframework.stereotype.Service;

@Service
public class UserAccess extends UserService {
    public boolean getAccessMenuRoomReservation() {
        return true;
    }
}
