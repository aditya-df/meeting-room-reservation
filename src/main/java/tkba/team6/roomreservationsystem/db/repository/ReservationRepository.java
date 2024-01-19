package tkba.team6.roomreservationsystem.db.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Nullable;
import tkba.team6.roomreservationsystem.db.entity.Reservations;

@Repository
public interface ReservationRepository extends CrudRepository<Reservations, Long> {
    Reservations save(@Nullable Reservations Reservation);

    Optional<Reservations> findById(long id);

    List<Reservations> findAll();

    List<Reservations> findByStatus(String string);

    Optional<Reservations> findByStatusAndId(String string, long id);

    List<Reservations> findByStatusInAndRequestDate(List<String> Status, Date requestDate, Time startTime, Time endTime);
}