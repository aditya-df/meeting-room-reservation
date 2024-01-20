package tkba.team6.roomreservationsystem.db.model.reservation;

import java.sql.Date;
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

    public final Reservations saveReservation(Reservations Reservation) {
        return ReservationRepository.save(Reservation);
    }

    public final List<Reservations> getReservationOnPending() {
        return ReservationRepository.findByStatus("pending");
    }

    public final List<Reservations> findByStatusInAndRequestDate(List<String> Status, Date requestDate) {
        return ReservationRepository.findByStatusInAndRequestDate(Status, requestDate);
    }

    public final void rejectReservation (long id) throws Exception {
        Reservations Reservations = ReservationRepository.findByStatusAndId("pending", id).orElse(null);
        if (Reservations != null) {
            Reservations.setStatus("rejected");
            ReservationRepository.save(Reservations);
        }else{
            throw new Exception("Reservation Invalid");
        }
    }

    public final void approveReservation (long id) throws Exception {
        Reservations Reservations = ReservationRepository.findByStatusAndId("pending", id).orElse(null);
        if (Reservations != null) {
            Reservations.setStatus("approved");
            ReservationRepository.save(Reservations);
        }else{
            throw new Exception("Reservation Invalid");
        }
    }
}
