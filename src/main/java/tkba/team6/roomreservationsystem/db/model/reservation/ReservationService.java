package tkba.team6.roomreservationsystem.db.model.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tkba.team6.roomreservationsystem.db.repository.ReservationRepository;
import tkba.team6.roomreservationsystem.db.entity.Reservations;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository ReservationRepository;

    public final List<Reservations> getAllReservation() {
        return ReservationRepository.findAll();
    }

    public final Reservations getReservationById(long id) {
        return ReservationRepository.findById(id).orElse(null);
    }

    public final void saveReservation(Reservations Reservation) {
        ReservationRepository.save(Reservation);
    }
}
