package tkba.team6.roomreservationsystem.db.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;
import java.sql.Time;
import java.sql.Date;

@Entity
@Data
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long request_user;
    private Long room_id;
    private Date requestDate;
    private Time startTime;
    private Time endTime;
    private String status;
    private String request_snack;
}
