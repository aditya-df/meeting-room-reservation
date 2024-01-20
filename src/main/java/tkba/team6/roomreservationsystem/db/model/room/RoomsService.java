package tkba.team6.roomreservationsystem.db.model.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tkba.team6.roomreservationsystem.db.repository.RoomRepository;
import tkba.team6.roomreservationsystem.db.entity.Rooms;

@Service
public class RoomsService {
    @Autowired
    private RoomRepository RoomRepository;

    public final List<Rooms> getAllRooms() {
        return RoomRepository.findAll();
    }

    public final Rooms getRoomById(long id) {
        return RoomRepository.findById(id).orElse(null);
    }

    public final Rooms saveRoom(Rooms Room) {
        return RoomRepository.save(Room);
    }
}
