package tkba.team6.roomreservationsystem.db.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Rooms Room;
    private Date requestDate;
    private Time startTime;
    private Time endTime;
    private String status;
    private String request_snack;
}
