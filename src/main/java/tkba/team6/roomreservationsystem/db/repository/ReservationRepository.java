package tkba.team6.roomreservationsystem.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import tkba.team6.roomreservationsystem.db.entity.Reservations;

@Repository
public interface ReservationRepository extends CrudRepository<Reservations, Long> {
    Reservations save(@Nullable Reservations Reservation);

    Optional<Reservations> findById(long id);

    List<@NonNull Reservations> findAll();
}