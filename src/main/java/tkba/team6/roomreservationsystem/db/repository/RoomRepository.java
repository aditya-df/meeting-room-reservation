package tkba.team6.roomreservationsystem.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import tkba.team6.roomreservationsystem.db.entity.Rooms;

@Repository
public interface RoomRepository extends CrudRepository<Rooms, Long> {
    Rooms save(@Nullable Rooms Rooms);

    Optional<Rooms> findById(long id);

    List<@NonNull Rooms> findAll();

    Rooms findByName(String RoomName);
}